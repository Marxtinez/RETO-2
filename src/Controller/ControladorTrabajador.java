package Controller;

import Model.Trabajador;
import java.sql.*;
import java.util.ArrayList;


public class ControladorTrabajador {
public static ArrayList<Trabajador> trabajadores=new ArrayList<>();

    //Obtener todos los trabajadores
    public static void obtenerInformacionTrabajador() throws SQLException {
        trabajadores.clear();
        Statement st= ControladorConexion.miConexion.createStatement();
        ResultSet rs_scriptInfTrabajador = st.executeQuery( "SELECT * FROM trabajador");
            while (rs_scriptInfTrabajador.next()) {
                trabajadores.add(new Trabajador(rs_scriptInfTrabajador.getInt("id_trabajador"),
                        rs_scriptInfTrabajador.getString("email"),
                        rs_scriptInfTrabajador.getString("nombre"),
                        rs_scriptInfTrabajador.getString("telefono"),
                        rs_scriptInfTrabajador.getString("cargo"),
                        rs_scriptInfTrabajador.getString("persona_contacto"),
                        rs_scriptInfTrabajador.getString("CIF")));
            }
            st.close();
    }
    //Actualizar trabajador
    public void actualizarTrabajador(int id_trabajadorModificar, int id_trabajador, String email, String nombre, String telefono, String cargo, String persona_contacto, String CIF) throws SQLException {
        obtenerInformacionTrabajador();
        Trabajador trabajadorMod = new Trabajador(id_trabajador, email, nombre, telefono, cargo, persona_contacto, CIF);

        for (int i = 0; i < trabajadores.size(); i++) {
            if (trabajadores.get(i).getId_trabajador() == id_trabajadorModificar) {
                trabajadores.set(i, trabajadorMod);
                break;
            }
        }

        String scriptActTrabajador = "UPDATE trabajador SET id_trabajador = ?, email = ?, nombre = ?, telefono = ?, cargo = ?, persona_contacto = ?, CIF = ? WHERE id_trabajador = ?";
        try (PreparedStatement ps = ControladorConexion.miConexion.prepareStatement(scriptActTrabajador)) {
            ps.setInt(1, id_trabajador);
            ps.setString(2, email);
            ps.setString(3, nombre);
            ps.setString(4, telefono);
            ps.setString(5, cargo);
            ps.setString(6, persona_contacto);
            ps.setString(7, CIF);
            ps.setInt(8, id_trabajadorModificar);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //Eliminar trabajador por ID
    public static void eliminarTrabajador(int id_trabajadorEliminar) throws SQLException {
        for (int i = 0; i < trabajadores.size(); i++) {
            if (trabajadores.get(i).getId_trabajador() == id_trabajadorEliminar) {
                trabajadores.remove(i);
                break;
            }
        }
        String scriptEliTrabajador = "DELETE FROM trabajador WHERE id_trabajador = ?";
        try (PreparedStatement ps = ControladorConexion.miConexion.prepareStatement(scriptEliTrabajador)) {
            ps.setInt(1, id_trabajadorEliminar);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //Insertar trabajador
    public void insertarTrabajador(int id_trabajador,String email,String nombre, String telefono, String cargo,String persona_contacto, String CIF){
        Trabajador nuevoTrabajador= new Trabajador(id_trabajador, email, nombre, telefono, cargo, persona_contacto, CIF);
        String scriptIntTrabajador="INSERT INTO trabajador(id_trabajador,email,nombre,telefono,cargo,persona_contacto,CIF) VALUES(?,?,?,?,?,?)";
        try {
                PreparedStatement statement = ControladorConexion.miConexion.prepareStatement(scriptIntTrabajador);
            statement.setInt(1,id_trabajador);
            statement.setString(2,email);
            statement.setString(3,nombre);
            statement.setString(4,telefono);
            statement.setString(5,cargo);
            statement.setString(6,persona_contacto);
            statement.setString(7, CIF);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Obtener trabajadores por empresa por el CIF
    // Obtener trabajadores por empresa por el CIF
    public static void obtenerTrabajadorPorEmpresa(String CIF) throws SQLException {
        String scriptObtTraPorEmp = "SELECT * FROM trabajador WHERE CIF = ?";
        trabajadores.clear(); // Limpiar la lista antes de llenarla
        try (PreparedStatement statement = ControladorConexion.miConexion.prepareStatement(scriptObtTraPorEmp)) {
            statement.setString(1, CIF);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Trabajador trabajador = new Trabajador(
                        resultSet.getInt("id_trabajador"),
                        resultSet.getString("email"),
                        resultSet.getString("nombre"),
                        resultSet.getString("telefono"),
                        resultSet.getString("cargo"),
                        resultSet.getString("persona_contacto"),
                        resultSet.getString("CIF")
                );
                trabajadores.add(trabajador);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
package Controller;

import Model.Trabajador;
import View.PanelConsultaTrabajadorTutor;
import View.PanelOpcionesTutor;

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
                        rs_scriptInfTrabajador.getBoolean("persona_contacto"),
                        rs_scriptInfTrabajador.getString("CIF")));
            }
            st.close();
    }
    //Actualizar trabajador
    public static void actualizarTrabajador(int id_trabajadorModificar,String email, String nombre, String telefono, String cargo, Boolean persona_contacto, String CIF,PanelConsultaTrabajadorTutor panel) throws SQLException {
        obtenerInformacionTrabajador();
        Trabajador trabajadorMod = new Trabajador(id_trabajadorModificar, email, nombre, telefono, cargo, persona_contacto, CIF);

        for (int i = 0; i < trabajadores.size(); i++) {
            if (trabajadores.get(i).getId_trabajador() == id_trabajadorModificar) {
                trabajadores.set(i, trabajadorMod);
                panel.comboTrabajador.removeItemAt(i);
                panel.comboTrabajador.addItem(trabajadorMod);
                break;
            }
        }

        String scriptActTrabajador = "UPDATE trabajador SET email = ?, nombre = ?, telefono = ?, cargo = ?, persona_contacto = ?, CIF = ? WHERE id_trabajador = ?";
        try (PreparedStatement ps = ControladorConexion.miConexion.prepareStatement(scriptActTrabajador)) {
            ps.setString(1, email);
            ps.setString(2, nombre);
            ps.setString(3, telefono);
            ps.setString(4, cargo);
            ps.setBoolean(5, persona_contacto);
            ps.setString(6, CIF);
            ps.setInt(7, id_trabajadorModificar);
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
    public static void insertarTrabajador(String email, String nombre, String telefono, String cargo, Boolean persona_contacto, String CIF, PanelConsultaTrabajadorTutor panel){
        String scriptIntTrabajador="INSERT INTO trabajador(email,nombre,telefono,cargo,persona_contacto,CIF) VALUES(?,?,?,?,?,?)";
        try {
                PreparedStatement statement = ControladorConexion.miConexion.prepareStatement(scriptIntTrabajador,Statement.RETURN_GENERATED_KEYS);
            statement.setString(1,email);
            statement.setString(2,nombre);
            statement.setString(3,telefono);
            statement.setString(4,cargo);
            statement.setBoolean(5,persona_contacto);
            statement.setString(6, CIF);
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()){
                int id_trabajador = rs.getInt(1);
                Trabajador nuevoTrabajador= new Trabajador(id_trabajador, email, nombre, telefono, cargo, persona_contacto, CIF);
                trabajadores.add(nuevoTrabajador);
                panel.comboTrabajador.addItem(nuevoTrabajador);
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Obtener trabajadores por empresa por el CIF
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
                        resultSet.getBoolean("persona_contacto"),
                        resultSet.getString("CIF")
                );
                trabajadores.add(trabajador);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int siguienteValor() throws SQLException {
        int id = 0;
        Statement st = ControladorConexion.miConexion.createStatement();
        ResultSet rs = st.executeQuery("SELECT last_value FROM public.trabajador_id_trabajador_seq");

        while (rs.next()){
            id = rs.getInt(1);
        }

        rs.close();
        st.close();
        return id;
    }
}
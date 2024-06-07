package Controller;

import Model.Trabajador;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ControladorTrabajador {
    private static final String URL = "jdbc:postgresql://192.168.109.1:5432/DBRETO";
    private static final String USER = "postgres";
    private static final String PASSWORD = "Virtual01";

    //Obtener todos los trabajadores
    public List<Trabajador> obtenerInformacionTrabajador() {
        String scriptInfTrabajador = "SELECT * FROM trabajador";
        List<Trabajador> trabajadores = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(scriptInfTrabajador)) {

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

        return trabajadores;
    }
    //Obtener trabajador por ID
    public Trabajador obtenerTrabajadorPorId(int id) {
        String scriptObtTrabajador = "SELECT * FROM trabajador WHERE id_trabajador = ?";
        try (Connection connection = DBConnection.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(scriptObtTrabajador)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Trabajador(
                        resultSet.getInt("id_trabajador"),
                        resultSet.getString("email"),
                        resultSet.getString("nombre"),
                        resultSet.getString("telefono"),
                        resultSet.getString("cargo"),
                        resultSet.getString("persona_contacto"),
                        resultSet.getString("CIF")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    //Actualizar trabajador
    public void actualizarTrabajador(Trabajador trabajador) {
        String scriptActTrabajador = "UPDATE trabajador SET email = ?, nombre = ?, telefono = ?, cargo = ?, persona_contacto = ?, CIF = ? WHERE id_trabajador = ?";
        try (Connection connection = DBConnection.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(scriptActTrabajador)) {
            statement.setString(1, trabajador.getEmail());
            statement.setString(2, trabajador.getNombre());
            statement.setString(3, trabajador.getTelefono());
            statement.setString(4, trabajador.getCargo());
            statement.setString(5, trabajador.getPersona_contacto());
            statement.setString(6, trabajador.getCIF());
            statement.setInt(7, trabajador.getId_trabajador());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //Eliminar trabajador por ID
    public void eliminarTrabajador(int id){
        String scriptEliTrabajador="DELETE FROM trabajador WHERE id_trabajador = ?";
        try(Connection connection = DBConnection.getConnection(URL,USER,PASSWORD);
        PreparedStatement statement= connection.prepareStatement(scriptEliTrabajador)) {
            statement.setInt(1,id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    //Insertar trabajador
    public void insertarTrabajador(Trabajador trabajador){
        String scriptIntTrabajador="INSERT INTO trabajador(email,nombre,telefono,cargo,persona_contacto,CIF) VALUES(?,?,?,?,?,?)";
        try (Connection connection = DBConnection.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(scriptIntTrabajador)) {
            statement.setString(1, trabajador.getEmail());
            statement.setString(2, trabajador.getNombre());
            statement.setString(3, trabajador.getTelefono());
            statement.setString(4, trabajador.getCargo());
            statement.setString(5, trabajador.getPersona_contacto());
            statement.setString(6, trabajador.getCIF());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Obtener trabajadores por empresa por el CIF
    public List<Trabajador>obtenerTrabajadorPorEmpresa(String CIF){
        String scriptObtTraPorEmp="SELECT * FROM trabajador WHERE CIF=?";
        List<Trabajador> trabajadores=new ArrayList<>();
        try(Connection connection = DBConnection.getConnection(URL,USER,PASSWORD);
            PreparedStatement statement= connection.prepareStatement(scriptObtTraPorEmp)) {
            statement.setString(1,CIF);
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
        return trabajadores;
    }
}
package Controller;

import Model.Trabajador;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ControladorTrabajador {
    private static final String URL = "jdbc:postgresql://192.168.109.1:5432/DBRETO";
    private static final String USER = "postgres";
    private static final String PASSWORD = "Virtual01";

    public List<Trabajador> obtenerInformacionTrabajador(){
        String scriptTrabajador="SELECT * FROM trabajador";
        List<Trabajador> trabajadores=new ArrayList<>();

        try (Connection connection = DBConnection.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(scriptTrabajador)) {

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

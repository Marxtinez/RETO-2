package Controller;

import Model.Empresa_Contacta_Tutor;
import Model.TutorFCT;
import java.sql.*;
import java.util.ArrayList;

//Consulta C8
public class ControladorConsultaHistorialContactos {
    public static ArrayList<Empresa_Contacta_Tutor> resultados = new ArrayList<>();

    public static void cargarHistorialContactos(String cif) {
        if (resultados != null) {
            resultados.clear();
        }

        String consultaSQL = "SELECT ect.id_tutor, ect.CIF, ect.fecha, t.email, t.nombre, t.telefono " +
                "FROM empresa_contacta_tutor ect " +
                "JOIN tutorfct t ON ect.id_tutor = t.id_tutor " +
                "WHERE ect.CIF = ? " +
                "ORDER BY ect.fecha DESC";

        try {
            PreparedStatement statement = ControladorConexion.miConexion.prepareStatement(consultaSQL);
            statement.setString(1, cif);

            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Empresa_Contacta_Tutor contacto = new Empresa_Contacta_Tutor(
                            rs.getInt("id_tutor"),
                            rs.getString("CIF"),
                            rs.getString("fecha")
                    );

                    TutorFCT tutor = new TutorFCT(
                            rs.getInt("id_tutor"),
                            rs.getString("email"),
                            rs.getString("nombre"),
                            rs.getString("telefono")
                    );
                    resultados.add(contacto);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}


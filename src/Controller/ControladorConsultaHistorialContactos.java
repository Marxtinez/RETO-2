package Controller;
import Model.Empresa_Contacta_Tutor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ControladorConsultaHistorialContactos {

    // Obtener historial de contacto por empresa
    public static List<Empresa_Contacta_Tutor> obtenerHistorialContactoPorEmpresa(String CIF) throws SQLException {
        List<Empresa_Contacta_Tutor> historialPorEmpresa = new ArrayList<>();
        String scriptObtenerHistorialContacto = "SELECT * FROM empresa_contacta_tutor WHERE CIF = ?";
        try (PreparedStatement ps = ControladorConexion.miConexion.prepareStatement(scriptObtenerHistorialContacto)) {
            ps.setString(1, CIF);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Empresa_Contacta_Tutor historial = new Empresa_Contacta_Tutor(
                        rs.getInt("id_tutor"),
                        rs.getString("CIF"),
                        rs.getString("fecha")
                );
                historialPorEmpresa.add(historial);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return historialPorEmpresa;
    }
}

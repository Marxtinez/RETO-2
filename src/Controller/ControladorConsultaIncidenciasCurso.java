package Controller;
import Model.Empresa_Contacta_Tutor;
import Model.Incidencia;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class ControladorConsultaIncidenciasCurso {
    // Obtener historial de incidencia por curso
    public static List<Incidencia> obtenerHistorialContactoPorEmpresa(int id_incidencia) throws SQLException {
        List<Incidencia> historialIncidenciaPorCurso = new ArrayList<>();
        String scriptObtenerHistorialContacto = "SELECT * FROM incidencia WHERE id_incidencia = ?";
        try (PreparedStatement ps = ControladorConexion.miConexion.prepareStatement(scriptObtenerHistorialContacto)) {
            ps.setInt(1, id_incidencia);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Incidencia historial = new Incidencia(
                        rs.getInt("id_incidencia"),
                        rs.getString("descripcion"),
                        rs.getString("fecha"),
                        rs.getString("CIF")
                );
                historialIncidenciaPorCurso.add(historial);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return historialIncidenciaPorCurso;
    }
}

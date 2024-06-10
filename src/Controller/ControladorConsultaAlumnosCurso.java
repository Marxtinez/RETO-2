package Controller;
import Model.Empresa_Solicita_Ciclo;
import java.sql.*;

public class ControladorConsultaAlumnosCurso {
    //Obtener historial de alumnos solicitados por empresa
    public static Empresa_Solicita_Ciclo obtenerSolicitudPorEmpresaYCiclo(String cif, String idCiclo, String curso) throws SQLException {
        String scriptObtenerSolicitud = "SELECT CIF, id_ciclo, cant_alumnos " +
                "FROM GRUPO_REALIZA_EMPRESA " +
                "WHERE CIF = ? AND id_ciclo = ? AND curso = ?";
        Empresa_Solicita_Ciclo solicitud = null;

        try (PreparedStatement statement = ControladorConexion.miConexion.prepareStatement(scriptObtenerSolicitud)) {
            statement.setString(1, cif);
            statement.setString(2, idCiclo);
            statement.setString(3, curso);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                solicitud = new Empresa_Solicita_Ciclo(
                        rs.getString("CIF"),
                        rs.getString("id_ciclo"),
                        rs.getInt("cant_alumnos")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return solicitud;
    }
}
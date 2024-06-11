package Controller;
import Model.Empresa_Solicita_Ciclo;
import java.sql.*;

public class ControladorConsultaAlumnosCurso {
    public static Empresa_Solicita_Ciclo obtenerSolicitudPorEmpresaYCiclo(String cif, String id_grupo, String curso) throws SQLException {
        String scriptObtenerSolicitud = "SELECT gre.cif, gre.id_grupo, gre.cant_alumnos, e.nombre AS nombre_empresa " +
                " FROM grupo_realiza_empresa gre" +
                "INNER JOIN empresa e ON gre.cif = e.cif" +
                "WHERE gre.cif = ? AND gre.id_grupo = ? AND gre.curso = ?";
        Empresa_Solicita_Ciclo solicitud = null;

        try (PreparedStatement statement = ControladorConexion.miConexion.prepareStatement(scriptObtenerSolicitud)) {
            statement.setString(1, cif);
            statement.setString(2, id_grupo);
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
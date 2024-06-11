package Controller;

import Model.Grupo;
import Model.Incidencia;
import Model.IncidenciaGrupo;
import java.sql.*;
import java.util.ArrayList;

public class ControladorConsultaIncidenciasCurso {
    public static ArrayList<IncidenciaGrupo>resultados = new ArrayList<>();
    public static void cargarConsultaIncidenciasPorCurso(String fechaInicio, String fechaFin) {
        if (resultados != null) {
            resultados.clear();
        }

        String scriptObtenerIncidenciasPorCurso = "SELECT i.id_incidencia, i.descripcion, i.fecha, i.CIF, g.id_grupo, g.num_alumnos, g.id_ciclo " +
                "FROM incidencia i " +
                "JOIN grupo g ON i.id_grupo = g.id_grupo " +
                "WHERE i.fecha BETWEEN ? AND ?";

        try {
            PreparedStatement statement = ControladorConexion.miConexion.prepareStatement(scriptObtenerIncidenciasPorCurso);
            statement.setString(1, fechaInicio);
            statement.setString(2, fechaFin);

            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    int id_incidencia = rs.getInt("id_incidencia");
                    String descripcion = rs.getString("descripcion");
                    String fecha = rs.getString("fecha");
                    String CIF = rs.getString("CIF");

                    String id_grupo = rs.getString("id_grupo");
                    int num_alumnos = rs.getInt("num_alumnos");
                    String id_ciclo = rs.getString("id_ciclo");

                    Grupo grupo = new Grupo(id_grupo, num_alumnos, id_ciclo);
                    Incidencia incidencia = new Incidencia(id_incidencia, descripcion, fecha, CIF);

                    IncidenciaGrupo incidenciaGrupo = new IncidenciaGrupo(incidencia, grupo);
                    resultados.add(incidenciaGrupo);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

package Controller;


import Model.Grupo;
import Model.Incidencia;
import Model.IncidenciaGrupo;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ControladorConsultaIncidenciasCurso {
    public static ArrayList<IncidenciaGrupo> resultados = new ArrayList<>();

    public static void cargarConsultaIncidenciasPorCurso(String fechaInicio, String fechaFin, String curso) {
        if (resultados != null) {
            resultados.clear();
        }

        String scriptObtenerIncidenciasPorCurso = "SELECT i.id_incidencia, i.descripcion, i.fecha, i.CIF, g.id_grupo, g.num_alumnos, g.id_ciclo " +
                "FROM incidencia i " +
                "JOIN empresa e ON i.CIF = e.CIF " +
                "JOIN grupo_realiza_empresa gre ON e.CIF = gre.CIF " +
                "JOIN grupo g ON gre.id_grupo = g.id_grupo " +
                "WHERE i.fecha BETWEEN ? AND ? " +
                "AND gre.curso = ?";

        try {
            PreparedStatement statement = ControladorConexion.miConexion.prepareStatement(scriptObtenerIncidenciasPorCurso);

            // Convertir las fechas de cadena a java.sql.Date usando el método convertToDate
            Date sqlFechaInicio = convertToDate(fechaInicio);
            Date sqlFechaFin = convertToDate(fechaFin);

            statement.setDate(1, sqlFechaInicio);
            statement.setDate(2, sqlFechaFin);
            statement.setString(3, curso);

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
            e.printStackTrace();
        }
    }

    public static Date convertToDate(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-dd-MM");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        return Date.valueOf(localDate);
    }

    public static void main(String[] args) {
        cargarConsultaIncidenciasPorCurso("2023-01-06", "2024-01-01", "2023-2024");
        System.out.println(resultados.toString());
    }
}

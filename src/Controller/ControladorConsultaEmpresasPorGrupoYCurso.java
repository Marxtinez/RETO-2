package Controller;

public class ControladorConsultaEmpresasPorGrupoYCurso {
    public static ArrayList<Empresa> consultaEmpresasPorCicloYCursos(String ciclo, String curso) {
        ArrayList<Empresa> resultados = new ArrayList<>();

        String consultaSQL = "SELECT \n" +
                "    e.cif AS CIF,\n" +
                "    e.nombre AS Nombre_Empresa,\n" +
                "    COUNT(gre.num_fcts) AS Numero_Practicas\n" +
                "FROM \n" +
                "    empresa e\n" +
                "JOIN \n" +
                "    grupo_realiza_empresa gre ON e.cif = gre.cif\n" +
                "JOIN \n" +
                "    grupo g ON gre.id_grupo = g.id_grupo\n" +
                "WHERE \n" +
                "    g.ciclo = ? AND gre.curso = ?\n" +
                "GROUP BY \n" +
                "    e.cif, e.nombre;";

        try (Connection conexion = ControladorConexion.miConexion;
             PreparedStatement ps = conexion.prepareStatement(consultaSQL)) {

            ps.setString(1, ciclo);
            ps.setString(2, curso);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Empresa empresa = new Empresa();
                    empresa.setCIF(rs.getString("CIF"));
                    empresa.setNombre(rs.getString("Nombre_Empresa"));
                    empresa.setNum_practicas(rs.getInt("Numero_Practicas"));

                    resultados.add(empresa);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return resultados;
    }

    // Consulta C3
    public static ArrayList<TutorFCT> consultaProfesorYEmpresasPorGrupoYCursos(String grupo, String curso) {
        ArrayList<TutorFCT> resultados = new ArrayList<>();

        String consultaSQL = "SELECT \n" +
                "    tf.id_tutor AS ID_Profesor,\n" +
                "    tf.nombre AS Nombre_Profesor,\n" +
                "    tf.email AS Email_Profesor,\n" +
                "    tf.telefono AS Telefono_Profesor,\n" +
                "    e.cif AS CIF,\n" +
                "    e.nombre AS Nombre_Empresa,\n" +
                "    COUNT(gre.num_fcts) AS Numero_Practicas\n" +
                "FROM \n" +
                "    tutor_responsable_grupo trg\n" +
                "JOIN \n" +
                "    grupo g ON trg.id_grupo = g.id_grupo\n" +
                "JOIN \n" +
                "    tutorfct tf ON trg.id_tutor = tf.id_tutor\n" +
                "JOIN \n" +
                "    grupo_realiza_empresa gre ON g.id_grupo = gre.id_grupo AND g.curso = gre.curso\n" +
                "JOIN \n" +
                "    empresa e ON gre.cif = e.cif\n" +
                "WHERE \n" +
                "    g.nombre = ? AND g.curso = ?\n" +
                "GROUP BY \n" +
                "    tf.id_tutor, tf.nombre, tf.email, tf.telefono, e.cif, e.nombre;";

        try (Connection conexion = ControladorConexion.miConexion;
             PreparedStatement ps = conexion.prepareStatement(consultaSQL)) {

            ps.setString(1, grupo);
            ps.setString(2, curso);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    TutorFCT tutor = new TutorFCT();
                    tutor.setId_tutor(rs.getInt("ID_Profesor"));
                    tutor.setNombre(rs.getString("Nombre_Profesor"));
                    tutor.setEmail(rs.getString("Email_Profesor"));
                    tutor.setTelefono(rs.getString("Telefono_Profesor"));

                    Empresa empresa = new Empresa();
                    empresa.setCIF(rs.getString("CIF"));
                    empresa.setNombre(rs.getString("Nombre_Empresa"));
                    empresa.setNum_practicas(rs.getInt("Numero_Practicas"));

                    tutor.addEmpresa(empresa);

                    resultados.add(tutor);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return resultados;
    }

    // Consulta C4
    public static ArrayList<Empresa> consultaDatosFCTPorEmpresaYCursos(String CIF, String curso) {
        ArrayList<Empresa> resultados = new ArrayList<>();

        String consultaSQL = "SELECT \n" +
                "    g.nombre AS Nombre_Grupo,\n" +
                "    a.id_alumno AS ID_Alumno,\n" +
                "    a.nombre AS Nombre_Alumno\n" +
                "FROM \n" +
                "    empresa e\n" +
                "JOIN \n" +
                "    grupo_realiza_empresa gre ON e.cif = gre.cif\n" +
                "JOIN \n" +
                "    grupo g ON gre.id_grupo = g.id_grupo\n" +
                "JOIN \n" +
                "    alumno a ON g.id_grupo = a.id_grupo\n" +
                "WHERE \n" +
                "    e.cif = ? AND gre.curso = ?;";

        try (Connection conexion = ControladorConexion.miConexion;
             PreparedStatement ps = conexion.prepareStatement(consultaSQL)) {

            ps.setString(1, CIF);
            ps.setString(2, curso);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Grupo grupo = new Grupo();
                    grupo.setNombre(rs.getString("Nombre_Grupo"));

                    Alumno alumno = new Alumno();
                    alumno.setId_alumno(rs.getInt("ID_Alumno"));
                    alumno.setNombre(rs.getString("Nombre_Alumno"));

                    Empresa empresa = new Empresa();
                    empresa.setCIF(CIF);
                    empresa.addGrupo(grupo);
                    grupo.addAlumno(alumno);

                    resultados.add(empresa);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return resultados;
    }
}

package Controller;

import Model.Empresa;
import Model.TutorFCT;
import Model.TutorFCTEmpresaFCT;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ControladorConsultaGrupoTutorEmpresas {
    public static ArrayList<TutorFCTEmpresaFCT> resultados = new ArrayList<>();

    public static void cargaConsultaGrupoTutorEmpresasC3(String grupo, String curso) {
        if (resultados != null) {
            resultados.clear();
        }

        String consultaSQL = "SELECT  \n" +
                "tf.id_tutor AS ID_Profesor, \n" +
                "tf.nombre AS Nombre_Profesor, \n" +
                "tf.email AS Email_Profesor, \n" +
                "tf.telefono AS Telefono_Profesor, \n" +
                "e.cif AS CIF, \n" +
                "e.nombre AS Nombre_Empresa, \n" +
                "e.direccion AS Direccion_Empresa, \n" +
                "e.tecnologias AS Tecnologias_Empresa, \n" +
                "e.sector AS Sector_Empresa, \n" +
                "e.telefono AS Telefono_Empresa, \n" +
                "e.num_empleados AS Numero_Empleados, \n" +
                "e.ult_anio_colab AS Ultimo_Anio_Colaboracion, \n" +
                "COUNT(gre.id_grupo) AS Numero_Practicas_Acogidas,\n" +
                "sum(gre.cant_alumnos) as Alumnos_FCT_Realizadas_Total \n" +
                "FROM  \n" +
                "tutorfct tf \n" +
                "JOIN  \n" +
                "tutor_responsable_grupo trg ON tf.id_tutor = trg.id_tutor \n" +
                "JOIN  \n" +
                "grupo g ON trg.id_grupo = g.id_grupo \n" +
                "JOIN  \n" +
                "grupo_realiza_empresa gre ON g.id_grupo = gre.id_grupo AND trg.curso = gre.curso \n" +
                "JOIN  \n" +
                "empresa e ON gre.cif = e.cif \n" +
                "WHERE  \n" +
                "g.id_grupo = ? AND trg.curso = ? \n" +
                "GROUP BY  \n" +
                "tf.id_tutor, tf.nombre, tf.email, tf.telefono, e.cif, e.nombre, e.direccion, e.tecnologias, e.sector, e.telefono, e.num_empleados, e.ult_anio_colab; \n";

        try {
            PreparedStatement ps = ControladorConexion.miConexion.prepareStatement(consultaSQL);
            ps.setString(1, grupo);
            ps.setString(2, curso);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Empresa empresa = new Empresa();
                    empresa.setCIF(rs.getString("CIF"));
                    empresa.setNombre(rs.getString("nombre_empresa"));
                    empresa.setDireccion(rs.getString("direccion_empresa"));
                    empresa.setTecnologias(rs.getString("tecnologias_empresa"));
                    empresa.setSector(rs.getString("sector_empresa"));
                    empresa.setTelefono(rs.getString("telefono_empresa"));
                    empresa.setNum_empleados(rs.getInt("numero_empleados"));
                    empresa.setUlt_anio_colab(rs.getInt("ultimo_anio_colaboracion"));

                    TutorFCT tutor = new TutorFCT();
                    tutor.setId_tutor(rs.getInt("ID_Profesor"));
                    tutor.setNombre(rs.getString("Nombre_Profesor"));
                    tutor.setTelefono(rs.getString("Telefono_Profesor"));
                    tutor.setEmail(rs.getString("Email_Profesor"));

                    int num_practicas = rs.getInt("Numero_Practicas_Acogidas");
                    int num_total_alumnos_FCT = rs.getInt("Alumnos_FCT_Realizadas_Total");

                    TutorFCTEmpresaFCT resultado = new TutorFCTEmpresaFCT(empresa, tutor,num_practicas, num_total_alumnos_FCT);
                    resultados.add(resultado);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

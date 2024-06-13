package Controller;

import Model.EmpresaCursoFCT;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ControladorConsultaFCTPorEmpresaYCurso {
    public static ArrayList<EmpresaCursoFCT> resultados = new ArrayList<>();
    public static void cargaConsultasFCTPorEmpresaYCursoC4(String cif, String curso){
        if (resultados != null) {
            resultados.clear();
        }

        String consultaSQL ="SELECT \n" +
                "    g.id_grupo AS ID_Grupo,\n" +
                "    g.num_alumnos AS Numero_Alumnos,\n" +
                "    c.id_ciclo AS ID_Ciclo,\n" +
                "    COUNT(gre.id_grupo) AS Numero_Practicas_Acogidas,\n" +
                "    sum(gre.cant_alumnos) as Cantidad_Alumnos_Realizado_FCT\n" +
                "FROM \n" +
                "    grupo g\n" +
                "JOIN \n" +
                "    grupo_realiza_empresa gre ON g.id_grupo = gre.id_grupo\n" +
                "JOIN \n" +
                "    ciclo c ON g.id_ciclo = c.id_ciclo\n" +
                "WHERE \n" +
                "    gre.cif = ? AND gre.curso = ?\n" +
                "GROUP BY \n" +
                "    g.id_grupo, g.num_alumnos, c.id_ciclo;\n";
        try {
            PreparedStatement ps = ControladorConexion.miConexion.prepareStatement(consultaSQL);
            ps.setString(1, cif);
            ps.setString(2, curso);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    EmpresaCursoFCT resultado = new EmpresaCursoFCT(rs.getString(1),rs.getInt(2),rs.getString(3),rs.getInt(4),rs.getInt(5));
                    resultados.add(resultado);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

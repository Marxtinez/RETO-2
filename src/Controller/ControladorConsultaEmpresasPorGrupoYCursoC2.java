package Controller;

import Model.Empresa;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import Model.EmpresaFCT;
import java.sql.SQLException;

public class ControladorConsultaEmpresasPorGrupoYCursoC2 {
    public static ArrayList<EmpresaFCT> resultados = new ArrayList<>();

    public static void cargaConsultaCicloEmpresas(String ciclo, String curso) {
        if (resultados != null) {
            resultados.clear();
        }

        String consultaSQL = "SELECT \n" +
                "    e.cif AS CIF,\n" +
                "    e.nombre AS Nombre_Empresa,\n" +
                "    e.direccion AS Direccion_Empresa,\n" +
                "    e.tecnologias AS Tecnologias_Empresa,\n" +
                "    e.sector AS Sector_Empresa,\n" +
                "    e.telefono AS Telefono_Empresa,\n" +
                "    e.num_empleados AS Numero_Empleados,\n" +
                "    e.ult_anio_colab AS Ultimo_Anio_Colaboracion,\n" +
                "    COUNT(gre.id_grupo) AS Numero_Practicas_Acogidas,\n" +
                "    sum(gre.cant_alumnos) as Alumnos_FCT_Realizadas_Total\n" +
                "FROM \n" +
                "    empresa e\n" +
                "JOIN \n" +
                "    grupo_realiza_empresa gre ON e.cif = gre.cif\n" +
                "JOIN \n" +
                "    grupo g ON gre.id_grupo = g.id_grupo\n" +
                "WHERE \n" +
                "    g.id_ciclo = ? AND gre.curso = ?\n" +
                "GROUP BY \n" +
                "    e.cif, e.nombre, e.direccion, e.tecnologias, e.sector, e.telefono, e.num_empleados, e.ult_anio_colab;";

        try {
            PreparedStatement ps = ControladorConexion.miConexion.prepareStatement(consultaSQL);
            ps.setString(1, ciclo);
            ps.setString(2, curso);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Empresa empresa = new Empresa();
                    empresa.setCIF(rs.getString("CIF"));
                    empresa.setNombre(rs.getString("Nombre_Empresa"));
                    empresa.setDireccion(rs.getString("Direccion_Empresa"));
                    empresa.setTecnologias(rs.getString("Tecnologias_Empresa"));
                    empresa.setSector(rs.getString("Sector_Empresa"));
                    empresa.setTelefono(rs.getString("Telefono_Empresa"));
                    empresa.setNum_empleados(rs.getInt("Numero_Empleados"));
                    empresa.setUlt_anio_colab(rs.getInt("Ultimo_Anio_Colaboracion"));
                    int num_practicas = rs.getInt("Numero_practicas_acogidas");
                    int alumnos_FCT_Realizadas_Total = rs.getInt("Alumnos_FCT_Realizadas_Total");
                    EmpresaFCT resultado = new EmpresaFCT(empresa,num_practicas, alumnos_FCT_Realizadas_Total);
                    resultados.add(resultado);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /*
    public static void main(String[] args) {
        cargaConsultaCicloEmpresas("2DAM", "2023-2024");
        System.out.println(resultados.toString());
    }

     */
}

package Controller;
import Model.Empresa;
import Model.EmpresaTecnologias;

import java.util.ArrayList;
import java.sql.*;

public class ControladorConsultaEmpresaTecnologia {
    public static ArrayList<EmpresaTecnologias> resultados = new ArrayList<>();

    public static void cargarConsultaEmpresaC7(String tecnologias) {
        String scriptObtenerEmpresasPorTecnologias = "SELECT e.nombre AS nombre_empresa, e.tecnologias AS tecnologias " +
                "                             FROM empresa e" +
                "                             WHERE e.tecnologias LIKE ?";
        try {
            PreparedStatement statement = ControladorConexion.miConexion.prepareStatement(scriptObtenerEmpresasPorTecnologias);
            statement.setString(1, tecnologias);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Empresa empresa = new Empresa();
                    empresa.setNombre(rs.getString("nombre_empresa"));
                    Empresa tecnologia = new Empresa();
                    tecnologia.setTecnologias(rs.getString("tecnologias"));
                    EmpresaTecnologias resultado = new EmpresaTecnologias(empresa, tecnologia);
                    resultados.add(resultado);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
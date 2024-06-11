package Controller;
import Model.Empresa;
import Model.EmpresaTecnologias;

import java.util.ArrayList;
import java.sql.*;

public class ControladorConsultaEmpresaTecnologia {
    public static ArrayList<EmpresaTecnologias>resultados;

    public static void cargarConsultaEmpresaC7(String tecnologias){
        if (resultados !=null){
            resultados.clear();
        }
    String scriptObtenerEmpresasPorTecnologias="SELECT e.nombre AS nombre_empresa, e.tecnologias AS tecnologias \" +\n" +
            "                             \"FROM empresa e \" +\n" +
            "                             \"WHERE e.tecnologias LIKE ?";
         try {
             PreparedStatement statement=ControladorConexion.miConexion.prepareStatement(scriptObtenerEmpresasPorTecnologias);
             statement.setString(1,tecnologias);
            try(ResultSet rs=statement.executeQuery()){
                while (rs.next()){
                    Empresa empresa=new Empresa();
                    empresa.setNombre(rs.getString("nombre_empresa"));
                    Empresa tecnologia= new Empresa();
                    tecnologia.setTecnologias(rs.getString("tecnologia"));
                    EmpresaTecnologias resultado=new EmpresaTecnologias(empresa,tecnologia);
                    resultados.add(resultado);
                }
            }

         }catch (SQLException e){
        throw new RuntimeException(e);
         }
    }

    public static void main(String[] args) {
        cargarConsultaEmpresaC7("AgriTech, IoT");
        for (EmpresaTecnologias resultado : resultados) {
            System.out.println("Nombre de la Empresa: " + resultado.getEmpresa().getNombre());
            System.out.println("Tecnologías: " + resultado.getEmpresa().getTecnologias());
        }
    }
}
package Controller;
import Model.Empresa;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class ControladorConsultaEmpresaTecnologia {
    //Obtener empresas por tecnologias
    public static List<Empresa> obtenerEmpresasPorTecnologia(String tecnologias) throws SQLException{
    List<Empresa> empresaPorTecnologia=new ArrayList<>();
    String scriptObtenerEmpresasPorTecnologias="SELECT * FROM empresa WHERE tecnologias = ? ";
    try (PreparedStatement statement=ControladorConexion.miConexion.prepareStatement(scriptObtenerEmpresasPorTecnologias)){
        statement.setString(1,tecnologias);
        ResultSet rs=statement.executeQuery();
        while (rs.next()){
            Empresa listadoEmpresas=new Empresa(
                    rs.getString("CIF"),
                    rs.getString("nombre"),
                    rs.getString("direccion"),
                    rs.getString("tecnologias"),
                    rs.getString("sector"),
                    rs.getString("telefonos"),
                    rs.getInt("num_empleados"),
                    rs.getInt("ult_anio_colab")
                    );
        }
    }catch (SQLException e){
        e.printStackTrace();
    }
    return empresaPorTecnologia;
    }
}
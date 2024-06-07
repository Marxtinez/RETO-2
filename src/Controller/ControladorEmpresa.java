package Controller;

import Model.Empresa;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ControladorEmpresa {
    public static ArrayList<Empresa> empresas = new ArrayList<>();

    public static void cargaContenidoEmpresas() throws SQLException {

        Statement st = ControladorConexion.miConexion.createStatement();

        ResultSet rs_sqlLeerEmpresas = st.executeQuery("SELECT * FROM EMPRESA");

        while (rs_sqlLeerEmpresas.next()){
            empresas.add(new Empresa(rs_sqlLeerEmpresas.getString("cif"),
                    rs_sqlLeerEmpresas.getString("nombre"),
                    rs_sqlLeerEmpresas.getString("direccion"),
                    rs_sqlLeerEmpresas.getString("tecnologias"),
                    rs_sqlLeerEmpresas.getString("sector"),
                    rs_sqlLeerEmpresas.getString("telefono"),
                    rs_sqlLeerEmpresas.getInt("num_empleados"),
                    rs_sqlLeerEmpresas.getInt("ult_anio_colab")));
        }
        st.close();
    }
    public static void modificaEmpresa(Empresa empresaMod) throws SQLException {

        //Primero cargo el array de empresas:
        cargaContenidoEmpresas();

        for (int i=0;i<empresas.size();i++){
            if (empresas.get(i).getCIF().equals(empresaMod.getCIF())){
                empresas.set(i,empresaMod);
            }
        }

    }

    public static void main(String[] args) throws SQLException {
        /*empresas.add(new Empresa("CIF001", "Tech Innovations", "123 Silicon Valley", "AI, Cloud Computing", "Technology", "600123456", 150, 2023));
        empresas.add(new Empresa("CIF002", "Green Energy", "456 Renewable Rd", "Solar, Wind", "Energy", "600234567", 200, 2022));
        empresas.add(new Empresa("CIF003", "Health Solutions", "789 Wellness Blvd", "Telemedicine, AI", "Healthcare", "600345678", 120, 2021));
        empresas.add(new Empresa("CIF004", "EduFuture", "101 Learning St", "EdTech, AI", "Education", "600456789", 180, 2023));
        empresas.add(new Empresa("CIF005", "Fintech World", "202 Finance Ave", "Blockchain, AI", "Finance", "600567890", 250, 2022));
        empresas.add(new Empresa("CIF006", "AgriCorp", "303 Farming Ln", "AgriTech, IoT", "Agriculture", "600678901", 100, 2021));
        empresas.add(new Empresa("CIF007", "Retail Hub", "404 Commerce St", "E-commerce, Logistics", "Retail", "600789012", 300, 2023));
        empresas.add(new Empresa("CIF008", "Auto Drive", "505 Motorway", "Autonomous Vehicles, AI", "Automotive", "600890123", 220, 2022));
        empresas.add(new Empresa("CIF009", "BioGen", "606 Genome Pl", "Genomics, Biotech", "Biotechnology", "600901234", 130, 2021));
        empresas.add(new Empresa("CIF010", "Urban Plan", "707 Metro Ave", "Smart Cities, IoT", "Urban Planning", "600012345", 140, 2023));

        try {
            modificaEmpresa(new Empresa("CIF004", "modificada", "modificada St", "mod", "Education", "600456789", 180, 2023));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println(empresas.toString());*/

        cargaContenidoEmpresas();
        System.out.println(empresas.toString());
    }


}

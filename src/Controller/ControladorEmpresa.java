package Controller;

import Model.Empresa;
import View.PanelConsultaEmpresaTutor;

import java.sql.*;
import java.util.ArrayList;

public class ControladorEmpresa {
    public static ArrayList<Empresa> empresas = new ArrayList<>();

    public static void cargaContenidoEmpresas() throws SQLException {
        empresas.clear();
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
    public static void modificaEmpresa(String cifModificar, String cif, String nombre, String direccion, String tecnologias, String sector, String telefono, int num_empleados, int ult_anio_colab) throws SQLException {
        cargaContenidoEmpresas();
        Empresa empresaMod = new Empresa(cif,nombre,direccion,tecnologias,sector,telefono,num_empleados,ult_anio_colab);
        for (int i=0;i<empresas.size();i++){
            if (empresas.get(i).getCIF().equals(cifModificar)){
                empresas.set(i,empresaMod);
                break;
            }
        }
        String sql = "UPDATE EMPRESA SET cif = ?, nombre = ?, direccion = ?, tecnologias = ?, sector = ?, telefono = ?, num_empleados = ?, ult_anio_colab = ? WHERE cif = ?";
        PreparedStatement ps = ControladorConexion.miConexion.prepareStatement(sql);
        ps.setString(1, cif);
        ps.setString(2, nombre);
        ps.setString(3, direccion);
        ps.setString(4, tecnologias);
        ps.setString(5, sector);
        ps.setString(6, telefono);
        ps.setInt(7, num_empleados);
        ps.setInt(8, ult_anio_colab);
        ps.setString(9, cifModificar);
        ps.executeUpdate();
        ps.close();
    }
    public static void agregaEmpresa(String cif, String nombre, String direccion, String tecnologias, String sector, String telefono, int num_empleados, int ult_anio_colab, PanelConsultaEmpresaTutor panel) {
        Empresa nuevaEmpresa = new Empresa(cif, nombre, direccion, tecnologias, sector, telefono, num_empleados, ult_anio_colab);
        String sql = "INSERT INTO EMPRESA (cif, nombre, direccion, tecnologias, sector, telefono, num_empleados, ult_anio_colab) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = ControladorConexion.miConexion.prepareStatement(sql);
            ps.setString(1, cif);
            ps.setString(2, nombre);
            ps.setString(3, direccion);
            ps.setString(4, tecnologias);
            ps.setString(5, sector);
            ps.setString(6, telefono);
            ps.setInt(7, num_empleados);
            ps.setInt(8, ult_anio_colab);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        empresas.add(nuevaEmpresa);
        panel.comboEmpresa.addItem(nuevaEmpresa);
    }
    public static void eliminaEmpresa(String cifEliminar) throws SQLException {
        for (int i=0;i<empresas.size();i++){
            if (empresas.get(i).getCIF().equals(cifEliminar)){
                empresas.remove(i);
                break;
            }
        }
        String sql = "DELETE FROM EMPRESA WHERE cif = ?";
        PreparedStatement ps = ControladorConexion.miConexion.prepareStatement(sql);
        ps.setString(1, cifEliminar);
        ps.executeUpdate();
        ps.close();
    }
/*
    public static void main(String[] args) throws SQLException {
        cargaContenidoEmpresas();
        System.out.println(empresas.toString());
        //agregaEmpresa("prueba","prueba","prueba", "prueba", "prueba", "prueba", 1, 1);
        //modificaEmpresa("prueba","pruebaMODIFICADA", "", "", "", "", "", 0, 0);
        eliminaEmpresa("CIF010");
    }*/


}

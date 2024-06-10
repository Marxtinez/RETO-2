package Controller;
import Model.Empresa_Contacta_Tutor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ControladorHistorialContactos {
    public static ArrayList<Empresa_Contacta_Tutor> historialContacto=new ArrayList<>();

    public static void ObtenerHitorialContactos() throws SQLException{
        historialContacto.clear();
        Statement statement=ControladorConexion.miConexion.createStatement();
        ResultSet rs_scriptObtHistorial= statement.executeQuery("SELECT * FROM empresa_contacta_tutor");
        while (rs_scriptObtHistorial.next()) {
            historialContacto.add(new Empresa_Contacta_Tutor(
                    rs_scriptObtHistorial.getInt("id_tutor"),
                    rs_scriptObtHistorial.getString("CIF"),
                    rs_scriptObtHistorial.getString("fecha")));
        }
        statement.close();
    }
    public static void actualizarHistorialContacto(int id_tutorModificar,int id_tutor,String CIF,String fecha) throws SQLException{
        ObtenerHitorialContactos();
        Empresa_Contacta_Tutor nuevoHistorial= new Empresa_Contacta_Tutor(id_tutor, CIF, fecha);
        for (int i = 0; i < historialContacto.size(); i++) {
            if (historialContacto.get(i).getId_tutor()==id_tutorModificar){
                historialContacto.set(i,nuevoHistorial);
                break;
            }
        }
        String scriptActHistorialContacto="UPDATE empresa_contacta_tutor SET id_tutor=?, CIF=?, fecha=? WHERE id_tutor=?";
        try(PreparedStatement statement=ControladorConexion.miConexion.prepareStatement(scriptActHistorialContacto)){
            statement.setInt(1,id_tutor);
            statement.setString(2,CIF);
            statement.setString(3,fecha);
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public static void eliminarHistorialContacto(int id_HistorialEliminar)throws SQLException{
        for (int i = 0; i < historialContacto.size(); i++) {
            if (historialContacto.get(i).getId_tutor()==id_HistorialEliminar){
                historialContacto.remove(i);
                break;
            }
        }
        String scriptEliminarHistorialContacto= "DELETE FROM empresa_contacta_tutor WHERE id_tutor=?";
        try (PreparedStatement statement=ControladorConexion.miConexion.prepareStatement(scriptEliminarHistorialContacto)){
            statement.setInt(1,id_HistorialEliminar);
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public static void insertarHistoriaContacto(int idTutor, String CIF, String fecha) throws SQLException {
        Empresa_Contacta_Tutor nuevaHistoria = new Empresa_Contacta_Tutor(idTutor, CIF, fecha);
        String scriptInsertarHistorialContacto = "INSERT INTO empresa_contacta_tutor (id_tutor, CIF, fecha) VALUES (?, ?, ?)";
        try (PreparedStatement ps = ControladorConexion.miConexion.prepareStatement(scriptInsertarHistorialContacto)) {
            ps.setInt(1, idTutor);
            ps.setString(2, CIF);
            ps.setString(3, fecha);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // Obtener historias de contacto por empresa
    public static List<Empresa_Contacta_Tutor> obtenerHistoriasContactoPorEmpresa(String CIF) throws SQLException {
        List<Empresa_Contacta_Tutor> historialPorEmpresa = new ArrayList<>();
        String scriptObtenerHistorialContacto = "SELECT * FROM HISTORIA_CONTACTO WHERE CIF = ?";
        try (PreparedStatement ps = ControladorConexion.miConexion.prepareStatement(scriptObtenerHistorialContacto)) {
            ps.setString(1, CIF);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Empresa_Contacta_Tutor historia = new Empresa_Contacta_Tutor(
                        rs.getInt("id_tutor"),
                        rs.getString("CIF"),
                        rs.getString("fecha")
                );
                historialPorEmpresa.add(historia);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return historialPorEmpresa;
    }

}

package Controller;

import Model.Incidencia;
import View.PanelConsultaIncidenciaTutor;

import java.sql.*;
import java.util.ArrayList;

public class ControladorIncidencia {
    public static ArrayList<Incidencia> incidencias = new ArrayList<>();

    public static void cargaContenidoIncidencias() throws SQLException {
        Statement st = ControladorConexion.miConexion.createStatement();
        ResultSet rs_sqlLeerIncidencias = st.executeQuery("SELECT * FROM INCIDENCIA");
        while (rs_sqlLeerIncidencias.next()) {
            incidencias.add(new Incidencia(
                    rs_sqlLeerIncidencias.getInt("id_incidencia"),
                    rs_sqlLeerIncidencias.getString("descripcion"),
                    rs_sqlLeerIncidencias.getString("fecha"),
                    rs_sqlLeerIncidencias.getString("CIF")
            ));
        }
        st.close();
    }

    public static void modificaIncidencia(int idIncidenciaModificar, String descripcion, String fecha, String CIF) throws SQLException {
        cargaContenidoIncidencias();

        String sql = "UPDATE INCIDENCIA SET descripcion = ?, fecha = ?, CIF = ? WHERE id_incidencia = ?";
        PreparedStatement ps = ControladorConexion.miConexion.prepareStatement(sql);
        ps.setString(1, descripcion);
        ps.setDate(2, Date.valueOf(fecha));
        ps.setString(3, CIF);
        ps.setInt(4, idIncidenciaModificar);
        ps.executeUpdate();
        ps.close();

        Incidencia incidenciaMod = new Incidencia(idIncidenciaModificar, descripcion, fecha, CIF);
        for (int i = 0; i < incidencias.size(); i++) {
            if (incidencias.get(i).getId_incidencia() == idIncidenciaModificar) {
                incidencias.set(i, incidenciaMod);
                break;
            }
        }
    }

    public static void agregaIncidencia(String descripcion, String fecha, String CIF, PanelConsultaIncidenciaTutor panel) {
        String sql = "INSERT INTO INCIDENCIA (descripcion, fecha, CIF) VALUES (?, ?, ?)";
        try {
            PreparedStatement ps = ControladorConexion.miConexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, descripcion);
            ps.setDate(2, Date.valueOf(fecha));
            ps.setString(3, CIF);
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            int id_incidencia = -1;
            if (rs.next()) {
                id_incidencia = rs.getInt(1);
            }

            Incidencia nuevaIncidencia = new Incidencia(id_incidencia, descripcion, fecha, CIF);
            incidencias.add(nuevaIncidencia);
            panel.comboIncidencia.addItem(nuevaIncidencia);
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Método para eliminar un registro Incidencia
    public static void eliminaIncidencia(int id_incidencia) throws SQLException {
        for (int i = 0; i < incidencias.size(); i++) {
            if (incidencias.get(i).getId_incidencia() == id_incidencia) {
                incidencias.remove(i);
                break;
            }
        }
        String sql = "DELETE FROM INCIDENCIA WHERE id_incidencia = ?";
        PreparedStatement ps = ControladorConexion.miConexion.prepareStatement(sql);
        ps.setInt(1, id_incidencia);
        ps.executeUpdate();
        ps.close();
    }

    public static int siguienteValor() throws SQLException {
        int id = 0;
        Statement st = ControladorConexion.miConexion.createStatement();
        ResultSet rs = st.executeQuery("SELECT last_value FROM public.incidencia_id_incidencia_seq");

        while (rs.next()){
            id = rs.getInt(1);
        }

        rs.close();
        st.close();
        return id;
    }

/*
    public static void main(String[] args) throws SQLException {
        cargaContenidoIncidencias();
        System.out.println(incidencias.toString());
        //agregaIncidencia("PRUEBA", "2024-06-08", "CIF001");
        //modificaIncidencia(21, "Incidencia modificada", "2024-06-08", "CIF002");
        //eliminaIncidencia(21);
    }

 */

}


package Controller;

import Model.FCT;
import View.PanelConsultaFCTTutor;

import java.sql.*;
import java.util.ArrayList;

public class ControladorFCT {
    public static ArrayList<FCT> fcts = new ArrayList<>();

    public static void cargaContenidoFCTs() throws SQLException {
        fcts.clear();
        Statement st = ControladorConexion.miConexion.createStatement();
        ResultSet rs_sqlLeerFCTs = st.executeQuery("SELECT * FROM GRUPO_REALIZA_EMPRESA");
        while (rs_sqlLeerFCTs.next()) {
            fcts.add(new FCT(
                    rs_sqlLeerFCTs.getString("id_grupo"),
                    rs_sqlLeerFCTs.getString("CIF"),
                    rs_sqlLeerFCTs.getString("curso"),
                    rs_sqlLeerFCTs.getInt("cant_alumnos")
            ));
        }
        st.close();
    }

    public static void modificaFCT(String idGrupoModificar, String CIFModificar, String cursoModificar, String id_grupo, String CIF, String curso, int num_alumnos,PanelConsultaFCTTutor panel) throws SQLException {
        cargaContenidoFCTs();
        FCT fctMod = new FCT(id_grupo, CIF, curso, num_alumnos);
        for (int i = 0; i < fcts.size(); i++) {
            FCT currentFCT = fcts.get(i);
            if (currentFCT.getId_grupo().equals(idGrupoModificar) && currentFCT.getCIF().equals(CIFModificar) && currentFCT.getCurso().equals(cursoModificar)) {
                fcts.set(i, fctMod);
                panel.comboFCT.removeItemAt(i);
                panel.comboFCT.addItem(fctMod);
                break;
            }
        }
        String sql = "UPDATE GRUPO_REALIZA_EMPRESA SET id_grupo = ?, CIF = ?, curso = ?, cant_alumnos = ? WHERE id_grupo = ? AND CIF = ? AND curso = ?";
        PreparedStatement ps = ControladorConexion.miConexion.prepareStatement(sql);
        ps.setString(1, id_grupo);
        ps.setString(2, CIF);
        ps.setString(3, curso);
        ps.setInt(4, num_alumnos);
        ps.setString(5, idGrupoModificar);
        ps.setString(6, CIFModificar);
        ps.setString(7, cursoModificar);
        ps.executeUpdate();
        ps.close();
    }
    public static void agregaFCT(String id_grupo, String CIF, String curso, int num_alumnos, PanelConsultaFCTTutor panel) {
        FCT nuevoFCT = new FCT(id_grupo, CIF, curso, num_alumnos);
        String sql = "INSERT INTO GRUPO_REALIZA_EMPRESA (id_grupo, CIF, curso, cant_alumnos) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement ps = ControladorConexion.miConexion.prepareStatement(sql);
            ps.setString(1, id_grupo);
            ps.setString(2, CIF);
            ps.setString(3, curso);
            ps.setInt(4, num_alumnos);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        fcts.add(nuevoFCT);
        panel.comboFCT.addItem(nuevoFCT);
    }

    public static void eliminaFCT(String id_grupo, String CIF, String curso) throws SQLException {
        for (int i = 0; i < fcts.size(); i++) {
            FCT currentFCT = fcts.get(i);
            if (currentFCT.getId_grupo().equals(id_grupo) && currentFCT.getCIF().equals(CIF) && currentFCT.getCurso().equals(curso)) {
                fcts.remove(i);
                break;
            }
        }
        String sql = "DELETE FROM GRUPO_REALIZA_EMPRESA WHERE id_grupo = ? AND CIF = ? AND curso = ?";
        PreparedStatement ps = ControladorConexion.miConexion.prepareStatement(sql);
        ps.setString(1, id_grupo);
        ps.setString(2, CIF);
        ps.setString(3, curso);
        ps.executeUpdate();
        ps.close();
    }
}

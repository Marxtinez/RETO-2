package Controller;

import Model.TutorFCT;
import View.PanelAgregarTutor;
import View.PanelConsultaTutoresTutor;

import java.sql.*;
import java.util.ArrayList;

public class ControladorTutorFCT {
    public static ArrayList<TutorFCT> tutores = new ArrayList<>();

    public static void cargaContenidoTutores() throws SQLException {
        tutores.clear();
        Statement st = ControladorConexion.miConexion.createStatement();
        ResultSet rs_sqlLeerTutores = st.executeQuery("SELECT * FROM TUTORFCT");
        while (rs_sqlLeerTutores.next()) {
            tutores.add(new TutorFCT(
                    rs_sqlLeerTutores.getInt("id_tutor"),
                    rs_sqlLeerTutores.getString("email"),
                    rs_sqlLeerTutores.getString("nombre"),
                    rs_sqlLeerTutores.getString("telefono")
            ));
        }
        st.close();
    }

    public static void modificaTutor(int idModificar, String email, String nombre, String telefono,PanelConsultaTutoresTutor panel) throws SQLException {
        cargaContenidoTutores();
        TutorFCT tutorMod = new TutorFCT(idModificar, email, nombre, telefono);
        for (int i = 0; i < tutores.size(); i++) {
            if (tutores.get(i).getId_tutor() == idModificar) {
                tutores.set(i, tutorMod);
                panel.comboTutores.removeItemAt(i);
                panel.comboTutores.addItem(tutorMod);
                break;
            }
        }
        String sql = "UPDATE TUTORFCT SET email = ?, nombre = ?, telefono = ? WHERE id_tutor = ?";
        PreparedStatement ps = ControladorConexion.miConexion.prepareStatement(sql);
        ps.setString(1, email);
        ps.setString(2, nombre);
        ps.setString(3, telefono);
        ps.setInt(4, idModificar);
        ps.executeUpdate();
        ps.close();
    }

    public static void agregaTutor(String email, String nombre, String telefono, PanelConsultaTutoresTutor panel) {
        String sql = "INSERT INTO TUTORFCT (email, nombre, telefono) VALUES (?, ?, ?)";
        try {
            PreparedStatement ps = ControladorConexion.miConexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, email);
            ps.setString(2, nombre);
            ps.setString(3, telefono);
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int id_tutor = rs.getInt(1);
                TutorFCT nuevoTutor = new TutorFCT(id_tutor, email, nombre, telefono);
                tutores.add(nuevoTutor);
                panel.comboTutores.addItem(nuevoTutor);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void eliminaTutor(int idEliminar) throws SQLException {
        for (int i = 0; i < tutores.size(); i++) {
            if (tutores.get(i).getId_tutor() == idEliminar) {
                tutores.remove(i);
                break;
            }
        }
        String sql = "DELETE FROM TUTORFCT WHERE id_tutor = ?";
        PreparedStatement ps = ControladorConexion.miConexion.prepareStatement(sql);
        ps.setInt(1, idEliminar);
        ps.executeUpdate();
        ps.close();
    }

    public static int siguienteValor() throws SQLException {
        int id = 0;
        Statement st = ControladorConexion.miConexion.createStatement();
        ResultSet rs = st.executeQuery("SELECT last_value FROM public.tutorfct_id_tutor_seq");

        while (rs.next()){
            id = rs.getInt(1);
        }

        rs.close();
        st.close();
        return id;
    }
}

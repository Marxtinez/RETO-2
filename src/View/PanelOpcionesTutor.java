package View;

import Controller.ControladorPanelPrincipal;
import Model.Idioma;

import javax.swing.*;
import java.sql.SQLException;

public class PanelOpcionesTutor extends JPanel {
    JButton btnEmpresa;
    public PanelOpcionesTutor() throws SQLException {
        Idioma idioma = new Idioma(Idioma.spanish);

        btnEmpresa = new JButton(idioma.getProperty("empresa"));

        btnEmpresa.addActionListener(e-> {
            try {
                ControladorPanelPrincipal.nuevoPanelActivo(new PanelConsultaEmpresaTutor());
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        add(btnEmpresa);
    }
}

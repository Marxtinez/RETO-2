package View;

import Controller.ControladorPanelPrincipal;
import Model.Idioma;

import javax.swing.*;
import java.sql.SQLException;

public class PanelOpcionesTutor extends JPanel {
    public static PanelConsultaEmpresaTutor panelConsultaEmpresaTutor;
    public static PanelConsultaFCTTutor panelConsultaFCTTutor;

    static {
        try {
            panelConsultaEmpresaTutor = new PanelConsultaEmpresaTutor();
            panelConsultaFCTTutor = new PanelConsultaFCTTutor();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    JButton btnEmpresa,btnFCT;
    public PanelOpcionesTutor() throws SQLException {
        Idioma idioma = new Idioma(Idioma.spanish);

        btnEmpresa = new JButton(idioma.getProperty("empresa"));
        btnFCT = new JButton(idioma.getProperty("fct"));

        btnEmpresa.addActionListener(e-> {
                ControladorPanelPrincipal.nuevoPanelActivo(panelConsultaEmpresaTutor);
        });
        btnFCT.addActionListener(e-> ControladorPanelPrincipal.nuevoPanelActivo(panelConsultaFCTTutor));

        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        add(btnEmpresa);
        add(btnFCT);
    }
}

package View;

import Controller.ControladorPanelPrincipal;
import Model.Idioma;

import javax.swing.*;
import java.sql.SQLException;

public class PanelOpcionesTutor extends JPanel {
    public static PanelConsultaEmpresaTutor panelConsultaEmpresaTutor;

    static {
        try {
            panelConsultaEmpresaTutor = new PanelConsultaEmpresaTutor();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    JButton btnEmpresa;
    public PanelOpcionesTutor() throws SQLException {
        Idioma idioma = new Idioma(Idioma.spanish);

        btnEmpresa = new JButton(idioma.getProperty("empresa"));

        btnEmpresa.addActionListener(e-> {
                ControladorPanelPrincipal.nuevoPanelActivo(panelConsultaEmpresaTutor);
        });

        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        add(btnEmpresa);
    }
}

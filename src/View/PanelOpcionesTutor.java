package View;

import Controller.ControladorPanelPrincipal;
import Model.Idioma;

import javax.swing.*;

public class PanelOpcionesTutor extends JPanel {
    JButton btnEmpresa;
    PanelConsultaEmpresaTutor panelConsultaEmpresaTutor = new PanelConsultaEmpresaTutor();
    public PanelOpcionesTutor() {
        Idioma idioma = new Idioma(Idioma.spanish);

        btnEmpresa = new JButton(idioma.getProperty("empresa"));

        btnEmpresa.addActionListener(e-> ControladorPanelPrincipal.nuevoPanelActivo(panelConsultaEmpresaTutor));

        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        add(btnEmpresa);
    }
}

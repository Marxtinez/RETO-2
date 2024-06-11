package View;

import Controller.ControladorPanelPrincipal;
import Model.Idioma;

import javax.swing.*;
import java.sql.SQLException;

public class PanelOpcionesProf extends JPanel {
    //public static PanelConsultaEmpresaTutor panelConsultaEmpresaTutor;
    //public static PanelConsultaFCTTutor panelConsultaFCTTutor;
    //public static PanelConsultaTutoresTutor panelConsultaTutoresTutor;
    //public static PanelConsultaTrabajadorTutor panelConsultaTrabajadorTutor;

    /*static {
        try {
            //panelConsultaEmpresaTutor = new PanelConsultaEmpresaTutor();
            //panelConsultaFCTTutor = new PanelConsultaFCTTutor();
            //panelConsultaTutoresTutor = new PanelConsultaTutoresTutor();
            //panelConsultaTrabajadorTutor = new PanelConsultaTrabajadorTutor();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }*/

    JButton btnAtras,btnEmpresa,btnFCT,btnTutores,btnTrabajador;
    public PanelOpcionesProf() throws SQLException {
        Idioma idioma = new Idioma(Idioma.spanish);

        btnAtras = new JButton(idioma.getProperty("atras"));
        btnEmpresa = new JButton(idioma.getProperty("empresa"));
        btnFCT = new JButton(idioma.getProperty("fct"));
        btnTutores = new JButton(idioma.getProperty("tutor"));
        btnTrabajador = new JButton(idioma.getProperty("trabajador"));

        btnAtras.addActionListener(e-> ControladorPanelPrincipal.panelAntiguo());
        /*btnEmpresa.addActionListener(e-> ControladorPanelPrincipal.nuevoPanelActivo(panelConsultaEmpresaTutor));
        btnFCT.addActionListener(e-> ControladorPanelPrincipal.nuevoPanelActivo(panelConsultaFCTTutor));
        btnTutores.addActionListener(e-> ControladorPanelPrincipal.nuevoPanelActivo(panelConsultaTutoresTutor));
        btnTrabajador.addActionListener(e-> ControladorPanelPrincipal.nuevoPanelActivo(panelConsultaTrabajadorTutor));*/

        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

        add(btnEmpresa);
        add(btnFCT);
        add(btnTutores);
        add(btnTrabajador);
        add(btnAtras);
    }
}
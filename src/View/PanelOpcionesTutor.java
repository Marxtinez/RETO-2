package View;

import Controller.ControladorIncidencia;
import Controller.ControladorPanelPrincipal;
import Controller.ControladorTutorFCT;
import Model.Idioma;

import javax.swing.*;
import java.sql.SQLException;

public class PanelOpcionesTutor extends JPanel {
    public static PanelConsultaEmpresaTutor panelConsultaEmpresaTutor;
    public static PanelConsultaFCTTutor panelConsultaFCTTutor;
    public static PanelConsultaTutoresTutor panelConsultaTutoresTutor;
    public static PanelConsultaTrabajadorTutor panelConsultaTrabajadorTutor;
    public static PanelConsultaIncidenciaTutor panelConsultaIncidenciaTutor;

    static {
        try {
            panelConsultaEmpresaTutor = new PanelConsultaEmpresaTutor();
            panelConsultaFCTTutor = new PanelConsultaFCTTutor();
            panelConsultaTutoresTutor = new PanelConsultaTutoresTutor();
            panelConsultaTrabajadorTutor = new PanelConsultaTrabajadorTutor();
            panelConsultaIncidenciaTutor = new PanelConsultaIncidenciaTutor();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    JButton btnAtras,btnEmpresa,btnFCT,btnTutores,btnTrabajador,btnIncidencia;
    public PanelOpcionesTutor() throws SQLException {
        Idioma idioma = new Idioma(Idioma.spanish);

        btnAtras = new JButton(idioma.getProperty("atras"));
        btnEmpresa = new JButton(idioma.getProperty("empresa"));
        btnFCT = new JButton(idioma.getProperty("fct"));
        btnTutores = new JButton(idioma.getProperty("tutor"));
        btnTrabajador = new JButton(idioma.getProperty("trabajador"));
        btnIncidencia = new JButton(idioma.getProperty("incidencia"));

        btnAtras.addActionListener(e-> ControladorPanelPrincipal.panelAntiguo());
        btnEmpresa.addActionListener(e-> ControladorPanelPrincipal.nuevoPanelActivo(panelConsultaEmpresaTutor));
        btnFCT.addActionListener(e-> ControladorPanelPrincipal.nuevoPanelActivo(panelConsultaFCTTutor));
        btnTutores.addActionListener(e-> ControladorPanelPrincipal.nuevoPanelActivo(panelConsultaTutoresTutor));
        btnTrabajador.addActionListener(e-> ControladorPanelPrincipal.nuevoPanelActivo(panelConsultaTrabajadorTutor));
        btnIncidencia.addActionListener(e-> ControladorPanelPrincipal.nuevoPanelActivo(panelConsultaIncidenciaTutor));

        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

        add(btnEmpresa);
        add(btnFCT);
        add(btnTutores);
        add(btnTrabajador);
        add(btnIncidencia);
        add(btnAtras);
    }
}

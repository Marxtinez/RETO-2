package View;

import Controller.ControladorIncidencia;
import Controller.ControladorPanelPrincipal;
import Controller.ControladorTutorFCT;
import Model.Idioma;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class PanelOpcionesTutor extends JPanel {
    public static PanelConsultaEmpresaTutor panelConsultaEmpresaTutor;
    public static PanelConsultaFCTTutor panelConsultaFCTTutor;
    public static PanelConsultaTutoresTutor panelConsultaTutoresTutor;
    public static PanelConsultaTrabajadorTutor panelConsultaTrabajadorTutor;
    public static PanelConsultaIncidenciaTutor panelConsultaIncidenciaTutor;
    public static PanelConsultasEspecificas panelConsultasEspecificas;

    static {
        try {
            panelConsultaEmpresaTutor = new PanelConsultaEmpresaTutor();
            panelConsultaFCTTutor = new PanelConsultaFCTTutor();
            panelConsultaTutoresTutor = new PanelConsultaTutoresTutor();
            panelConsultaTrabajadorTutor = new PanelConsultaTrabajadorTutor();
            panelConsultaIncidenciaTutor = new PanelConsultaIncidenciaTutor();
            panelConsultasEspecificas = new PanelConsultasEspecificas();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    JButton btnAtras,btnEmpresa,btnFCT,btnTutores,btnTrabajador,btnIncidencia,btnConsultas;
    public PanelOpcionesTutor() throws SQLException {
        Idioma idioma = new Idioma(Idioma.spanish);

        btnAtras = new JButton(idioma.getProperty("atras"));
        btnEmpresa = new JButton(idioma.getProperty("empresa"));
        btnFCT = new JButton(idioma.getProperty("fct"));
        btnTutores = new JButton(idioma.getProperty("tutor"));
        btnTrabajador = new JButton(idioma.getProperty("trabajador"));
        btnIncidencia = new JButton(idioma.getProperty("incidencia"));
        btnConsultas = new JButton(idioma.getProperty("consultas"));

        btnConsultas.setAlignmentX(CENTER_ALIGNMENT);
        btnAtras.setAlignmentX(CENTER_ALIGNMENT);
        btnIncidencia.setAlignmentX(CENTER_ALIGNMENT);
        btnFCT.setAlignmentX(CENTER_ALIGNMENT);
        btnTrabajador.setAlignmentX(CENTER_ALIGNMENT);
        btnEmpresa.setAlignmentX(CENTER_ALIGNMENT);
        btnTutores.setAlignmentX(CENTER_ALIGNMENT);

        Dimension btnDimension = new Dimension(200,25);

        btnTutores.setMaximumSize(btnDimension);
        btnTrabajador.setMaximumSize(btnDimension);
        btnConsultas.setMaximumSize(btnDimension);
        btnEmpresa.setMaximumSize(btnDimension);
        btnFCT.setMaximumSize(btnDimension);
        btnIncidencia.setMaximumSize(btnDimension);


        btnAtras.addActionListener(e-> ControladorPanelPrincipal.panelAntiguo());
        btnEmpresa.addActionListener(e-> ControladorPanelPrincipal.nuevoPanelActivo(panelConsultaEmpresaTutor));
        btnFCT.addActionListener(e-> ControladorPanelPrincipal.nuevoPanelActivo(panelConsultaFCTTutor));
        btnTutores.addActionListener(e-> ControladorPanelPrincipal.nuevoPanelActivo(panelConsultaTutoresTutor));
        btnTrabajador.addActionListener(e-> ControladorPanelPrincipal.nuevoPanelActivo(panelConsultaTrabajadorTutor));
        btnIncidencia.addActionListener(e-> ControladorPanelPrincipal.nuevoPanelActivo(panelConsultaIncidenciaTutor));
        btnConsultas.addActionListener(e-> ControladorPanelPrincipal.nuevoPanelActivo(panelConsultasEspecificas));

        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

        add(Box.createVerticalStrut(20));
        add(btnEmpresa);
        add(Box.createVerticalStrut(10));
        add(btnFCT);
        add(Box.createVerticalStrut(10));
        add(btnTutores);
        add(Box.createVerticalStrut(10));
        add(btnTrabajador);
        add(Box.createVerticalStrut(10));
        add(btnIncidencia);
        add(Box.createVerticalStrut(10));
        add(btnConsultas);
        add(Box.createVerticalStrut(30));
        add(btnAtras);
    }
}

package View;

import Controller.ControladorFCT;
import Controller.ControladorPanelPrincipal;
import Model.Idioma;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class PanelModificarFCT extends JPanel {
    JTextField txtGrupo,txtCif,txtCurso,txtAlumnos;
    JLabel lblGrupo,lblCif,lblCurso,lblAlumno,lblResultado;
    JButton btnMod,btnAtras;
    public PanelModificarFCT() {
        Idioma idioma = new Idioma(PanelConfiguracion.comboIdioma.getSelectedIndex());
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        btnAtras = new JButton(idioma.getProperty("atras"));
        btnMod = new JButton(idioma.getProperty("modificar"));

        lblAlumno = new JLabel(idioma.getProperty("alumnos"));
        lblCurso = new JLabel(idioma.getProperty("curso"));
        lblGrupo = new JLabel(idioma.getProperty("grupo"));
        lblCif = new JLabel(idioma.getProperty("cif"));

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new BoxLayout(panelBotones,BoxLayout.X_AXIS));

        JPanel panelForm = new JPanel();
        panelForm.setLayout(new BoxLayout(panelForm,BoxLayout.X_AXIS));

        JPanel panelIzq = new JPanel();
        panelIzq.setLayout(new BoxLayout(panelIzq,BoxLayout.Y_AXIS));

        txtAlumnos = new JTextField();
        txtCurso = new JTextField();
        txtGrupo = new JTextField();
        txtCif = new JTextField();

        lblResultado = new JLabel();
        lblResultado.setAlignmentX(CENTER_ALIGNMENT);

        btnAtras.addActionListener(e-> ControladorPanelPrincipal.panelAntiguo());
        btnMod.addActionListener(e->{
            try {
                ControladorFCT.modificaFCT(PanelOpcionesTutor.panelConsultaFCTTutor.grupoMod,PanelOpcionesTutor.panelConsultaFCTTutor.cifMod,PanelOpcionesTutor.panelConsultaFCTTutor.cursoMod,txtGrupo.getText(),txtCif.getText(),txtCurso.getText(),Integer.valueOf(txtAlumnos.getText()),PanelOpcionesTutor.panelConsultaFCTTutor);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            lblResultado.setText("Se ha modificado la FCT correctamente");
            Timer timer = new Timer(2500, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    lblResultado.setText("");
                }
            });
            timer.setRepeats(false);
            timer.start();
        });

        panelBotones.add(btnMod);
        panelBotones.add(Box.createHorizontalStrut(10));
        panelBotones.add(btnAtras);

        add(Box.createVerticalStrut(35));
        panelIzq.add(lblGrupo);
        panelIzq.add(txtGrupo);
        panelIzq.add(Box.createVerticalStrut(10));
        panelIzq.add(lblCif);
        panelIzq.add(txtCif);
        panelIzq.add(Box.createVerticalStrut(10));
        panelIzq.add(lblCurso);
        panelIzq.add(txtCurso);
        panelIzq.add(Box.createVerticalStrut(10));
        panelIzq.add(lblAlumno);
        panelIzq.add(txtAlumnos);

        panelForm.add(panelIzq);
        add(panelForm);
        add(Box.createVerticalStrut(10));
        add(lblResultado);
        add(Box.createVerticalStrut(10));
        add(panelBotones);

    }
}

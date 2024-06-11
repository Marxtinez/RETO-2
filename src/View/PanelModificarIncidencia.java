package View;

import Controller.ControladorIncidencia;
import Controller.ControladorPanelPrincipal;
import Controller.ControladorTutorFCT;
import Model.Idioma;
import Model.Incidencia;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class PanelModificarIncidencia extends JPanel {
    JTextField txtIdIncidencia, txtCif, txtDescripcion, txtFecha;
    JLabel lblIdIncidencia, lblCif, lblDescripcion, lblFecha,lblResultado;
    JButton btnMod,btnAtras;
    public PanelModificarIncidencia() throws SQLException {
        Idioma idioma = new Idioma(Idioma.spanish);

        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        btnAtras = new JButton(idioma.getProperty("atras"));
        btnMod = new JButton(idioma.getProperty("modificar"));

        lblFecha = new JLabel(idioma.getProperty("fecha"));
        lblDescripcion = new JLabel(idioma.getProperty("descrip"));
        lblIdIncidencia = new JLabel(idioma.getProperty("idincidencia"));
        lblCif = new JLabel(idioma.getProperty("cif"));

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new BoxLayout(panelBotones,BoxLayout.X_AXIS));

        JPanel panelFormX = new JPanel();
        panelFormX.setLayout(new BoxLayout(panelFormX,BoxLayout.X_AXIS));

        JPanel panelFormY = new JPanel();
        panelFormY.setLayout(new BoxLayout(panelFormY,BoxLayout.Y_AXIS));

        txtFecha = new JTextField();
        txtDescripcion = new JTextField();
        txtIdIncidencia = new JTextField();
        txtCif = new JTextField();

        txtIdIncidencia.setEditable(false);

        lblResultado = new JLabel();
        lblResultado.setAlignmentX(CENTER_ALIGNMENT);

        btnAtras.addActionListener(e-> ControladorPanelPrincipal.panelAntiguo());
        btnMod.addActionListener(e->{
            try {
                ControladorIncidencia.modificaIncidencia(Integer.valueOf(txtIdIncidencia.getText()),txtDescripcion.getText(),txtFecha.getText(),txtCif.getText(),PanelOpcionesTutor.panelConsultaIncidenciaTutor);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            lblResultado.setText("Se ha modificado la incidencia correctamente");
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
        panelFormY.add(lblIdIncidencia);
        panelFormY.add(txtIdIncidencia);
        panelFormY.add(Box.createVerticalStrut(10));
        panelFormY.add(lblCif);
        panelFormY.add(txtCif);
        panelFormY.add(Box.createVerticalStrut(10));
        panelFormY.add(lblFecha);
        panelFormY.add(txtFecha);
        panelFormY.add(Box.createVerticalStrut(10));
        panelFormY.add(lblDescripcion);
        panelFormY.add(txtDescripcion);

        panelFormX.add(panelFormY);
        add(panelFormX);
        add(Box.createVerticalStrut(10));
        add(lblResultado);
        add(Box.createVerticalStrut(10));
        add(panelBotones);

    }
}
package View;

import Controller.ControladorIncidencia;
import Controller.ControladorPanelPrincipal;
import Controller.ControladorTutorFCT;
import Model.Idioma;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class PanelAgregarIncidencia extends JPanel {
    JTextField txtIdIncidencia, txtCif, txtDescrip, txtFecha;
    static JLabel lblIdIncidencia, lblCif, lblDescrip, lblFecha,lblResultado;
    static JButton btnAgregar,btnAtras;
    public PanelAgregarIncidencia() throws SQLException {
        Idioma idioma = new Idioma(Idioma.spanish);

        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        btnAtras = new JButton(idioma.getProperty("atras"));
        btnAgregar = new JButton(idioma.getProperty("agregar"));

        lblFecha = new JLabel(idioma.getProperty("fecha"));
        lblDescrip = new JLabel(idioma.getProperty("descrip"));
        lblIdIncidencia = new JLabel(idioma.getProperty("idincidencia"));
        lblCif = new JLabel(idioma.getProperty("cif"));

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new BoxLayout(panelBotones,BoxLayout.X_AXIS));

        JPanel panelFormX = new JPanel();
        panelFormX.setLayout(new BoxLayout(panelFormX,BoxLayout.X_AXIS));

        JPanel panelFormY = new JPanel();
        panelFormY.setLayout(new BoxLayout(panelFormY,BoxLayout.Y_AXIS));

        txtFecha = new JTextField();
        txtDescrip = new JTextField();
        txtIdIncidencia = new JTextField(String.valueOf(ControladorIncidencia.siguienteValor()+1));
        txtCif = new JTextField();

        txtIdIncidencia.setEditable(false);

        lblResultado = new JLabel();
        lblResultado.setAlignmentX(CENTER_ALIGNMENT);

        btnAtras.addActionListener(e-> ControladorPanelPrincipal.panelAntiguo());
        btnAgregar.addActionListener(e->{
            ControladorIncidencia.agregaIncidencia(txtDescrip.getText(),txtFecha.getText(),txtCif.getText(),PanelOpcionesTutor.panelConsultaIncidenciaTutor);
            txtCif.setText("");
            txtFecha.setText("");
            txtDescrip.setText("");
            try {
                txtIdIncidencia.setText(String.valueOf(ControladorIncidencia.siguienteValor()+1));
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            lblResultado.setText("Incidencia añadida correctamente");
            Timer timer = new Timer(2500, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    lblResultado.setText("");
                }
            });
            timer.setRepeats(false);
            timer.start();
        });

        panelBotones.add(btnAgregar);
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
        panelFormY.add(lblDescrip);
        panelFormY.add(txtDescrip);

        panelFormX.add(panelFormY);
        add(panelFormX);
        add(Box.createVerticalStrut(10));
        add(lblResultado);
        add(Box.createVerticalStrut(10));
        add(panelBotones);

    }
    public static void actualizaIdioma(int newLang){
        Idioma idioma = new Idioma(newLang);
        btnAtras.setText(idioma.getProperty("atras"));
        btnAgregar.setText(idioma.getProperty("agregar"));

        lblFecha.setText(idioma.getProperty("fecha"));
        lblDescrip.setText(idioma.getProperty("descrip"));
        lblIdIncidencia.setText(idioma.getProperty("idincidencia"));
        lblCif.setText(idioma.getProperty("cif"));
    }
}
package View;

import Controller.ControladorConexion;
import Controller.ControladorPanelPrincipal;
import Model.Idioma;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelConfiguracion extends JPanel {
    public static JComboBox comboIdioma = new JComboBox<>();
    static JLabel lblURl, lblUsuario, lblPassword;
    JTextField txtUrl,txtNombre,txtPassword;
    static JButton btnAcceder,btnAtras;
    public PanelConfiguracion() {
        Idioma idioma = new Idioma(Idioma.spanish);

        lblURl = new JLabel(idioma.getProperty("url"));
        lblUsuario = new JLabel(idioma.getProperty("usuario"));
        lblPassword = new JLabel(idioma.getProperty("contrasena"));
        txtUrl = new JTextField(ControladorConexion.url);
        txtNombre = new JTextField(ControladorConexion.user);
        txtPassword = new JTextField(ControladorConexion.password);
        btnAcceder = new JButton(idioma.getProperty("acceder"));
        btnAtras = new JButton(idioma.getProperty("atras"));

        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

        comboIdioma.addItem(idioma.getProperty("espanol"));
        comboIdioma.addItem(idioma.getProperty("ingles"));

        Dimension dimensionBtn = new Dimension(400,25);
        comboIdioma.setPreferredSize(dimensionBtn);
        comboIdioma.setMaximumSize(dimensionBtn);

        JPanel panelContenido = new JPanel();
        panelContenido.setLayout(new BoxLayout(panelContenido,BoxLayout.X_AXIS));

        JPanel panelIzquierdo = new JPanel();
        panelIzquierdo.setLayout(new BoxLayout(panelIzquierdo,BoxLayout.Y_AXIS));

        panelIzquierdo.add(lblURl);
        panelIzquierdo.add(txtUrl);
        panelIzquierdo.add(Box.createVerticalStrut(20));
        panelIzquierdo.add(lblUsuario);
        panelIzquierdo.add(txtNombre);
        panelIzquierdo.add(Box.createVerticalStrut(20));
        panelIzquierdo.add(lblPassword);
        panelIzquierdo.add(txtPassword);

        JPanel panelDerecho = new JPanel();
        panelDerecho.setLayout(new BoxLayout(panelDerecho,BoxLayout.Y_AXIS));
        panelDerecho.add(btnAcceder);

        panelContenido.add(panelIzquierdo);
        panelContenido.add(Box.createHorizontalStrut(50));
        panelContenido.add(panelDerecho);

        add(Box.createVerticalStrut(25));
        add(comboIdioma);
        add(Box.createVerticalStrut(50));
        add(panelContenido);
        add(Box.createVerticalStrut(25));
        add(btnAtras);

        btnAtras.addActionListener(e->ControladorPanelPrincipal.panelAntiguo());
        btnAcceder.addActionListener(e-> {
            try {
                ControladorConexion.nuevaConexion(txtUrl.getText(),txtNombre.getText(),txtPassword.getText());
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        comboIdioma.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(PanelAgregarEmpresa.btnAtras != null) {
                    PanelAgregarEmpresa.actualizaIdioma(comboIdioma.getSelectedIndex());
                    PanelAgregarFCT.actualizaIdioma(comboIdioma.getSelectedIndex());
                    PanelAgregarIncidencia.actualizaIdioma(comboIdioma.getSelectedIndex());
                    PanelAgregarTrabajador.actualizaIdioma(comboIdioma.getSelectedIndex());
                    PanelAgregarTutor.actualizaIdioma(comboIdioma.getSelectedIndex());
                    PanelConfiguracion.actualizaIdioma(comboIdioma.getSelectedIndex());


                    //PanelAgregarFCT.actualizaIdioma(comboIdioma.getSelectedIndex());
                    //PanelAgregarFCT.actualizaIdioma(comboIdioma.getSelectedIndex());
                    //PanelAgregarFCT.actualizaIdioma(comboIdioma.getSelectedIndex());
                }else{

                }
            }
        });
    }

    public static void actualizaIdioma(int newLang){
        Idioma idioma = new Idioma(newLang);

        btnAtras.setText(idioma.getProperty("atras"));
        btnAcceder.setText(idioma.getProperty("agregar"));

        lblURl.setText(idioma.getProperty("url"));
        lblUsuario.setText(idioma.getProperty("usuario"));
        lblPassword.setText(idioma.getProperty("contrasena"));
    }

}
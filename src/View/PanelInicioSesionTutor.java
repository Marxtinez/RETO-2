package View;

import Controller.ControladorPanelPrincipal;
import Model.Idioma;

import javax.swing.*;

public class PanelInicioSesionTutor extends JPanel {
    JLabel lblNombre,lblPassword;
    JButton btnAtras,btnEntrar;
    JTextField txtNombre,txtPassword;
    public PanelInicioSesionTutor() {
        Idioma idioma = new Idioma(Idioma.spanish);

        lblNombre = new JLabel(idioma.getProperty("usuario"));
        lblPassword = new JLabel(idioma.getProperty("contraseña"));
        btnAtras = new JButton(idioma.getProperty("atras"));
        btnEntrar = new JButton(idioma.getProperty("acceder"));
        txtNombre = new JTextField();
        txtPassword = new JTextField();

        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new BoxLayout(panelBotones,BoxLayout.X_AXIS));

        panelBotones.add(btnEntrar);
        panelBotones.add(Box.createHorizontalStrut(30));
        panelBotones.add(btnAtras);

        btnAtras.addActionListener(e-> ControladorPanelPrincipal.panelAntiguo());

        add(Box.createVerticalStrut(50));
        add(lblNombre);
        add(txtNombre);
        add(Box.createVerticalStrut(15));
        add(lblPassword);
        add(txtPassword);
        add(Box.createVerticalStrut(25));
        add(panelBotones);

    }
}

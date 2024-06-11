package View;

import Controller.ControladorInicoSesion;
import Controller.ControladorPanelPrincipal;
import Model.Idioma;

import javax.swing.*;
import java.sql.SQLException;

public class PanelInicioSesionProf extends JPanel {
    JLabel lblNombre, lblPassword;
    JButton btnAtras, btnEntrar;
    public JTextField txtNombre, txtPassword;

    public PanelInicioSesionProf() throws SQLException {
        Idioma idioma = new Idioma(Idioma.spanish);

        lblNombre = new JLabel(idioma.getProperty("usuario"));
        lblPassword = new JLabel(idioma.getProperty("contrasena"));
        btnAtras = new JButton(idioma.getProperty("atras"));
        btnEntrar = new JButton(idioma.getProperty("acceder"));
        txtNombre = new JTextField();
        txtPassword = new JTextField();

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel panelFormX = new JPanel();
        panelFormX.setLayout(new BoxLayout(panelFormX, BoxLayout.X_AXIS));

        JPanel panelFormY = new JPanel();
        panelFormY.setLayout(new BoxLayout(panelFormY, BoxLayout.Y_AXIS));

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.X_AXIS));

        panelBotones.add(btnEntrar);
        panelBotones.add(Box.createHorizontalStrut(30));
        panelBotones.add(btnAtras);

        btnAtras.addActionListener(e -> ControladorPanelPrincipal.panelAntiguo());
        btnEntrar.addActionListener(e->ControladorInicoSesion.cambioPanelProf(this));

        txtNombre.setText("Alejandro Breva");
        txtPassword.setText("IIII");


        panelFormY.add(Box.createVerticalStrut(50));
        panelFormY.add(lblNombre);
        panelFormY.add(txtNombre);
        panelFormY.add(Box.createVerticalStrut(15));
        panelFormY.add(lblPassword);
        panelFormY.add(txtPassword);
        panelFormY.add(Box.createVerticalStrut(25));

        panelFormX.add(panelFormY);
        add(panelFormX);
        add(panelBotones);

        btnEntrar.addActionListener(e -> {

        });
    }
}
package View;

import Controller.ControladorEmpresa;
import Controller.ControladorInicoSesion;
import Controller.ControladorPanelPrincipal;
import Controller.ControladorTutorFCT;
import Model.Idioma;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class PanelInicioSesionTutor extends JPanel {
    static JLabel lblNombre,lblPassword;
    static JButton btnAtras,btnEntrar;
    public JTextField txtNombre,txtPassword;
    public PanelInicioSesionTutor() throws SQLException {
        Idioma idioma = new Idioma(PanelConfiguracion.comboIdioma.getSelectedIndex());
        lblNombre = new JLabel(idioma.getProperty("usuario"));
        lblPassword = new JLabel(idioma.getProperty("contrasena"));
        btnAtras = new JButton(idioma.getProperty("atras"));
        btnEntrar = new JButton(idioma.getProperty("acceder"));
        txtNombre = new JTextField();
        txtPassword = new JTextField();

        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

        JPanel panelFormX = new JPanel();
        panelFormX.setLayout(new BoxLayout(panelFormX,BoxLayout.X_AXIS));

        JPanel panelFormY = new JPanel();
        panelFormY.setLayout(new BoxLayout(panelFormY,BoxLayout.Y_AXIS));

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new BoxLayout(panelBotones,BoxLayout.X_AXIS));

        panelBotones.add(btnEntrar);
        panelBotones.add(Box.createHorizontalStrut(30));
        panelBotones.add(btnAtras);

        btnAtras.addActionListener(e-> ControladorPanelPrincipal.panelAntiguo());


        txtNombre.setText("Daniel Garcia");
        txtPassword.setText("EEEE");


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

        btnEntrar.addActionListener(e-> {
            ControladorInicoSesion.cambioPanelTutor(this);
        });
    }
    public static void actualizaIdioma(int newLang){
        Idioma idioma = new Idioma(newLang);

        lblNombre.setText(idioma.getProperty("usuario"));
        lblPassword.setText(idioma.getProperty("contrasena"));
        btnAtras.setText(idioma.getProperty("atras"));
        btnEntrar.setText(idioma.getProperty("acceder"));
    }
}

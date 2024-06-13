package View;

import Controller.ControladorPanelPrincipal;
import Model.Idioma;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class MainFrame extends JFrame {
    public MainFrame() throws SQLException {
        this.setTitle("Gestor FCT");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setBounds(600,200,650,350);
        MainPanel myPanel = new MainPanel();
        this.add(myPanel);
        this.setVisible(true);
    }
}

class MainPanel extends JPanel {
    static JButton btn_tutor, btn_configuracion;
    JPanel master = new JPanel();
    ControladorPanelPrincipal panelPrincipal;
    PanelConfiguracion panelConfiguracion = new PanelConfiguracion();
    PanelInicioSesionTutor panelInicioSesionTutor = new PanelInicioSesionTutor();
    public MainPanel() throws SQLException {
        JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main,BoxLayout.X_AXIS));
        Idioma idioma = new Idioma(Idioma.spanish);

        JPanel center = new JPanel();
        center.setLayout(new BoxLayout(center,BoxLayout.Y_AXIS));
        btn_tutor = new JButton(idioma.getProperty("inicioSesionTutor"));
        btn_configuracion = new JButton(idioma.getProperty("configuracion"));

        Dimension dimensionBtn = new Dimension(300,25);
        btn_tutor.setPreferredSize(dimensionBtn);
        btn_tutor.setMaximumSize(dimensionBtn);
        btn_configuracion.setPreferredSize(dimensionBtn);
        btn_configuracion.setMaximumSize(dimensionBtn);

        panelPrincipal = new ControladorPanelPrincipal(this,master);

        btn_configuracion.addActionListener(e-> panelPrincipal.nuevoPanelActivo(panelConfiguracion));
        btn_tutor.addActionListener(e-> panelPrincipal.nuevoPanelActivo(panelInicioSesionTutor));

        center.add(Box.createVerticalStrut(90));
        center.add(btn_tutor);
        center.add(Box.createVerticalStrut(50));
        center.add(btn_configuracion);
        center.add(Box.createVerticalStrut(90));

        main.add(Box.createHorizontalStrut(500));
        main.add(center);
        main.add(Box.createHorizontalStrut(500));

        master.add(main);
    }
    public static void actualizaIdioma(int newLang){
        Idioma idioma = new Idioma(newLang);

        btn_tutor.setText(idioma.getProperty("inicioSesionTutor"));
        btn_configuracion.setText(idioma.getProperty("configuracion"));
    }
}
package View;

import Controller.ControladorPanelPrincipal;
import Model.Idioma;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    public MainFrame(){
        this.setTitle("Gestor FCT");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setBounds(600,200,650,350);
        MainPanel myPanel = new MainPanel();
        this.add(myPanel);
        this.setVisible(true);
    }
}

class MainPanel extends JPanel {
    JButton btn_profesor, btn_tutor, btn_configuracion;
    public MainPanel() {
        JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main,BoxLayout.X_AXIS));
        Idioma idioma = new Idioma(Idioma.spanish);

        JPanel center = new JPanel();
        center.setLayout(new BoxLayout(center,BoxLayout.Y_AXIS));
        btn_profesor = new JButton(idioma.getProperty("inicioSesionProfesor"));
        btn_tutor = new JButton(idioma.getProperty("inicioSesionTutor"));
        btn_configuracion = new JButton(idioma.getProperty("configuracion"));

        Dimension dimensionBtn = new Dimension(300,25);
        btn_profesor.setPreferredSize(dimensionBtn);
        btn_profesor.setMaximumSize(dimensionBtn);
        btn_tutor.setPreferredSize(dimensionBtn);
        btn_tutor.setMaximumSize(dimensionBtn);
        btn_configuracion.setPreferredSize(dimensionBtn);
        btn_configuracion.setMaximumSize(dimensionBtn);

        ControladorPanelPrincipal panelPrincipal = new ControladorPanelPrincipal(this,main);

        btn_configuracion.addActionListener(e-> panelPrincipal.nuevoPanelActivo(new PanelConfiguracion()));

        center.add(Box.createVerticalStrut(50));
        center.add(btn_profesor);
        center.add(Box.createVerticalStrut(50));
        center.add(btn_tutor);
        center.add(Box.createVerticalStrut(50));
        center.add(btn_configuracion);

        main.add(Box.createHorizontalStrut(500));
        main.add(center);
        main.add(Box.createHorizontalStrut(500));

        add(main);
    }
}
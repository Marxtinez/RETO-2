package View;

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
        this.setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
        Idioma idioma = new Idioma(Idioma.spanish);

        JPanel center = new JPanel();
        center.setLayout(new BoxLayout(center,BoxLayout.Y_AXIS));
        btn_profesor = new JButton(idioma.getProperty("inicioSesionProfesor"));
        btn_tutor = new JButton(idioma.getProperty("inicioSesionTutor"));
        btn_configuracion = new JButton(idioma.getProperty("configuracion"));

        Dimension dimensionBtn = new Dimension(1000,25);
        btn_profesor.setPreferredSize(dimensionBtn);
        btn_profesor.setMaximumSize(dimensionBtn);
        btn_tutor.setPreferredSize(dimensionBtn);
        btn_tutor.setMaximumSize(dimensionBtn);
        btn_configuracion.setPreferredSize(dimensionBtn);
        btn_configuracion.setMaximumSize(dimensionBtn);


        center.add(btn_profesor);
        center.add(Box.createVerticalStrut(50));
        center.add(btn_tutor);
        center.add(Box.createVerticalStrut(50));
        center.add(btn_configuracion);

        add(Box.createHorizontalStrut(200));
        add(center);
        add(Box.createHorizontalStrut(200));
    }
}
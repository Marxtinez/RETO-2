package View;

import Controller.ControladorPanelPrincipal;
import Model.Idioma;

import javax.swing.*;
import java.awt.*;

public class PanelConsultasEspecificas extends JPanel {
    JButton btnC1,btnC2,btnC3,btnC4,btnC5,btnC6,btnC7,btnC8,btnAtras;
    JLabel lblC1,lblC2,lblC3,lblC4,lblC5,lblC6,lblC7,lblC8;
    public PanelConsultasEspecificas() {
        Idioma idioma = new Idioma(Idioma.spanish);

        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

        JPanel panelform = new JPanel();
        panelform.setLayout(new BoxLayout(panelform,BoxLayout.X_AXIS));

        JPanel panelformIzq = new JPanel();
        panelformIzq.setLayout(new BoxLayout(panelformIzq,BoxLayout.Y_AXIS));

        JPanel panelformDer = new JPanel();
        panelformDer.setLayout(new BoxLayout(panelformDer,BoxLayout.Y_AXIS));

        btnC1 = new JButton(idioma.getProperty("ir"));
        btnC2 = new JButton(idioma.getProperty("ir"));
        btnC3 = new JButton(idioma.getProperty("ir"));
        btnC4 = new JButton(idioma.getProperty("ir"));
        btnC5 = new JButton(idioma.getProperty("ir"));
        btnC6 = new JButton(idioma.getProperty("ir"));
        btnC7 = new JButton(idioma.getProperty("ir"));
        btnC8 = new JButton(idioma.getProperty("ir"));
        btnAtras = new JButton(idioma.getProperty("atras"));

        btnC1.addActionListener(e-> ControladorPanelPrincipal.nuevoPanelActivo(new PanelC1()));

        lblC1 = new JLabel(idioma.getProperty("c1"));
        lblC2 = new JLabel(idioma.getProperty("c2"));
        lblC3 = new JLabel(idioma.getProperty("c3"));
        lblC4 = new JLabel(idioma.getProperty("c4"));
        lblC5 = new JLabel(idioma.getProperty("c5"));
        lblC6 = new JLabel(idioma.getProperty("c6"));
        lblC7 = new JLabel(idioma.getProperty("c7"));
        lblC8 = new JLabel(idioma.getProperty("c8"));

        panelformIzq.add(lblC1);
        panelformIzq.add(btnC1);
        panelformIzq.add(Box.createVerticalStrut(10));
        panelformIzq.add(lblC2);
        panelformIzq.add(btnC2);
        panelformIzq.add(Box.createVerticalStrut(10));
        panelformIzq.add(lblC3);
        panelformIzq.add(btnC3);
        panelformIzq.add(Box.createVerticalStrut(10));
        panelformIzq.add(lblC4);
        panelformIzq.add(btnC4);

        panelformDer.add(lblC5);
        panelformDer.add(btnC5);
        panelformDer.add(Box.createVerticalStrut(10));
        panelformDer.add(lblC6);
        panelformDer.add(btnC6);
        panelformDer.add(Box.createVerticalStrut(10));
        panelformDer.add(lblC7);
        panelformDer.add(btnC7);
        panelformDer.add(Box.createVerticalStrut(10));
        panelformDer.add(lblC8);
        panelformDer.add(btnC8);

        panelform.add(panelformIzq);
        panelform.add(Box.createHorizontalStrut(25));
        panelform.add(panelformDer);

        add(Box.createVerticalStrut(25));
        add(panelform);
        add(Box.createVerticalStrut(25));
    }
}

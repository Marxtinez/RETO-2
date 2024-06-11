package View;

import Model.Idioma;

import javax.swing.*;

public class PanelConsultasEspecificas extends JPanel {
    JButton btnC1,btnC2,btnC3,btnC4,btnC5,btnC6,btnC7,btnC8,btnAtras;
    JLabel lblC1,lblC2,lblC3,lblC4,lblC5,lblC6,lblC7,lblC8;
    public PanelConsultasEspecificas() {
        Idioma idioma = new Idioma(Idioma.spanish);

        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

        btnC1 = new JButton("ir");
        btnC2 = new JButton("ir");
        btnC3 = new JButton("ir");
        btnC4 = new JButton("ir");
        btnC5 = new JButton("ir");
        btnC6 = new JButton("ir");
        btnC7 = new JButton("ir");
        btnC8 = new JButton("ir");
        btnAtras = new JButton("atras");

        lblC1 = new JLabel(idioma.getProperty("c1"));
        lblC2 = new JLabel(idioma.getProperty("c2"));
        lblC3 = new JLabel(idioma.getProperty("c3"));
        lblC4 = new JLabel(idioma.getProperty("c4"));
        lblC5 = new JLabel(idioma.getProperty("c5"));
        lblC6 = new JLabel(idioma.getProperty("c6"));
        lblC7 = new JLabel(idioma.getProperty("c7"));
        lblC8 = new JLabel(idioma.getProperty("c8"));
    }
}

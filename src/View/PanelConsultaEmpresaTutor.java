package View;

import Model.Idioma;

import javax.swing.*;

public class PanelConsultaEmpresaTutor extends JPanel {
    JComboBox comboEmpresa = new JComboBox<>();
    public PanelConsultaEmpresaTutor() {
        Idioma idioma = new Idioma(Idioma.spanish);

        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        comboEmpresa.addItem("Hola");

        add(comboEmpresa);


    }
}

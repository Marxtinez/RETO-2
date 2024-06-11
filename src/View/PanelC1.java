package View;

import Model.Idioma;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class PanelC1 extends JPanel {
    JTable tabla;
    public PanelC1() {
        Idioma idioma = new Idioma(Idioma.spanish);

        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

        String[] columns = {"a","b","c","d"};

        DefaultTableModel tableModel = new DefaultTableModel(columns,0);
        tabla = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tabla);

        add(scrollPane);
    }
}

package View;

import Model.Idioma;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class PanelC1 extends JPanel {
    JTable tabla;
    public PanelC1() {
        Idioma idioma = new Idioma(Idioma.spanish);

        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

        String[] columns = {"CIF","Nombre_Empresa","Direccion_Empresa","Tecnologias_Empresa","Sector_Empresa","Telefono_Empresa","Numero_Empleados","Ultimo_Anio_Colaboracion","ID_Profesor","Nombre_Profesor","Email_Profesor","Telefono_Profesor","ID_Trabajador","Nombre_Trabajador","Telefono_Trabajador","Cargo_Trabajador","Persona_Contacto"};

        DefaultTableModel tableModel = new DefaultTableModel(columns,0);
        tabla = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tabla);

        tabla.getColumnModel().getColumn(1).setPreferredWidth(150);


        add(scrollPane);
    }
}

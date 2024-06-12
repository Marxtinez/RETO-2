package View;

import Controller.ControladorConsultaEmpresaPorCIF;
import Model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Vector;

public class PanelC1 extends JPanel {
    JButton btnBusca;
    JTextField txtBusca;
    JTable tabla;
    public PanelC1() {
        Idioma idioma = new Idioma(PanelConfiguracion.comboIdioma.getSelectedIndex());
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

        String[] columns = {"CIF","Nombre_Empresa","Direccion_Empresa","Tecnologias_Empresa","Sector_Empresa","Telefono_Empresa","Numero_Empleados","Ultimo_Anio_Colaboracion","ID_Profesor","Nombre_Profesor","Email_Profesor","Telefono_Profesor","ID_Trabajador","Nombre_Trabajador","Telefono_Trabajador","Cargo_Trabajador","Persona_Contacto"};

        DefaultTableModel tableModel = new DefaultTableModel(columns,0);
        tabla = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tabla);
        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        txtBusca = new JTextField();
        btnBusca = new JButton(idioma.getProperty("buscar"));

        btnBusca.addActionListener(e-> {ControladorConsultaEmpresaPorCIF.cargaConsultaEmpresaC1(txtBusca.getText());
            for (EmpresaTutorTrabajador consulta : ControladorConsultaEmpresaPorCIF.resultados){
                Empresa empresa = consulta.getEmpresa();
                Trabajador trabajador = consulta.getTrabajador();
                TutorFCT tutorFCT = consulta.getTutorFCT();
                String[] fila = {empresa.getCIF(), empresa.getNombre(), empresa.getDireccion(), empresa.getTecnologias(), empresa.getSector(), empresa.getTelefono(), String.valueOf(empresa.getNum_empleados()), String.valueOf(empresa.getUlt_anio_colab()), String.valueOf(tutorFCT.getId_tutor()),tutorFCT.getNombre(),tutorFCT.getEmail(),tutorFCT.getTelefono(), String.valueOf(trabajador.getId_trabajador()),trabajador.getNombre(),trabajador.getTelefono(),trabajador.getCargo(), String.valueOf(trabajador.getPersona_contacto())};
                tableModel.addRow(fila);
                tableModel.fireTableDataChanged();
            }});

        add(txtBusca);
        add(btnBusca);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        add(scrollPane);
    }
}

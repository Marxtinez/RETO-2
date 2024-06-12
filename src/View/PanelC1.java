package View;

import Controller.ControladorConsultaEmpresaPorCIF;
import Controller.ControladorPanelPrincipal;
import Model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Vector;

public class PanelC1 extends JPanel {
    JButton btnBusca,btnAtras;
    JTextField txtBusca;
    JLabel lblBusca;
    JTable tabla;
    public PanelC1() {
        Idioma idioma = new Idioma(PanelConfiguracion.comboIdioma.getSelectedIndex());
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

        String[] columns = {"CIF","Nombre_Empresa","Direccion_Empresa","Tecnologias_Empresa","Sector_Empresa","Telefono_Empresa","Numero_Empleados","Ultimo_Anio_Colaboracion","ID_Profesor","Nombre_Profesor","Email_Profesor","Telefono_Profesor","ID_Trabajador","Nombre_Trabajador","Telefono_Trabajador","Cargo_Trabajador","Persona_Contacto"};

        DefaultTableModel tableModel = new DefaultTableModel(columns,0);
        tabla = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tabla);
        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        JPanel panelForm = new JPanel();
        panelForm.setLayout(new BoxLayout(panelForm,BoxLayout.X_AXIS));

        JPanel panelFormIzq = new JPanel();
        panelFormIzq.setLayout(new BoxLayout(panelFormIzq,BoxLayout.Y_AXIS));

        txtBusca = new JTextField();
        lblBusca = new JLabel(idioma.getProperty("cif"));
        btnBusca = new JButton(idioma.getProperty("buscar"));
        btnAtras = new JButton(idioma.getProperty("atras"));
        btnAtras.setAlignmentX(CENTER_ALIGNMENT);

        btnAtras.addActionListener(e-> ControladorPanelPrincipal.panelAntiguo());
        btnBusca.addActionListener(e-> {
            tableModel.setRowCount(0);
            ControladorConsultaEmpresaPorCIF.cargaConsultaEmpresaC1(txtBusca.getText());
            for (EmpresaTutorTrabajador consulta : ControladorConsultaEmpresaPorCIF.resultados){
                Empresa empresa = consulta.getEmpresa();
                Trabajador trabajador = consulta.getTrabajador();
                TutorFCT tutorFCT = consulta.getTutorFCT();
                String[] fila = {empresa.getCIF(), empresa.getNombre(), empresa.getDireccion(), empresa.getTecnologias(), empresa.getSector(), empresa.getTelefono(), String.valueOf(empresa.getNum_empleados()), String.valueOf(empresa.getUlt_anio_colab()), String.valueOf(tutorFCT.getId_tutor()),tutorFCT.getNombre(),tutorFCT.getEmail(),tutorFCT.getTelefono(), String.valueOf(trabajador.getId_trabajador()),trabajador.getNombre(),trabajador.getTelefono(),trabajador.getCargo(), String.valueOf(trabajador.getPersona_contacto())};
                tableModel.addRow(fila);
                tableModel.fireTableDataChanged();
            }});

        panelFormIzq.add(lblBusca);
        panelFormIzq.add(txtBusca);
        panelFormIzq.add(btnBusca);

        panelForm.add(panelFormIzq);
        panelForm.add(Box.createHorizontalStrut(20));
        panelForm.add(btnBusca);

        add(panelForm);
        add(Box.createVerticalStrut(15));
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(400,200));
        add(scrollPane);
        add(Box.createVerticalStrut(15));
        add(btnAtras);
    }
}

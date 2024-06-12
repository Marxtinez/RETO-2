package View;

import Controller.ControladorConsultaEmpresaTecnologia;
import Controller.ControladorConsultaHistorialContactos;
import Controller.ControladorPanelPrincipal;
import Model.EmpresaTecnologias;
import Model.Empresa_Contacta_Tutor;
import Model.Idioma;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PanelC8 extends JPanel {
    JButton btnBusca,btnAtras;
    JTextField txtBusca;
    JLabel lblBusca;
    JTable tabla;
    public PanelC8() {
        Idioma idioma = new Idioma(PanelConfiguracion.comboIdioma.getSelectedIndex());
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

        String[] columns = {"Id_Tutor","CIF","Fecha"};

        DefaultTableModel tableModel = new DefaultTableModel(columns,0);
        tabla = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tabla);

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
            ControladorConsultaHistorialContactos.cargarHistorialContactos(txtBusca.getText());
            for (Empresa_Contacta_Tutor consulta : ControladorConsultaHistorialContactos.resultados){
                String[] fila = {String.valueOf(consulta.getId_tutor()),consulta.getCIF(),consulta.getFecha()};
                tableModel.addRow(fila);
                tableModel.fireTableDataChanged();
            }
            tableModel.fireTableDataChanged();
        });

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
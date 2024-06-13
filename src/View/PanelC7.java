package View;

import Controller.ControladorConsultaEmpresaPorCIF;
import Controller.ControladorConsultaEmpresaTecnologia;
import Controller.ControladorConsultaFCTPorEmpresaYCurso;
import Controller.ControladorPanelPrincipal;
import Model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PanelC7 extends JPanel {
    static JButton btnBusca,btnAtras;
    JTextField txtBusca;
    static JLabel lblBusca;
    JTable tabla;
    public PanelC7() {
        Idioma idioma = new Idioma(PanelConfiguracion.comboIdioma.getSelectedIndex());
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

        String[] columns = {"Nombre_Empresa","Tecnologias"};

        DefaultTableModel tableModel = new DefaultTableModel(columns,0);
        tabla = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tabla);

        JPanel panelForm = new JPanel();
        panelForm.setLayout(new BoxLayout(panelForm,BoxLayout.X_AXIS));

        JPanel panelFormIzq = new JPanel();
        panelFormIzq.setLayout(new BoxLayout(panelFormIzq,BoxLayout.Y_AXIS));

        txtBusca = new JTextField();
        lblBusca = new JLabel(idioma.getProperty("tecno"));
        btnBusca = new JButton(idioma.getProperty("buscar"));
        btnAtras = new JButton(idioma.getProperty("atras"));
        btnAtras.setAlignmentX(CENTER_ALIGNMENT);

        btnAtras.addActionListener(e-> ControladorPanelPrincipal.panelAntiguo());
        btnBusca.addActionListener(e-> {
            tableModel.setRowCount(0);
            ControladorConsultaEmpresaTecnologia.cargarConsultaEmpresaC7(txtBusca.getText());
            for (EmpresaTecnologias consulta : ControladorConsultaEmpresaTecnologia.resultados){
                String[] fila = {consulta.getEmpresa().getNombre(),consulta.getTecnologias().getTecnologias()};
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
    public static void actualizaIdioma(int newLang){
        Idioma idioma = new Idioma(newLang);

        lblBusca.setText(idioma.getProperty("tecno"));
        btnBusca.setText(idioma.getProperty("buscar"));
        btnAtras.setText(idioma.getProperty("atras"));
    }
}
package View;

import Controller.ControladorConsultaEmpresasPorGrupoYCurso;
import Controller.ControladorConsultaFCTPorEmpresaYCurso;
import Controller.ControladorPanelPrincipal;
import Model.Empresa;
import Model.EmpresaCursoFCT;
import Model.EmpresaFCT;
import Model.Idioma;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PanelC4 extends JPanel {
    JButton btnBusca,btnAtras;
    JTextField txtCif,txtCurso;
    JLabel lblCif,lblCurso;
    JTable tabla;
    public PanelC4() {
        Idioma idioma = new Idioma(PanelConfiguracion.comboIdioma.getSelectedIndex());
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

        String[] columns = {"Id_Grupo","Id_Ciclo","Cantidad_Alumnos"};

        DefaultTableModel tableModel = new DefaultTableModel(columns,0);
        tabla = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tabla);

        JPanel panelForm = new JPanel();
        panelForm.setLayout(new BoxLayout(panelForm,BoxLayout.X_AXIS));

        JPanel panelFormIzq = new JPanel();
        panelFormIzq.setLayout(new BoxLayout(panelFormIzq,BoxLayout.Y_AXIS));

        txtCif = new JTextField();
        lblCif = new JLabel(idioma.getProperty("cif"));
        txtCurso = new JTextField();
        lblCurso = new JLabel(idioma.getProperty("curso"));
        btnBusca = new JButton(idioma.getProperty("buscar"));
        btnAtras = new JButton(idioma.getProperty("atras"));
        btnAtras.setAlignmentX(CENTER_ALIGNMENT);

        btnAtras.addActionListener(e-> ControladorPanelPrincipal.panelAntiguo());
        btnBusca.addActionListener(e-> {
            tableModel.setRowCount(0);
            ControladorConsultaFCTPorEmpresaYCurso.cargaConsultasFCTPorEmpresaYCursoC4(txtCif.getText(),txtCurso.getText());
            for (EmpresaCursoFCT consulta : ControladorConsultaFCTPorEmpresaYCurso.resultados){
                String[] fila = {consulta.getId_grupo(),consulta.getId_ciclo(), String.valueOf(consulta.getCantidad_alumnos_realizado_FCT())};
                tableModel.addRow(fila);
                tableModel.fireTableDataChanged();
            }});

        panelFormIzq.add(lblCif);
        panelFormIzq.add(txtCif);
        panelFormIzq.add(Box.createVerticalStrut(10));
        panelFormIzq.add(lblCurso);
        panelFormIzq.add(txtCurso);
        panelFormIzq.add(btnBusca);

        panelForm.add(panelFormIzq);
        panelForm.add(Box.createHorizontalStrut(20));
        panelForm.add(btnBusca);

        add(panelForm);
        add(Box.createVerticalStrut(15));
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(400,150));
        add(scrollPane);
        add(Box.createVerticalStrut(15));
        add(btnAtras);
    }
}
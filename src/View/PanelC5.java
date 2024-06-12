package View;

import Controller.ControladorConsultaFCTPorEmpresaYCurso;
import Controller.ControladorConsultaIncidenciasCurso;
import Controller.ControladorPanelPrincipal;
import Model.EmpresaCursoFCT;
import Model.Idioma;
import Model.Incidencia;
import Model.IncidenciaGrupo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.concurrent.atomic.AtomicReference;

public class PanelC5 extends JPanel {
    JButton btnBusca,btnAtras;
    JTextField txtCurso;
    JLabel lblCurso;
    JTable tabla;
    public PanelC5() {
        Idioma idioma = new Idioma(PanelConfiguracion.comboIdioma.getSelectedIndex());
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

        String[] columns = {"Id_Incidencia","Descripcion","Fecha","Cif"};

        DefaultTableModel tableModel = new DefaultTableModel(columns,0);
        tabla = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tabla);
        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        JPanel panelForm = new JPanel();
        panelForm.setLayout(new BoxLayout(panelForm,BoxLayout.X_AXIS));

        JPanel panelFormIzq = new JPanel();
        panelFormIzq.setLayout(new BoxLayout(panelFormIzq,BoxLayout.Y_AXIS));

        txtCurso = new JTextField();
        lblCurso = new JLabel(idioma.getProperty("curso"));
        btnBusca = new JButton(idioma.getProperty("buscar"));
        btnAtras = new JButton(idioma.getProperty("atras"));
        btnAtras.setAlignmentX(CENTER_ALIGNMENT);

        btnAtras.addActionListener(e-> ControladorPanelPrincipal.panelAntiguo());
        btnBusca.addActionListener(e-> {
            tableModel.setRowCount(0);
            String Ini = (txtCurso.getText().substring(0, 4)).concat("-09-01");
            String Fin = (txtCurso.getText().substring(5, 9)).concat("-07-01");
            ControladorConsultaIncidenciasCurso.cargarConsultaIncidenciasPorCurso(Ini,Fin,txtCurso.getText());
            for (IncidenciaGrupo consulta : ControladorConsultaIncidenciasCurso.resultados){
                Incidencia incidencia = consulta.getIncidencia();
                String[] fila = {String.valueOf(incidencia.getId_incidencia()),incidencia.getDescripcion(),incidencia.getFecha(),incidencia.getCIF()};
                tableModel.addRow(fila);
                tableModel.fireTableDataChanged();
            }});

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
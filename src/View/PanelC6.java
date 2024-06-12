package View;

import Controller.ControladorConsultaAlumnosCurso;
import Controller.ControladorConsultaFCTPorEmpresaYCurso;
import Controller.ControladorPanelPrincipal;
import Model.EmpresaCursoFCT;
import Model.Idioma;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;

public class PanelC6 extends JPanel {
    JButton btnBusca,btnAtras;
    JTextField txtCif,txtCiclo,txtCurso;
    JLabel lblCif,lblCiclo,lblCurso;
    JTable tabla;
    public PanelC6() {
        Idioma idioma = new Idioma(PanelConfiguracion.comboIdioma.getSelectedIndex());
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

        String[] columns = {"Cif","Nombre_Empresa","Ciclo","Curso","Cantidad_Alumnos"};

        DefaultTableModel tableModel = new DefaultTableModel(columns,0);
        tabla = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tabla);

        JPanel panelForm = new JPanel();
        panelForm.setLayout(new BoxLayout(panelForm,BoxLayout.X_AXIS));

        JPanel panelFormIzq = new JPanel();
        panelFormIzq.setLayout(new BoxLayout(panelFormIzq,BoxLayout.Y_AXIS));

        txtCiclo = new JTextField();
        lblCiclo = new JLabel(idioma.getProperty("ciclo"));
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
            try {
                ControladorConsultaAlumnosCurso.obtenerSolicitudPorEmpresaYCiclo(txtCif.getText(),txtCiclo.getText(),txtCurso.getText());
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            for (EmpresaCursoFCT consulta : ControladorConsultaFCTPorEmpresaYCurso.resultados){
                String[] fila = {consulta.getId_grupo(),consulta.getId_ciclo(), String.valueOf(consulta.getCantidad_alumnos_realizado_FCT())};
                tableModel.addRow(fila);
                tableModel.fireTableDataChanged();
            }});

        panelFormIzq.add(lblCif);
        panelFormIzq.add(txtCif);
        panelFormIzq.add(Box.createVerticalStrut(10));
        panelFormIzq.add(lblCiclo);
        panelFormIzq.add(txtCiclo);
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
        scrollPane.setPreferredSize(new Dimension(400,110));
        add(scrollPane);
        add(Box.createVerticalStrut(15));
        add(btnAtras);
    }
}

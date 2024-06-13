package View;

import Controller.ControladorConsultaEmpresaPorCIF;
import Controller.ControladorConsultaEmpresasPorGrupoYCurso;
import Controller.ControladorPanelPrincipal;
import Model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PanelC2 extends JPanel{
    static JButton btnBusca,btnAtras;
    JTextField txtCiclo,txtCurso;
    static JLabel lblCiclo,lblCurso;
    JTable tabla;
    public PanelC2() {
        Idioma idioma = new Idioma(PanelConfiguracion.comboIdioma.getSelectedIndex());
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

        String[] columns = {"CIF","Nombre_Empresa","Direccion_Empresa","Tecnologias_Empresa","Sector_Empresa","Telefono_Empresa","Numero_Empleados","Ultimo_Anio_Colaboracion","Cantidad_Alumnos"};

        DefaultTableModel tableModel = new DefaultTableModel(columns,0);
        tabla = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tabla);
        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        JPanel panelForm = new JPanel();
        panelForm.setLayout(new BoxLayout(panelForm,BoxLayout.X_AXIS));

        JPanel panelFormIzq = new JPanel();
        panelFormIzq.setLayout(new BoxLayout(panelFormIzq,BoxLayout.Y_AXIS));

        txtCiclo = new JTextField();
        lblCiclo = new JLabel(idioma.getProperty("ciclo"));
        txtCurso = new JTextField();
        lblCurso = new JLabel(idioma.getProperty("curso"));
        btnBusca = new JButton(idioma.getProperty("buscar"));
        btnAtras = new JButton(idioma.getProperty("atras"));
        btnAtras.setAlignmentX(CENTER_ALIGNMENT);

        btnAtras.addActionListener(e-> ControladorPanelPrincipal.panelAntiguo());
        btnBusca.addActionListener(e-> {
            tableModel.setRowCount(0);
            ControladorConsultaEmpresasPorGrupoYCurso.cargaConsultaCicloEmpresasC2(txtCiclo.getText(),txtCurso.getText());
            for (EmpresaFCT consulta : ControladorConsultaEmpresasPorGrupoYCurso.resultados){
                Empresa empresa = consulta.getEmpresa();
                String[] fila = {empresa.getCIF(), empresa.getNombre(), empresa.getDireccion(), empresa.getTecnologias(), empresa.getSector(), empresa.getTelefono(), String.valueOf(empresa.getNum_empleados()), String.valueOf(empresa.getUlt_anio_colab()),String.valueOf(consulta.getNum_total_alumnos_FCT())};
                tableModel.addRow(fila);
                tableModel.fireTableDataChanged();
            }});

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
        scrollPane.setPreferredSize(new Dimension(400,150));
        add(scrollPane);
        add(Box.createVerticalStrut(15));
        add(btnAtras);
    }
    public static void actualizaIdioma(int newLang){
        Idioma idioma = new Idioma(newLang);

        lblCiclo.setText(idioma.getProperty("ciclo"));
        lblCurso.setText(idioma.getProperty("curso"));
        btnBusca.setText(idioma.getProperty("buscar"));
        btnAtras.setText(idioma.getProperty("atras"));
    }
}
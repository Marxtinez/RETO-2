package View;

import Controller.ControladorConsultaEmpresasPorGrupoYCurso;
import Controller.ControladorConsultaGrupoTutorEmpresas;
import Controller.ControladorPanelPrincipal;
import Model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PanelC3 extends JPanel{
    static JButton btnBusca,btnAtras;
    JTextField txtGrupo,txtCurso;
    static JLabel lblGrupo,lblCurso;
    JTable tabla;
    public PanelC3() {
        Idioma idioma = new Idioma(PanelConfiguracion.comboIdioma.getSelectedIndex());
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

        String[] columns = {"CIF","Nombre_Empresa","Direccion_Empresa","Tecnologias_Empresa","Sector_Empresa","Telefono_Empresa","Numero_Empleados","Ultimo_Anio_Colaboracion","ID_Profesor","Nombre_Profesor","Telefono_Profesor","Email_Profesor","Cantidad_Alumnos"};

        DefaultTableModel tableModel = new DefaultTableModel(columns,0);
        tabla = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tabla);
        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        JPanel panelForm = new JPanel();
        panelForm.setLayout(new BoxLayout(panelForm,BoxLayout.X_AXIS));

        JPanel panelFormIzq = new JPanel();
        panelFormIzq.setLayout(new BoxLayout(panelFormIzq,BoxLayout.Y_AXIS));

        txtGrupo = new JTextField();
        lblGrupo = new JLabel(idioma.getProperty("grupo"));
        txtCurso = new JTextField();
        lblCurso = new JLabel(idioma.getProperty("curso"));
        btnBusca = new JButton(idioma.getProperty("buscar"));
        btnAtras = new JButton(idioma.getProperty("atras"));
        btnAtras.setAlignmentX(CENTER_ALIGNMENT);

        btnAtras.addActionListener(e-> ControladorPanelPrincipal.panelAntiguo());
        btnBusca.addActionListener(e-> {
            tableModel.setRowCount(0);
            ControladorConsultaGrupoTutorEmpresas.cargaConsultaGrupoTutorEmpresasC3(txtGrupo.getText(),txtCurso.getText());
            for (TutorFCTEmpresaFCT consulta : ControladorConsultaGrupoTutorEmpresas.resultados){
                Empresa empresa = consulta.getEmpresa();
                TutorFCT tutorFCT = consulta.getTutor();
                String[] fila = {empresa.getCIF(), empresa.getNombre(), empresa.getDireccion(), empresa.getTecnologias(), empresa.getSector(), empresa.getTelefono(), String.valueOf(empresa.getNum_empleados()), String.valueOf(empresa.getUlt_anio_colab()),String.valueOf(tutorFCT.getId_tutor()), tutorFCT.getNombre(),tutorFCT.getTelefono(), tutorFCT.getEmail(),String.valueOf(consulta.getNum_total_alumnos_FCT())};
                tableModel.addRow(fila);
                tableModel.fireTableDataChanged();
            }});

        panelFormIzq.add(lblGrupo);
        panelFormIzq.add(txtGrupo);
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

        lblGrupo.setText(idioma.getProperty("grupo"));
        lblCurso.setText(idioma.getProperty("curso"));
        btnBusca.setText(idioma.getProperty("buscar"));
        btnAtras.setText(idioma.getProperty("atras"));
    }
}
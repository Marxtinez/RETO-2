package View;

import Controller.ControladorFCT;
import Controller.ControladorPanelPrincipal;
import Model.Empresa;
import Model.FCT;
import Model.Idioma;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class PanelConsultaFCTTutor extends JPanel{
    public JComboBox<FCT> comboFCT = new JComboBox<>();
    JTextField txtGrupo,txtCif,txtCurso,txtAlumnos;
    static JLabel lblGrupo,lblCif,lblCurso,lblAlumno;
    static JButton btnAgregar,btnEliminar,btnModificar,btnAtras;
    PanelAgregarFCT panelAgregarFCT = new PanelAgregarFCT();
    PanelModificarFCT panelModificarFCT = new PanelModificarFCT();
    public String cifMod,cursoMod,grupoMod;

    public PanelConsultaFCTTutor() throws SQLException {
        Idioma idioma = new Idioma(PanelConfiguracion.comboIdioma.getSelectedIndex());

        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        btnAtras = new JButton(idioma.getProperty("atras"));
        btnAgregar = new JButton(idioma.getProperty("agregar"));
        btnEliminar = new JButton(idioma.getProperty("eliminar"));
        btnModificar = new JButton(idioma.getProperty("modificar"));

        lblAlumno = new JLabel(idioma.getProperty("alumnos"));
        lblCurso = new JLabel(idioma.getProperty("curso"));
        lblGrupo = new JLabel(idioma.getProperty("grupo"));
        lblCif = new JLabel(idioma.getProperty("cif"));

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new BoxLayout(panelBotones,BoxLayout.X_AXIS));

        JPanel panelForm = new JPanel();
        panelForm.setLayout(new BoxLayout(panelForm,BoxLayout.X_AXIS));

        JPanel panelIzq = new JPanel();
        panelIzq.setLayout(new BoxLayout(panelIzq,BoxLayout.Y_AXIS));

        ControladorFCT.cargaContenidoFCTs();
        for (FCT fcts : ControladorFCT.fcts){
            comboFCT.addItem(fcts);
        }

        txtAlumnos = new JTextField(String.valueOf(ControladorFCT.fcts.get(0).getNum_alumnos()));
        txtCurso = new JTextField(ControladorFCT.fcts.get(0).getCurso());
        txtGrupo = new JTextField(ControladorFCT.fcts.get(0).getId_grupo());
        txtCif = new JTextField(ControladorFCT.fcts.get(0).getCIF());

        txtCif.setEditable(false);
        txtAlumnos.setEditable(false);
        txtGrupo.setEditable(false);
        txtCurso.setEditable(false);

        comboFCT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FCT fctSeleccionada = (FCT) comboFCT.getSelectedItem();
                txtAlumnos.setText(String.valueOf(fctSeleccionada.getNum_alumnos()));
                txtCif.setText(fctSeleccionada.getCIF());
                txtCurso.setText(fctSeleccionada.getCurso());
                txtGrupo.setText(fctSeleccionada.getId_grupo());
            }
        });

        btnAtras.addActionListener(e-> ControladorPanelPrincipal.panelAntiguo());
        btnAgregar.addActionListener(e-> ControladorPanelPrincipal.nuevoPanelActivo(panelAgregarFCT));
        btnEliminar.addActionListener(e->{
            try {
                FCT fctSeleccioanda = (FCT) comboFCT.getSelectedItem();
                ControladorFCT.eliminaFCT(fctSeleccioanda.getId_grupo(),fctSeleccioanda.getCIF(),fctSeleccioanda.getCurso());
                comboFCT.removeItem(fctSeleccioanda);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        btnModificar.addActionListener(e->{
            panelModificarFCT.txtAlumnos.setText(txtAlumnos.getText());
            panelModificarFCT.txtCif.setText(txtCif.getText());
            panelModificarFCT.txtCurso.setText(txtCurso.getText());
            panelModificarFCT.txtGrupo.setText(txtGrupo.getText());
            cifMod = txtCif.getText();
            grupoMod = txtGrupo.getText();
            cursoMod = txtCurso.getText();
            ControladorPanelPrincipal.nuevoPanelActivo(panelModificarFCT);
        });

        panelBotones.add(btnAgregar);
        panelBotones.add(Box.createHorizontalStrut(10));
        panelBotones.add(btnEliminar);
        panelBotones.add(Box.createHorizontalStrut(10));
        panelBotones.add(btnModificar);
        panelBotones.add(Box.createHorizontalStrut(10));
        panelBotones.add(btnAtras);

        add(Box.createVerticalStrut(20));
        add(comboFCT);
        add(Box.createVerticalStrut(20));
        panelIzq.add(lblGrupo);
        panelIzq.add(txtGrupo);
        panelIzq.add(Box.createVerticalStrut(10));
        panelIzq.add(lblCif);
        panelIzq.add(txtCif);
        panelIzq.add(Box.createVerticalStrut(10));
        panelIzq.add(lblCurso);
        panelIzq.add(txtCurso);
        panelIzq.add(Box.createVerticalStrut(10));
        panelIzq.add(lblAlumno);
        panelIzq.add(txtAlumnos);

        panelForm.add(panelIzq);
        add(panelForm);
        add(Box.createVerticalStrut(20));
        add(panelBotones);
    }
    public static void actualizaIdioma(int newLang){
        Idioma idioma = new Idioma(newLang);

        btnAtras.setText(idioma.getProperty("atras"));
        btnAgregar.setText(idioma.getProperty("agregar"));
        btnEliminar.setText(idioma.getProperty("eliminar"));
        btnModificar.setText(idioma.getProperty("modificar"));

        lblAlumno.setText(idioma.getProperty("alumnos"));
        lblCurso.setText(idioma.getProperty("curso"));
        lblGrupo.setText(idioma.getProperty("grupo"));
        lblCif.setText(idioma.getProperty("cif"));
    }
}

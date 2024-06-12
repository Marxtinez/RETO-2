package View;

import Controller.ControladorIncidencia;
import Controller.ControladorPanelPrincipal;
import Controller.ControladorTutorFCT;
import Model.Idioma;
import Model.Incidencia;
import Model.TutorFCT;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class PanelConsultaIncidenciaTutor extends JPanel {
    public JComboBox<Incidencia> comboIncidencia = new JComboBox<>();
    JTextField txtIdIncidencia, txtCif, txtDescripcion, txtFecha;
    JLabel lblIdIncidencia, lblCif, lblDescripcion, lblFecha;
    JButton btnAgregar,btnEliminar,btnModificar,btnAtras;
    PanelAgregarIncidencia panelAgregarIncidencia = new PanelAgregarIncidencia();
    PanelModificarIncidencia panelModificarIncidencia = new PanelModificarIncidencia();

    public PanelConsultaIncidenciaTutor() throws SQLException {
        Idioma idioma = new Idioma(PanelConfiguracion.comboIdioma.getSelectedIndex());

        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        btnAtras = new JButton(idioma.getProperty("atras"));
        btnAgregar = new JButton(idioma.getProperty("agregar"));
        btnEliminar = new JButton(idioma.getProperty("eliminar"));
        btnModificar = new JButton(idioma.getProperty("modificar"));

        lblFecha = new JLabel(idioma.getProperty("fecha"));
        lblCif = new JLabel(idioma.getProperty("cif"));
        lblIdIncidencia = new JLabel(idioma.getProperty("idincidencia"));
        lblDescripcion = new JLabel(idioma.getProperty("descrip"));

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new BoxLayout(panelBotones,BoxLayout.X_AXIS));

        JPanel panelFormX = new JPanel();
        panelFormX.setLayout(new BoxLayout(panelFormX,BoxLayout.X_AXIS));

        JPanel panelFormY = new JPanel();
        panelFormY.setLayout(new BoxLayout(panelFormY,BoxLayout.Y_AXIS));

        ControladorIncidencia.cargaContenidoIncidencias();
        for (Incidencia incidencia : ControladorIncidencia.incidencias){
            comboIncidencia.addItem(incidencia);
        }

        txtFecha = new JTextField(ControladorIncidencia.incidencias.get(0).getFecha());
        txtIdIncidencia = new JTextField(String.valueOf(ControladorIncidencia.incidencias.get(0).getId_incidencia()));
        txtCif = new JTextField(ControladorIncidencia.incidencias.get(0).getCIF());
        txtDescripcion = new JTextField(ControladorIncidencia.incidencias.get(0).getDescripcion());

        txtDescripcion.setEditable(false);
        txtCif.setEditable(false);
        txtIdIncidencia.setEditable(false);
        txtFecha.setEditable(false);

        comboIncidencia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Incidencia incidenciaSeleccionada = (Incidencia) comboIncidencia.getSelectedItem();
                txtDescripcion.setText(incidenciaSeleccionada.getDescripcion());
                txtCif.setText(incidenciaSeleccionada.getCIF());
                txtFecha.setText(incidenciaSeleccionada.getFecha());
                txtIdIncidencia.setText(String.valueOf(incidenciaSeleccionada.getId_incidencia()));
            }
        });

        btnAtras.addActionListener(e-> ControladorPanelPrincipal.panelAntiguo());
        btnAgregar.addActionListener(e-> {
            ControladorPanelPrincipal.nuevoPanelActivo(panelAgregarIncidencia);});
        btnEliminar.addActionListener(e->{
            try {
                Incidencia incidenciaSeleccionada = (Incidencia) comboIncidencia.getSelectedItem();
                ControladorIncidencia.eliminaIncidencia(incidenciaSeleccionada.getId_incidencia());
                comboIncidencia.removeItem(incidenciaSeleccionada);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        btnModificar.addActionListener(e->{
            panelModificarIncidencia.txtCif.setText(txtCif.getText());
            panelModificarIncidencia.txtIdIncidencia.setText(txtIdIncidencia.getText());
            panelModificarIncidencia.txtDescripcion.setText(txtDescripcion.getText());
            panelModificarIncidencia.txtFecha.setText(txtFecha.getText());
            ControladorPanelPrincipal.nuevoPanelActivo(panelModificarIncidencia);
        });

        panelBotones.add(Box.createHorizontalStrut(50));
        panelBotones.add(btnAgregar);
        panelBotones.add(Box.createHorizontalStrut(10));
        panelBotones.add(btnEliminar);
        panelBotones.add(Box.createHorizontalStrut(10));
        panelBotones.add(btnModificar);
        panelBotones.add(Box.createHorizontalStrut(10));
        panelBotones.add(btnAtras);
        panelBotones.add(Box.createHorizontalStrut(50));

        add(Box.createVerticalStrut(20));
        add(comboIncidencia);
        add(Box.createVerticalStrut(20));
        panelFormY.add(lblIdIncidencia);
        panelFormY.add(txtIdIncidencia);
        panelFormY.add(Box.createVerticalStrut(10));
        panelFormY.add(lblCif);
        panelFormY.add(txtCif);
        panelFormY.add(Box.createVerticalStrut(10));
        panelFormY.add(lblFecha);
        panelFormY.add(txtFecha);
        panelFormY.add(Box.createVerticalStrut(10));
        panelFormY.add(lblDescripcion);
        panelFormY.add(txtDescripcion);

        panelFormX.add(panelFormY);
        add(panelFormX);
        add(Box.createVerticalStrut(20));
        add(panelBotones);
    }
}
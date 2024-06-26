package View;

import Controller.ControladorPanelPrincipal;
import Controller.ControladorTrabajador;
import Controller.ControladorTutorFCT;
import Model.Idioma;
import Model.Trabajador;
import Model.TutorFCT;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class PanelConsultaTrabajadorTutor extends JPanel {
    public JComboBox<Trabajador> comboTrabajador = new JComboBox<>();
    JTextField txtIdTrabajador,txtNombre,txtTelefono,txtEmail,txtCargo,txtCif;
    JCheckBox checkContaco;
    static JLabel lblIdTrabajador,lblNombre,lblTelefono,lblEmail,lblCargo,lblContaco,lblCif;
    static JButton btnAgregar,btnEliminar,btnModificar,btnAtras;
    PanelAgregarTrabajador panelAgregarTrabajador = new PanelAgregarTrabajador();
    PanelModificarTrabajador panelModificarTrabajador = new PanelModificarTrabajador();

    public PanelConsultaTrabajadorTutor() throws SQLException {
        Idioma idioma = new Idioma(PanelConfiguracion.comboIdioma.getSelectedIndex());

        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        btnAtras = new JButton(idioma.getProperty("atras"));
        btnAgregar = new JButton(idioma.getProperty("agregar"));
        btnEliminar = new JButton(idioma.getProperty("eliminar"));
        btnModificar = new JButton(idioma.getProperty("modificar"));

        lblEmail = new JLabel(idioma.getProperty("email"));
        lblNombre = new JLabel(idioma.getProperty("nombre"));
        lblIdTrabajador = new JLabel(idioma.getProperty("idtrabajador"));
        lblTelefono = new JLabel(idioma.getProperty("telefono"));
        lblCargo = new JLabel(idioma.getProperty("cargo"));
        lblContaco = new JLabel(idioma.getProperty("contacto"));
        lblCif = new JLabel(idioma.getProperty("cif"));

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new BoxLayout(panelBotones,BoxLayout.X_AXIS));

        JPanel panelFormX = new JPanel();
        panelFormX.setLayout(new BoxLayout(panelFormX,BoxLayout.X_AXIS));

        JPanel panelFormIzq = new JPanel();
        panelFormIzq.setLayout(new BoxLayout(panelFormIzq,BoxLayout.Y_AXIS));

        JPanel panelFormDer = new JPanel();
        panelFormDer.setLayout(new BoxLayout(panelFormDer,BoxLayout.Y_AXIS));

        ControladorTrabajador.obtenerInformacionTrabajador();
        for (Trabajador trabajador : ControladorTrabajador.trabajadores){
            comboTrabajador.addItem(trabajador);
        }

        txtEmail = new JTextField(ControladorTrabajador.trabajadores.get(0).getEmail());
        txtIdTrabajador = new JTextField(String.valueOf(ControladorTrabajador.trabajadores.get(0).getId_trabajador()));
        txtNombre= new JTextField(ControladorTrabajador.trabajadores.get(0).getNombre());
        txtTelefono = new JTextField(ControladorTrabajador.trabajadores.get(0).getTelefono());
        txtCargo = new JTextField(ControladorTrabajador.trabajadores.get(0).getCargo());
        checkContaco = new JCheckBox();
        txtCif= new JTextField(ControladorTrabajador.trabajadores.get(0).getCIF());
        checkContaco.setSelected(ControladorTrabajador.trabajadores.get(0).getPersona_contacto());

        txtTelefono.setEditable(false);
        txtNombre.setEditable(false);
        txtIdTrabajador.setEditable(false);
        txtEmail.setEditable(false);
        txtCif.setEditable(false);
        txtCargo.setEditable(false);
        checkContaco.setEnabled(false);

        comboTrabajador.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Trabajador trabajadorSeleccionado = (Trabajador) comboTrabajador.getSelectedItem();
                txtTelefono.setText(trabajadorSeleccionado.getTelefono());
                txtNombre.setText(trabajadorSeleccionado.getNombre());
                txtEmail.setText(trabajadorSeleccionado.getEmail());
                txtIdTrabajador.setText(String.valueOf(trabajadorSeleccionado.getId_trabajador()));
                txtCargo.setText(trabajadorSeleccionado.getCargo());
                txtCif.setText(trabajadorSeleccionado.getCIF());
                checkContaco.setSelected(trabajadorSeleccionado.getPersona_contacto());
            }
        });

        btnAtras.addActionListener(e-> ControladorPanelPrincipal.panelAntiguo());
        btnAgregar.addActionListener(e-> {
            ControladorPanelPrincipal.nuevoPanelActivo(panelAgregarTrabajador);});
        btnEliminar.addActionListener(e->{
            try {
                Trabajador trabajadorSeleccionado = (Trabajador) comboTrabajador.getSelectedItem();
               ControladorTrabajador.eliminarTrabajador(trabajadorSeleccionado.getId_trabajador());
                comboTrabajador.removeItem(trabajadorSeleccionado);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        btnModificar.addActionListener(e->{
            panelModificarTrabajador.txtIdTrabajador.setText(txtIdTrabajador.getText());
            panelModificarTrabajador.txtCargo.setText(txtCargo.getText());
            panelModificarTrabajador.txtCif.setText(txtCif.getText());
            panelModificarTrabajador.txtTelefono.setText(txtTelefono.getText());
            panelModificarTrabajador.txtNombre.setText(txtNombre.getText());
            panelModificarTrabajador.txtEmail.setText(txtEmail.getText());
            panelModificarTrabajador.checkContaco.setSelected(checkContaco.isSelected());
            ControladorPanelPrincipal.nuevoPanelActivo(panelModificarTrabajador);
        });

        panelBotones.add(btnAgregar);
        panelBotones.add(Box.createHorizontalStrut(10));
        panelBotones.add(btnEliminar);
        panelBotones.add(Box.createHorizontalStrut(10));
        panelBotones.add(btnModificar);
        panelBotones.add(Box.createHorizontalStrut(10));
        panelBotones.add(btnAtras);

        add(Box.createVerticalStrut(20));
        add(comboTrabajador);
        add(Box.createVerticalStrut(20));
        panelFormIzq.add(lblIdTrabajador);
        panelFormIzq.add(txtIdTrabajador);
        panelFormIzq.add(Box.createVerticalStrut(10));
        panelFormIzq.add(lblNombre);
        panelFormIzq.add(txtNombre);
        panelFormIzq.add(Box.createVerticalStrut(10));
        panelFormIzq.add(lblEmail);
        panelFormIzq.add(txtEmail);
        panelFormIzq.add(Box.createVerticalStrut(10));
        panelFormIzq.add(lblTelefono);
        panelFormIzq.add(txtTelefono);

        panelFormDer.add(lblCif);
        panelFormDer.add(txtCif);
        panelFormDer.add(Box.createVerticalStrut(10));
        panelFormDer.add(lblCargo);
        panelFormDer.add(txtCargo);
        panelFormDer.add(Box.createVerticalStrut(10));
        panelFormDer.add(lblContaco);
        panelFormDer.add(checkContaco);
        panelFormDer.add(Box.createVerticalStrut(40));

        panelFormX.add(panelFormIzq);
        panelFormX.add(Box.createHorizontalStrut(20));
        panelFormX.add(panelFormDer);

        add(panelFormX);
        add(Box.createVerticalStrut(20));
        add(panelBotones);
    }
    public static void actualizaIdioma(int newLang){
        Idioma idioma = new Idioma(newLang);

        btnAtras.setText(idioma.getProperty("atras"));
        btnAgregar.setText(idioma.getProperty("agregar"));
        btnEliminar.setText(idioma.getProperty("eliminar"));
        btnModificar.setText(idioma.getProperty("modificar"));

        lblEmail.setText(idioma.getProperty("email"));
        lblNombre.setText(idioma.getProperty("nombre"));
        lblIdTrabajador.setText(idioma.getProperty("idtrabajador"));
        lblTelefono.setText(idioma.getProperty("telefono"));
        lblCargo.setText(idioma.getProperty("cargo"));
        lblContaco.setText(idioma.getProperty("contacto"));
        lblCif.setText(idioma.getProperty("cif"));
    }
}

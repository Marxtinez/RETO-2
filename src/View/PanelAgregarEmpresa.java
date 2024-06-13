package View;

import Controller.ControladorConexion;
import Controller.ControladorEmpresa;
import Controller.ControladorPanelPrincipal;
import Model.Idioma;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelAgregarEmpresa extends JPanel {
    public JTextField txtCif,txtNombre,txtDireccion,txtTecnologias,txtSector,txtTelefono,txtEmpleados,txtUltColab;
    public static JButton btnAtras,btnAgregar;
    public static JLabel lblCif,lblNombre,lblDireccion,lblTecnologias,lblSector,lblTelefono,lblEmpleados,lblUltColab,lblResultado;
    public PanelAgregarEmpresa() {
        Idioma idioma = new Idioma(PanelConfiguracion.comboIdioma.getSelectedIndex());

        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        btnAtras = new JButton(idioma.getProperty("atras"));
        btnAgregar = new JButton(idioma.getProperty("agregar"));

        lblCif = new JLabel(idioma.getProperty("cif"));
        lblDireccion = new JLabel(idioma.getProperty("direccion"));
        lblEmpleados = new JLabel(idioma.getProperty("empleados"));
        lblSector = new JLabel(idioma.getProperty("sector"));
        lblTecnologias = new JLabel(idioma.getProperty("tecno"));
        lblTelefono = new JLabel(idioma.getProperty("telefono"));
        lblNombre = new JLabel(idioma.getProperty("nombre"));
        lblUltColab = new JLabel(idioma.getProperty("anioColab"));

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new BoxLayout(panelBotones,BoxLayout.X_AXIS));

        JPanel panelForm = new JPanel();
        panelForm.setLayout(new BoxLayout(panelForm,BoxLayout.X_AXIS));

        JPanel panelIzq = new JPanel();
        panelIzq.setLayout(new BoxLayout(panelIzq,BoxLayout.Y_AXIS));

        JPanel panelDer = new JPanel();
        panelDer.setLayout(new BoxLayout(panelDer,BoxLayout.Y_AXIS));

        txtCif = new JTextField();
        txtDireccion = new JTextField();
        txtEmpleados = new JTextField();
        txtSector = new JTextField();
        txtTecnologias = new JTextField();
        txtTelefono = new JTextField();
        txtNombre = new JTextField();
        txtUltColab = new JTextField();

        lblResultado = new JLabel();
        lblResultado.setAlignmentX(CENTER_ALIGNMENT);

        btnAtras.addActionListener(e-> ControladorPanelPrincipal.panelAntiguo());
        btnAgregar.addActionListener(e->{
                ControladorEmpresa.agregaEmpresa(txtCif.getText(),txtNombre.getText(),txtDireccion.getText(),txtTecnologias.getText(),txtSector.getText(),txtTelefono.getText(),Integer.valueOf(txtEmpleados.getText()),Integer.valueOf(txtUltColab.getText()),PanelOpcionesTutor.panelConsultaEmpresaTutor);
                txtCif.setText("");
                txtDireccion.setText("");
                txtNombre.setText("");
                txtEmpleados.setText("");
                txtSector.setText("");
                txtTecnologias.setText("");
                txtTelefono.setText("");
                txtUltColab.setText("");
                lblResultado.setText(idioma.getProperty("agregarEmpresa"));
                Timer timer = new Timer(2500, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        lblResultado.setText("");
                    }
                });
                timer.setRepeats(false);
                timer.start();
        });

        panelBotones.add(btnAgregar);
        panelBotones.add(Box.createHorizontalStrut(10));
        panelBotones.add(btnAtras);

        add(Box.createVerticalStrut(40));
        panelIzq.add(lblCif);
        panelIzq.add(txtCif);
        panelIzq.add(Box.createVerticalStrut(10));
        panelIzq.add(lblNombre);
        panelIzq.add(txtNombre);
        panelIzq.add(Box.createVerticalStrut(10));
        panelIzq.add(lblDireccion);
        panelIzq.add(txtDireccion);
        panelIzq.add(Box.createVerticalStrut(10));
        panelIzq.add(lblTelefono);
        panelIzq.add(txtTelefono);
        panelDer.add(lblSector);
        panelDer.add(txtSector);
        panelDer.add(Box.createVerticalStrut(10));
        panelDer.add(lblTecnologias);
        panelDer.add(txtTecnologias);
        panelDer.add(Box.createVerticalStrut(10));
        panelDer.add(lblEmpleados);
        panelDer.add(txtEmpleados);
        panelDer.add(Box.createVerticalStrut(10));
        panelDer.add(lblUltColab);
        panelDer.add(txtUltColab);

        panelForm.add(panelIzq);
        panelForm.add(Box.createHorizontalStrut(30));
        panelForm.add(panelDer);

        add(panelForm);
        add(Box.createVerticalStrut(10));
        add(lblResultado);
        add(Box.createVerticalStrut(10));
        add(panelBotones);
    }
    public static void actualizaIdioma(int newLang){
        Idioma idioma = new Idioma(newLang);

        btnAtras.setText(idioma.getProperty("atras"));
        btnAgregar.setText(idioma.getProperty("agregar"));
        lblCif.setText(idioma.getProperty("cif"));
        lblDireccion.setText(idioma.getProperty("direccion"));
        lblEmpleados.setText(idioma.getProperty("empleados"));
        lblSector.setText(idioma.getProperty("sector"));
        lblTecnologias.setText(idioma.getProperty("tecno"));
        lblTelefono.setText(idioma.getProperty("telefono"));
        lblNombre.setText(idioma.getProperty("nombre"));
        lblUltColab.setText(idioma.getProperty("anioColab"));

    }


}
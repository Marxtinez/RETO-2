package View;

import Controller.ControladorPanelPrincipal;
import Controller.ControladorTrabajador;
import Controller.ControladorTutorFCT;
import Model.Idioma;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class PanelAgregarTrabajador extends JPanel {
    JTextField txtIdTrabajador,txtNombre,txtTelefono,txtEmail,txtCargo,txtCif;
    JCheckBox checkContaco;
    JLabel lblIdTrabajador,lblNombre,lblTelefono,lblEmail,lblCargo,lblContaco,lblCif,lblResultado;
    JButton btnAgregar,btnAtras;

    public PanelAgregarTrabajador() throws SQLException {
        Idioma idioma = new Idioma(Idioma.spanish);

        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        btnAtras = new JButton(idioma.getProperty("atras"));
        btnAgregar = new JButton(idioma.getProperty("agregar"));

        lblTelefono = new JLabel(idioma.getProperty("telefono"));
        lblEmail = new JLabel(idioma.getProperty("email"));
        lblIdTrabajador = new JLabel(idioma.getProperty("idtrabajador"));
        lblNombre = new JLabel(idioma.getProperty("nombre"));
        lblCargo = new JLabel(idioma.getProperty("cargo"));
        lblCif = new JLabel(idioma.getProperty("cif"));
        lblContaco = new JLabel(idioma.getProperty("contacto"));

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new BoxLayout(panelBotones,BoxLayout.X_AXIS));

        JPanel panelFormX = new JPanel();
        panelFormX.setLayout(new BoxLayout(panelFormX,BoxLayout.X_AXIS));

        JPanel panelFormIzq = new JPanel();
        panelFormIzq.setLayout(new BoxLayout(panelFormIzq,BoxLayout.Y_AXIS));

        JPanel panelFormDer = new JPanel();
        panelFormDer.setLayout(new BoxLayout(panelFormDer,BoxLayout.Y_AXIS));

        txtEmail = new JTextField();
        txtIdTrabajador = new JTextField(String.valueOf(ControladorTrabajador.siguienteValor()+1));
        txtNombre= new JTextField();
        txtTelefono = new JTextField();
        txtCargo = new JTextField();
        checkContaco = new JCheckBox();
        txtCif= new JTextField();

        txtIdTrabajador.setEditable(false);

        lblResultado = new JLabel();
        lblResultado.setAlignmentX(CENTER_ALIGNMENT);

        btnAtras.addActionListener(e-> ControladorPanelPrincipal.panelAntiguo());
        btnAgregar.addActionListener(e->{
            ControladorTrabajador.insertarTrabajador(txtEmail.getText(),txtNombre.getText(),txtTelefono.getText(),txtCargo.getText(),checkContaco.isSelected(),txtCif.getText(),PanelOpcionesTutor.panelConsultaTrabajadorTutor);
            txtCargo.setText("");
            txtCif.setText("");
            txtEmail.setText("");
            txtTelefono.setText("");
            txtNombre.setText("");
            try {
                txtIdTrabajador.setText(String.valueOf(ControladorTrabajador.siguienteValor()+1));
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            lblResultado.setText("Trabajador añadido correctamente");
            Timer timer = new Timer(2500, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    lblResultado.setText("");
                }
            });
            timer.setRepeats(false);
            timer.start();
        });

        panelBotones.add(Box.createHorizontalStrut(50));
        panelBotones.add(btnAgregar);
        panelBotones.add(Box.createHorizontalStrut(10));
        panelBotones.add(btnAtras);
        panelBotones.add(Box.createHorizontalStrut(50));

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
        panelFormDer.add(Box.createVerticalStrut(60));

        panelFormX.add(panelFormIzq);
        panelFormX.add(Box.createHorizontalStrut(20));
        panelFormX.add(panelFormDer);

        add(Box.createVerticalStrut(40));
        add(panelFormX);
        add(Box.createVerticalStrut(10));
        add(lblResultado);
        add(Box.createVerticalStrut(10));
        add(panelBotones);

    }
}
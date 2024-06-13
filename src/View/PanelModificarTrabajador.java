package View;

import Controller.ControladorPanelPrincipal;
import Controller.ControladorTrabajador;
import Model.Idioma;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class PanelModificarTrabajador extends JPanel {
    JTextField txtIdTrabajador,txtNombre,txtTelefono,txtEmail,txtCargo,txtCif;
    JCheckBox checkContaco;
    static JLabel lblIdTrabajador,lblNombre,lblTelefono,lblEmail,lblCargo,lblContaco,lblCif,lblResultado;
    static JButton btnMod,btnAtras;

    public PanelModificarTrabajador() throws SQLException {
        Idioma idioma = new Idioma(PanelConfiguracion.comboIdioma.getSelectedIndex());
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        btnAtras = new JButton(idioma.getProperty("atras"));
        btnMod = new JButton(idioma.getProperty("modificar"));

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
        txtIdTrabajador = new JTextField();
        txtNombre= new JTextField();
        txtTelefono = new JTextField();
        txtCargo = new JTextField();
        checkContaco = new JCheckBox();
        txtCif= new JTextField();

        txtIdTrabajador.setEditable(false);

        lblResultado = new JLabel();
        lblResultado.setAlignmentX(CENTER_ALIGNMENT);

        btnAtras.addActionListener(e-> ControladorPanelPrincipal.panelAntiguo());
        btnMod.addActionListener(e->{
            try {
                ControladorTrabajador.actualizarTrabajador(Integer.valueOf(txtIdTrabajador.getText()),txtEmail.getText(),txtNombre.getText(),txtTelefono.getText(),txtCargo.getText(),checkContaco.isSelected(),txtCif.getText(),PanelOpcionesTutor.panelConsultaTrabajadorTutor);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            lblResultado.setText("Se ha modificado el trabajador correctamente");
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
        panelBotones.add(btnMod);
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
    public static void actualizaIdioma(int newLang){
        Idioma idioma = new Idioma(newLang);

        btnAtras.setText(idioma.getProperty("atras"));
        btnMod.setText(idioma.getProperty("modificar"));

        lblTelefono.setText(idioma.getProperty("telefono"));
        lblEmail.setText(idioma.getProperty("email"));
        lblIdTrabajador.setText(idioma.getProperty("idtrabajador"));
        lblNombre.setText(idioma.getProperty("nombre"));
        lblCargo.setText(idioma.getProperty("cargo"));
        lblCif.setText(idioma.getProperty("cif"));
        lblContaco.setText(idioma.getProperty("contacto"));
    }
}
package View;

import Controller.ControladorFCT;
import Controller.ControladorPanelPrincipal;
import Controller.ControladorTutorFCT;
import Model.Idioma;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class PanelModificarTutor extends JPanel{
    JTextField txtIdTutor, txtNombre, txtEmail, txtTelefono;
    JLabel lblIdTutor, lblNombre, lblEmail, lblTelefono,lblResultado;
    JButton btnMod,btnAtras;
    public PanelModificarTutor() throws SQLException {
        Idioma idioma = new Idioma(Idioma.spanish);

        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        btnAtras = new JButton(idioma.getProperty("atras"));
        btnMod = new JButton(idioma.getProperty("modificar"));

        lblTelefono = new JLabel(idioma.getProperty("telefono"));
        lblEmail = new JLabel(idioma.getProperty("email"));
        lblIdTutor = new JLabel(idioma.getProperty("idtutor"));
        lblNombre = new JLabel(idioma.getProperty("nombre"));

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new BoxLayout(panelBotones,BoxLayout.X_AXIS));

        JPanel panelFormX = new JPanel();
        panelFormX.setLayout(new BoxLayout(panelFormX,BoxLayout.X_AXIS));

        JPanel panelFormY = new JPanel();
        panelFormY.setLayout(new BoxLayout(panelFormY,BoxLayout.Y_AXIS));

        txtTelefono = new JTextField();
        txtEmail = new JTextField();
        txtIdTutor = new JTextField();
        txtNombre = new JTextField();

        txtIdTutor.setEditable(false);

        lblResultado = new JLabel();
        lblResultado.setAlignmentX(CENTER_ALIGNMENT);

        btnAtras.addActionListener(e-> ControladorPanelPrincipal.panelAntiguo());
        btnMod.addActionListener(e->{
            try {
                ControladorTutorFCT.modificaTutor(Integer.valueOf(txtIdTutor.getText()),txtEmail.getText(),txtNombre.getText(),txtTelefono.getText(),PanelOpcionesTutor.panelConsultaTutoresTutor);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            lblResultado.setText("Se ha modificado el tutor correctamente");
            Timer timer = new Timer(2500, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    lblResultado.setText("");
                }
            });
            timer.setRepeats(false);
            timer.start();
        });

        panelBotones.add(btnMod);
        panelBotones.add(Box.createHorizontalStrut(10));
        panelBotones.add(btnAtras);

        add(Box.createVerticalStrut(35));
        panelFormY.add(lblIdTutor);
        panelFormY.add(txtIdTutor);
        panelFormY.add(Box.createVerticalStrut(10));
        panelFormY.add(lblNombre);
        panelFormY.add(txtNombre);
        panelFormY.add(Box.createVerticalStrut(10));
        panelFormY.add(lblEmail);
        panelFormY.add(txtEmail);
        panelFormY.add(Box.createVerticalStrut(10));
        panelFormY.add(lblTelefono);
        panelFormY.add(txtTelefono);

        panelFormX.add(panelFormY);
        add(panelFormX);
        add(Box.createVerticalStrut(10));
        add(lblResultado);
        add(Box.createVerticalStrut(10));
        add(panelBotones);

    }
}
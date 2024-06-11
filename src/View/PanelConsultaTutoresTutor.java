package View;

import Controller.ControladorFCT;
import Controller.ControladorPanelPrincipal;
import Controller.ControladorTutorFCT;
import Model.FCT;
import Model.Idioma;
import Model.TutorFCT;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class PanelConsultaTutoresTutor extends JPanel {
    public JComboBox<TutorFCT> comboTutores = new JComboBox<>();
    JTextField txtIdTutor,txtNombre,txtTelefono,txtEmail;
    JLabel lblIdTutor,lblNombre,lblTelefono,lblEmail;
    JButton btnAgregar,btnEliminar,btnModificar,btnAtras;
    PanelAgregarTutor panelAgregarTutor = new PanelAgregarTutor();
    PanelModificarTutor panelModificarTutor = new PanelModificarTutor();

    public PanelConsultaTutoresTutor() throws SQLException {
        Idioma idioma = new Idioma(Idioma.spanish);

        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        btnAtras = new JButton(idioma.getProperty("atras"));
        btnAgregar = new JButton(idioma.getProperty("agregar"));
        btnEliminar = new JButton(idioma.getProperty("eliminar"));
        btnModificar = new JButton(idioma.getProperty("modificar"));

        lblEmail = new JLabel(idioma.getProperty("email"));
        lblNombre = new JLabel(idioma.getProperty("nombre"));
        lblIdTutor = new JLabel(idioma.getProperty("idtutor"));
        lblTelefono = new JLabel(idioma.getProperty("telefono"));

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new BoxLayout(panelBotones,BoxLayout.X_AXIS));

        JPanel panelFormX = new JPanel();
        panelFormX.setLayout(new BoxLayout(panelFormX,BoxLayout.X_AXIS));

        JPanel panelFormY = new JPanel();
        panelFormY.setLayout(new BoxLayout(panelFormY,BoxLayout.Y_AXIS));

        ControladorTutorFCT.cargaContenidoTutores();
        for (TutorFCT tutor : ControladorTutorFCT.tutores){
            comboTutores.addItem(tutor);
        }

        txtEmail = new JTextField(ControladorTutorFCT.tutores.get(0).getEmail());
        txtIdTutor = new JTextField(String.valueOf(ControladorTutorFCT.tutores.get(0).getId_tutor()));
        txtNombre= new JTextField(ControladorTutorFCT.tutores.get(0).getNombre());
        txtTelefono = new JTextField(ControladorTutorFCT.tutores.get(0).getTelefono());

        txtTelefono.setEditable(false);
        txtNombre.setEditable(false);
        txtIdTutor.setEditable(false);
        txtEmail.setEditable(false);

        comboTutores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TutorFCT tutorSeleccionado = (TutorFCT) comboTutores.getSelectedItem();
                txtTelefono.setText(tutorSeleccionado.getTelefono());
                txtNombre.setText(tutorSeleccionado.getNombre());
                txtEmail.setText(tutorSeleccionado.getEmail());
                txtIdTutor.setText(String.valueOf(tutorSeleccionado.getId_tutor()));
            }
        });

        btnAtras.addActionListener(e-> ControladorPanelPrincipal.panelAntiguo());
        btnAgregar.addActionListener(e-> {
            ControladorPanelPrincipal.nuevoPanelActivo(panelAgregarTutor);});
        btnEliminar.addActionListener(e->{
            try {
                TutorFCT tutorSeleccionado = (TutorFCT) comboTutores.getSelectedItem();
                ControladorTutorFCT.eliminaTutor(tutorSeleccionado.getId_tutor());
                comboTutores.removeItem(tutorSeleccionado);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        btnModificar.addActionListener(e->{
            panelModificarTutor.txtIdTutor.setText(txtIdTutor.getText());
            panelModificarTutor.txtEmail.setText(txtEmail.getText());
            panelModificarTutor.txtNombre.setText(txtNombre.getText());
            panelModificarTutor.txtTelefono.setText(txtTelefono.getText());
            ControladorPanelPrincipal.nuevoPanelActivo(panelModificarTutor);
        });

        panelBotones.add(btnAgregar);
        panelBotones.add(Box.createHorizontalStrut(10));
        panelBotones.add(btnEliminar);
        panelBotones.add(Box.createHorizontalStrut(10));
        panelBotones.add(btnModificar);
        panelBotones.add(Box.createHorizontalStrut(10));
        panelBotones.add(btnAtras);

        add(Box.createVerticalStrut(20));
        add(comboTutores);
        add(Box.createVerticalStrut(20));
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
        add(Box.createVerticalStrut(20));
        add(panelBotones);
    }
}

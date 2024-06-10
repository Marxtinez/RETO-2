package View;

import Controller.ControladorEmpresa;
import Controller.ControladorPanelPrincipal;
import Model.Empresa;
import Model.Idioma;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class PanelConsultaEmpresaTutor extends JPanel {
    public JComboBox<Empresa> comboEmpresa = new JComboBox<>();
    JTextField txtCif,txtNombre,txtDireccion,txtTecnologias,txtSector,txtTelefono,txtEmpleados,txtUltColab;
    JButton btnAtras,btnModificar,btnAgregar,btnEliminar;
    JLabel lblCif,lblNombre,lblDireccion,lblTecnologias,lblSector,lblTelefono,lblEmpleados,lblUltColab;
    PanelAgregarEmpresa panelAgregarEmpresa = new PanelAgregarEmpresa();
    PanelModificarEmpresa panelModificarEmpresa;
    public String cifMod;

    public PanelConsultaEmpresaTutor() throws SQLException {
        Idioma idioma = new Idioma(Idioma.spanish);

        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        btnAtras = new JButton(idioma.getProperty("atras"));
        btnAgregar = new JButton(idioma.getProperty("agregar"));
        btnEliminar = new JButton(idioma.getProperty("eliminar"));
        btnModificar = new JButton(idioma.getProperty("modificar"));

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

        ControladorEmpresa.cargaContenidoEmpresas();

        for (Empresa empresa : ControladorEmpresa.empresas){
            comboEmpresa.addItem(empresa);
        }

        txtCif = new JTextField(ControladorEmpresa.empresas.get(0).getCIF());
        txtDireccion = new JTextField(ControladorEmpresa.empresas.get(0).getDireccion());
        txtEmpleados = new JTextField(String.valueOf(ControladorEmpresa.empresas.get(0).getNum_empleados()));
        txtSector = new JTextField(ControladorEmpresa.empresas.get(0).getSector());
        txtTecnologias = new JTextField(ControladorEmpresa.empresas.get(0).getTecnologias());
        txtTelefono = new JTextField(ControladorEmpresa.empresas.get(0).getTelefono());
        txtNombre = new JTextField(ControladorEmpresa.empresas.get(0).getNombre());
        txtUltColab = new JTextField(String.valueOf(ControladorEmpresa.empresas.get(0).getUlt_anio_colab()));

        txtUltColab.setEditable(false);
        txtSector.setEditable(false);
        txtCif.setEditable(false);
        txtNombre.setEditable(false);
        txtEmpleados.setEditable(false);
        txtDireccion.setEditable(false);
        txtTecnologias.setEditable(false);
        txtTelefono.setEditable(false);

        comboEmpresa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Empresa empresaSeleccionada = (Empresa) comboEmpresa.getSelectedItem();
                txtCif.setText(empresaSeleccionada.getCIF());
                txtNombre.setText(empresaSeleccionada.getNombre());
                txtDireccion.setText(empresaSeleccionada.getDireccion());
                txtTelefono.setText(empresaSeleccionada.getTelefono());
                txtEmpleados.setText(String.valueOf(empresaSeleccionada.getNum_empleados()));
                txtSector.setText(empresaSeleccionada.getSector());
                txtTecnologias.setText(empresaSeleccionada.getTecnologias());
                txtUltColab.setText(String.valueOf(empresaSeleccionada.getUlt_anio_colab()));
            }
        });

        btnAtras.addActionListener(e-> ControladorPanelPrincipal.panelAntiguo());
        btnEliminar.addActionListener(e-> {
            try {
                Empresa empresaSeleccionada = (Empresa) comboEmpresa.getSelectedItem();
                ControladorEmpresa.eliminaEmpresa(empresaSeleccionada.getCIF());
                comboEmpresa.removeItem(empresaSeleccionada);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        btnAgregar.addActionListener(e->ControladorPanelPrincipal.nuevoPanelActivo(panelAgregarEmpresa));

        panelModificarEmpresa = new PanelModificarEmpresa();
        btnModificar.addActionListener(e->{panelModificarEmpresa.txtCif.setText(txtCif.getText());
            panelModificarEmpresa.txtDireccion.setText(txtDireccion.getText());
            panelModificarEmpresa.txtEmpleados.setText(txtEmpleados.getText());
            panelModificarEmpresa.txtNombre.setText(txtNombre.getText());
            panelModificarEmpresa.txtTecnologias.setText(txtTecnologias.getText());
            panelModificarEmpresa.txtTelefono.setText(txtTelefono.getText());
            panelModificarEmpresa.txtSector.setText(txtSector.getText());
            panelModificarEmpresa.txtUltColab.setText(txtUltColab.getText());
            cifMod = txtCif.getText();
            ControladorPanelPrincipal.nuevoPanelActivo(panelModificarEmpresa);});

        panelBotones.add(btnAgregar);
        panelBotones.add(Box.createHorizontalStrut(10));
        panelBotones.add(btnEliminar);
        panelBotones.add(Box.createHorizontalStrut(10));
        panelBotones.add(btnModificar);
        panelBotones.add(Box.createHorizontalStrut(10));
        panelBotones.add(btnAtras);

        add(Box.createVerticalStrut(20));
        add(comboEmpresa);
        add(Box.createVerticalStrut(20));
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
        add(Box.createVerticalStrut(20));
        add(panelBotones);

    }
}

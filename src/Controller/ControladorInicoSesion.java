package Controller;

import Model.RegistroUsuarios;
import View.*;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class ControladorInicoSesion {
    public static RegistroUsuarios registroUsuarios = new RegistroUsuarios();
    public static PanelOpcionesTutor panelOpcionesTutor;
    public static PanelOpcionesProf panelOpcionesProf;

    static {
        try {
            panelOpcionesTutor = new PanelOpcionesTutor();
            panelOpcionesProf = new PanelOpcionesProf();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean comprobarRegistroTutores(PanelInicioSesionTutor panel) {
        String usuario = panel.txtNombre.getText();
        String password = panel.txtPassword.getText();

        for (String clave : registroUsuarios.tutores.keySet()){
            if (clave.equals(usuario) && registroUsuarios.tutores.get(clave).equals(password)){
                return true;
            }
        }
        return false;
    }
    public static void cambioPanelTutor(PanelInicioSesionTutor panel) {
        if (comprobarRegistroTutores(panel)){
            ControladorPanelPrincipal.nuevoPanelActivo(panelOpcionesTutor);
        }else {
            new VentanaAviso("Usuario o Contraseña incorrectos");
        }
    }

    public static boolean comprobarRegistroProf(PanelInicioSesionProf panel) {
        String usuario = panel.txtNombre.getText();
        String password = panel.txtPassword.getText();

        for (String clave : registroUsuarios.profesores.keySet()){
            if (clave.equals(usuario) && registroUsuarios.profesores.get(clave).equals(password)){
                return true;
            }
        }
        return false;
    }
    public static void cambioPanelProf(PanelInicioSesionProf panel) {
        if (comprobarRegistroProf(panel)){
            ControladorPanelPrincipal.nuevoPanelActivo(panelOpcionesProf);
        }else {
            new VentanaAviso("Usuario o Contraseña incorrectos");
        }
    }
}
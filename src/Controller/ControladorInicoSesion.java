package Controller;

import Model.RegistroUsuarios;
import View.PanelInicioSesionTutor;
import View.PanelOpcionesTutor;
import View.VentanaAviso;

import javax.swing.*;
import java.awt.*;

public class ControladorInicoSesion {
    public static RegistroUsuarios registroUsuarios = new RegistroUsuarios();
    public static PanelOpcionesTutor panelOpcionesTutor = new PanelOpcionesTutor();

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
}
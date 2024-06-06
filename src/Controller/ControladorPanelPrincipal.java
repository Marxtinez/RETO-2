package Controller;

import javax.swing.*;
import java.util.Stack;

public class ControladorPanelPrincipal {
    static JPanel panelPadre, panelActivo;
    static Stack<JPanel> listaPaneles = new Stack<>();
    public ControladorPanelPrincipal(JPanel panel1, JPanel panel2){
        panelPadre = panel1;
        panelActivo = panel2;
        panelPadre.add(panelActivo);
        listaPaneles.add(panelActivo);

    }
    public static void nuevoPanelActivo(JPanel nuevoPanel){
        panelPadre.remove(panelActivo);
        panelPadre.add(nuevoPanel);
        panelPadre.revalidate();
        panelPadre.repaint();
        panelActivo = nuevoPanel;
        listaPaneles.add(panelActivo);
    }
    public static void panelAntiguo(){
        if (listaPaneles.size()>1){
            listaPaneles.pop();
            panelPadre.remove(panelActivo);
            panelActivo = listaPaneles.peek();
            panelPadre.add(panelActivo);
            panelPadre.revalidate();
            panelPadre.repaint();
        }
    }
}
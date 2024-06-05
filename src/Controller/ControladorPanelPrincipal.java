package Controller;

import javax.swing.*;

public class ControladorPanelPrincipal {
    static JPanel panelPadre, panelActivo, panelAntiguo;
    public ControladorPanelPrincipal(JPanel panel1, JPanel panel2){
        panelPadre = panel1;
        panelActivo = panel2;
        panelPadre.add(panelActivo);

    }
    public static void nuevoPanelActivo(JPanel nuevoPanel){
        panelPadre.remove(panelActivo);
        panelPadre.add(nuevoPanel);
        panelPadre.revalidate();
        panelPadre.repaint();
        panelAntiguo = panelActivo;
        panelActivo = nuevoPanel;
    }
    public static void PanelAntiguo(){
        panelPadre.remove(panelActivo);
        panelPadre.add(panelAntiguo);
        panelPadre.revalidate();
        panelPadre.repaint();
        panelActivo = panelAntiguo;
    }
}
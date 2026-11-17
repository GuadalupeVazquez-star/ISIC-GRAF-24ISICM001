/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ejercicio1_graf;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author BICHIYAL
 */

public class Ejercicio1_GRAF extends JPanel {
    // Coordenadas originales del triángulo
    //int[] xOrig = {50, 100, 150};
    //int[] yOrig = {150, 50, 150};
    int[] xOrig = {40, 90, 170}; //(cambio2)
    int[] yOrig = {180, 40, 180}; //(cambio2)
    
    // Vector de traslación
    //int tx = 180;
    //int ty = 70;
    int tx = 200; //(cambio1)
    int ty = -30; //(cambio1)

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // 1. Dibujar figura original
        g.setColor(Color.LIGHT_GRAY);
        g.fillPolygon(xOrig, yOrig, 3);
        g.setColor(Color.BLACK);
        g.drawString("Original", xOrig[0], yOrig[0] + 15);

        // 2. Calcular nuevos vértices manualmente
        int[] xTras = new int[3];
        int[] yTras = new int[3];
        for (int i = 0; i < 3; i++) {
            xTras[i] = xOrig[i] + tx;
            yTras[i] = yOrig[i] + ty;
        }

        // 3. Dibujar figura trasladada
        //g.setColor(new Color(41, 128, 185));
        g.setColor(new Color(231, 76, 60)); //(cambio2)
        g.fillPolygon(xTras, yTras, 3);
        g.drawString("Trasladada (" + tx + ", " + ty + ")", xTras[0], yTras[0] + 15);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Traslación - Nivel Básico");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new Ejercicio1_GRAF());
        frame.setSize(450, 350);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
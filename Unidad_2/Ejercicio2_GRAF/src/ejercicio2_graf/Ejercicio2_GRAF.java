/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ejercicio2_graf;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author BICHIYAL
 */
public class Ejercicio2_GRAF extends JPanel {
    int[] xOrig = {80, 140, 140, 80};
    int[] yOrig = {80, 80, 140, 140};
    //double sx = 1.8, sy = 1.8;
    double sx = 2.5, sy = 0.8; //(cambio1)

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Original
        g.setColor(Color.BLACK);
        g.drawPolygon(xOrig, yOrig, 4);
        g.drawString("Original", 80, 75);

        // Escalamiento respecto al origen (0,0) -> Se aleja del origen
        int[] xOrigen = new int[4];
        int[] yOrigen = new int[4];
        for (int i = 0; i < 4; i++) {
            xOrigen[i] = (int) (xOrig[i] * sx);
            yOrigen[i] = (int) (yOrig[i] * sy);
        }
        g.setColor(Color.RED);
        g.drawPolygon(xOrigen, yOrigen, 4);
        g.drawString("Respecto a (0,0)", xOrigen[0], yOrigen[0] - 5);

        // Escalamiento respecto al centroide (xf=110, yf=110) -> Se mantiene en su sitio
        //int xf = 110, yf = 110;
        int xf = 80, yf = 80; //(cambio2)
        int[] xFijo = new int[4];
        int[] yFijo = new int[4];
        for (int i = 0; i < 4; i++) {
            xFijo[i] = (int) (xf + (xOrig[i] - xf) * sx);
            yFijo[i] = (int) (yf + (yOrig[i] - yf) * sy);
        }
        //g.setColor(new Color(39, 174, 96));
        g.setColor(new Color(155, 89, 182)); //(cambio2)
        g.drawPolygon(xFijo, yFijo, 4);
        g.drawString("Respecto al centro", xFijo[0], yFijo[2] + 15);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Escalamiento - Nivel Básico");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new Ejercicio2_GRAF());
        frame.setSize(450, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ejercicio3_graf;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author BICHIYAL
 */
public class Ejercicio3_GRAF extends JPanel {
    int[] xOrig = {200, 320, 320, 200};
    int[] yOrig = {180, 180, 210, 210};
    int xp = 200, yp = 195; // Punto pivote
    double anguloGrados = 45.0;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Original
        g.setColor(Color.GRAY);
        g.drawPolygon(xOrig, yOrig, 4);

        // Rotación manual usando Math.toRadians
        double rad = Math.toRadians(anguloGrados);
        double cos = Math.cos(rad);
        double sin = Math.sin(rad);

        int[] xRot = new int[4];
        int[] yRot = new int[4];

        for (int i = 0; i < 4; i++) {
            int dx = xOrig[i] - xp;
            int dy = yOrig[i] - yp;
            xRot[i] = (int) Math.round(xp + (dx * cos - dy * sin));
            yRot[i] = (int) Math.round(yp + (dx * sin + dy * cos));
        }

        g.setColor(new Color(230, 126, 34));
        g.fillPolygon(xRot, yRot, 4);

        // Dibujar pivote
        g.setColor(Color.RED);
        g.fillOval(xp - 4, yp - 4, 8, 8);
        g.drawString("Pivote (" + xp + "," + yp + ") | Rotación: 45°", 20, 30);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Rotación - Nivel Básico");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new Ejercicio3_GRAF());
        frame.setSize(450, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
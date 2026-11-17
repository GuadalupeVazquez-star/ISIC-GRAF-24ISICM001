/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ejercicio4_graf;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author BICHIYAL
 */
public class Ejercicio4_GRAF extends JPanel {
    int[] xOrig = {100, 200, 200, 100};
    int[] yOrig = {200, 200, 100, 100};
    //double shx = 0.6; // Factor de sesgado en X
    double shx = -1.0; // izquierda y más inclinado (cambio1)
    double shy = 0.4; // Nuevo: Factor de sesgado en Y (cambio2)

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Original
        g.setColor(Color.LIGHT_GRAY);
        g.fillPolygon(xOrig, yOrig, 4);

        // Sesgado respecto a la base y = 200
        int yRef = 200;
        int[] xShear = new int[4];
        int[] yShear = new int[4];

        for (int i = 0; i < 4; i++) {
            // Desplazamiento proporcional a la altura respecto a yRef
            //xShear[i] = (int) (xOrig[i] + shx * (yRef - yOrig[i]));
            //yShear[i] = yOrig[i];
            int xRef = 100; // referencia en X (cambio2)
            xShear[i] = (int) (xOrig[i] + shx * (yRef - yOrig[i])); //(cambio2)
            yShear[i] = (int) (yOrig[i] + shy * (xOrig[i] - xRef)); //(cambio2)
        }

        g.setColor(new Color(26, 188, 156, 180));
        g.fillPolygon(xShear, yShear, 4);
        g.setColor(Color.BLACK);
        g.drawPolygon(xShear, yShear, 4);
        //g.drawString("Sesgado Horizontal (shx = " + shx + ")", 100, 85);
        g.drawString("Sesgado Horizontal: " + shx + " y V: " + shy, 100, 85); //(cambio2)
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Sesgado - Nivel Básico");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new Ejercicio4_GRAF());
        frame.setSize(450, 320);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

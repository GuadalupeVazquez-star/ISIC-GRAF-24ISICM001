/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ejercicio7_graf;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;

/**
 *
 * @author BICHIYAL
 */
public class Ejercicio7_GRAF extends JFrame {
    //private double theta1 = -30; // Ángulo hombro
    //private double theta2 = 45;  // Ángulo codo
    private double theta1 = -90; // Ángulo hombro (CAMBIO1)
    private double theta2 = 0;  // Ángulo codo  (CAMBIO1)

    public Ejercicio7_GRAF() {
        setTitle("Rotación Jerárquica - Brazo Articulado 2D");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(650, 500);
        setLayout(new BorderLayout());

        JPanel canvas = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                int baseX = getWidth() / 2;
                int baseY = getHeight() - 100;
                //int largoBrazo = 130;
                //int largoAntebrazo = 100;
                int largoBrazo = 170; //(CAMBIO1)
                int largoAntebrazo = 130; //(CAMBIO1)

                // Base fija
                g2d.setColor(Color.DARK_GRAY);
                g2d.fillRect(baseX - 40, baseY, 80, 20);

                // Guardar matriz identidad original
                AffineTransform inicial = g2d.getTransform();

                // --- ESLABÓN 1 (Hombro) ---
                g2d.translate(baseX, baseY);
                g2d.rotate(Math.toRadians(theta1));
                //g2d.setColor(new Color(52, 152, 219));
                g2d.setColor(new Color(142, 68, 173));//(CAMBIO2)
                g2d.fillRoundRect(0, -12, largoBrazo, 24, 15, 15);
                g2d.setColor(Color.BLACK);
                g2d.fillOval(-6, -6, 12, 12); // Articulación 1

                // --- ESLABÓN 2 (Codo: hereda la transformación del hombro) ---
                g2d.translate(largoBrazo, 0);
                g2d.rotate(Math.toRadians(theta2));
                //g2d.setColor(new Color(46, 204, 113));
                g2d.setColor(new Color(241, 196, 15)); //(CAMBIO2)
                g2d.fillRoundRect(0, -9, largoAntebrazo, 18, 12, 12);
                g2d.setColor(Color.RED);
                g2d.fillOval(-5, -5, 10, 10); // Articulación 2

                // Efector final (pinza)
                g2d.translate(largoAntebrazo, 0);
                //g2d.setColor(Color.ORANGE);
                //g2d.fillOval(-7, -7, 14, 14);
                g2d.setColor(Color.RED); //(cambio2)
                g2d.fillOval(-12, -12, 24, 24); //(cambio2)

                // Restaurar contexto gráfico
                g2d.setTransform(inicial);
            }
        };

        JPanel sliders = new JPanel(new GridLayout(2, 2));
        //JSlider s1 = new JSlider(-180, 0, -30);
        //JSlider s2 = new JSlider(-150, 150, 45);
        JSlider s1 = new JSlider(-180, 0, -90); //(CAMBIO1)
        JSlider s2 = new JSlider(-150, 150, 0); //(CAMBIO1)
        s1.addChangeListener(e -> { theta1 = s1.getValue(); canvas.repaint(); });
        s2.addChangeListener(e -> { theta2 = s2.getValue(); canvas.repaint(); });

        sliders.add(new JLabel(" Ángulo Hombro (θ1):"));
        sliders.add(s1);
        sliders.add(new JLabel(" Ángulo Codo (θ2):"));
        sliders.add(s2);

        add(canvas, BorderLayout.CENTER);
        add(sliders, BorderLayout.SOUTH);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Ejercicio7_GRAF().setVisible(true));
    }
}

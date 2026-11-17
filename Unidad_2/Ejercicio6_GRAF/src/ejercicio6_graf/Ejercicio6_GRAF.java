/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ejercicio6_graf;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;

/**
 *
 * @author BICHIYAL
 */
public class Ejercicio6_GRAF extends JFrame {
    private double sx = 1.0, sy = 1.0;

    public Ejercicio6_GRAF() {
        setTitle("2.1.2 Escalamiento No Uniforme Anclado");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLayout(new BorderLayout());

        JPanel lienzo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                int cx = getWidth() / 2;
                int cy = getHeight() / 2;

                // Ejes de referencia
                g2d.setColor(Color.LIGHT_GRAY);
                g2d.drawLine(cx, 0, cx, getHeight());
                g2d.drawLine(0, cy, getWidth(), cy);

                // Figura base centrada en (0,0) local
                RoundRectangle2D rect = new RoundRectangle2D.Double(-50, -50, 100, 100, 20, 20);

                // Transformación compuesta: Trasladar al centro -> Escalar
                AffineTransform at = new AffineTransform();
                at.translate(cx, cy);
                at.scale(sx, sy);

                g2d.setColor(new Color(142, 68, 173, 180));
                g2d.fill(at.createTransformedShape(rect));
                g2d.setColor(Color.BLACK);
                g2d.draw(at.createTransformedShape(rect));
            }
        };

        JPanel controles = new JPanel(new GridLayout(2, 2));
        JSlider sliderX = new JSlider(20, 300, 100);
        JSlider sliderY = new JSlider(20, 300, 100);

        sliderX.addChangeListener(e -> { sx = sliderX.getValue() / 100.0; lienzo.repaint(); });
        sliderY.addChangeListener(e -> { sy = sliderY.getValue() / 100.0; lienzo.repaint(); });

        controles.add(new JLabel(" Escala X (Sx):"));
        controles.add(sliderX);
        controles.add(new JLabel(" Escala Y (Sy):"));
        controles.add(sliderY);

        add(lienzo, BorderLayout.CENTER);
        add(controles, BorderLayout.SOUTH);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Ejercicio6_GRAF().setVisible(true));
    }
}
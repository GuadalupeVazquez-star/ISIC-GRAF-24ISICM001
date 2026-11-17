/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ejercicio8_graf;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author BICHIYAL
 */
public class Ejercicio8_GRAF extends JPanel implements MouseMotionListener {
    private double shx = 0.5;
    private double shy = 0.0;

    public Ejercicio8_GRAF() {
        addMouseMotionListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int origenX = getWidth() / 2;
        int origenY = getHeight() / 2 + 50;

        // Línea de horizonte / suelo
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.drawLine(0, origenY, getWidth(), origenY);

        // 1. Dibujar sombra sesgada en el suelo
        AffineTransform atSombra = new AffineTransform();
        atSombra.translate(origenX, origenY);
        atSombra.shear(shx, 0);      // Inclinación controlada por el mouse
        atSombra.scale(1.0, 0.4);    // Aplastamiento de sombra

        g2d.setColor(new Color(50, 50, 50, 90));
        g2d.fillRect(-40, -120, 80, 120); // Bloque vertical (objeto)

        // Aplicar transformación de sombra al mismo bloque
        Shape bloque = new Rectangle(-40, -120, 80, 120);
        g2d.fill(atSombra.createTransformedShape(bloque));

        // 2. Objeto sólido frontal
        g2d.setColor(new Color(52, 73, 94));
        g2d.fill(AffineTransform.getTranslateInstance(origenX, origenY).createTransformedShape(bloque));

        // 3. Texto tipográfico con sesgado bidimensional (shx y shy)
        AffineTransform atTexto = new AffineTransform();
        atTexto.translate(40, 80);
        atTexto.shear(shx * 0.5, shy * 0.5);
        g2d.setFont(new Font("SansSerif", Font.BOLD, 28));
        g2d.setColor(new Color(192, 57, 43));
        g2d.drawString("GRAFICACIÓN 2D", 0, 0);

        g2d.setFont(new Font("SansSerif", Font.PLAIN, 12));
        g2d.setColor(Color.BLACK);
        g2d.drawString(String.format("Mueve el ratón | shx: %.2f, shy: %.2f", shx, shy), 20, 25);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // Normalizar posición del ratón respecto al centro (-1.5 a 1.5)
        shx = (e.getX() - getWidth() / 2.0) / 150.0;
        shy = (e.getY() - getHeight() / 2.0) / 300.0;
        repaint();
    }

    @Override public void mouseDragged(MouseEvent e) {}

    public static void main(String[] args) {
        JFrame frame = new JFrame("Sesgado - Sombra y Tipografía Dinámica");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new Ejercicio8_GRAF());
        frame.setSize(650, 450);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

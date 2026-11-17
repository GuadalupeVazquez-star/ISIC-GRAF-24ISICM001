/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ejercicio5_graf;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author BICHIYAL
 */
public class Ejercicio5_GRAF extends JPanel implements KeyListener {
    private double posX = 200, posY = 200;
    private final double PASO = 15.0;
    private ArrayList<Point2D.Double> historial = new ArrayList<>();

    public Ejercicio5_GRAF() {
        setFocusable(true);
        addKeyListener(this);
        historial.add(new Point2D.Double(posX, posY));
    }

    private Shape crearNave() {
        Path2D nave = new Path2D.Double();
        nave.moveTo(0, -25);
        nave.lineTo(20, 20);
        nave.lineTo(0, 10);
        nave.lineTo(-20, 20);
        nave.closePath();
        return nave;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Dibujar rastro de traslaciones
        g2d.setColor(new Color(200, 200, 200, 120));
        for (Point2D.Double p : historial) {
            AffineTransform atRastro = AffineTransform.getTranslateInstance(p.x, p.y);
            g2d.draw(atRastro.createTransformedShape(crearNave()));
        }

        // Aplicar traslación actual mediante matriz afín
        AffineTransform transform = new AffineTransform();
        transform.translate(posX, posY);

        g2d.setColor(new Color(231, 76, 60));
        g2d.fill(transform.createTransformedShape(crearNave()));
        
        g2d.setColor(Color.DARK_GRAY);
        g2d.drawString("Usa las FLECHAS para trasladar | Posición: (" + posX + ", " + posY + ")", 15, 25);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP -> posY -= PASO;
            case KeyEvent.VK_DOWN -> posY += PASO;
            case KeyEvent.VK_LEFT -> posX -= PASO;
            case KeyEvent.VK_RIGHT -> posX += PASO;
        }
        historial.add(new Point2D.Double(posX, posY));
        repaint();
    }

    @Override public void keyTyped(KeyEvent e) {}
    @Override public void keyReleased(KeyEvent e) {}

    public static void main(String[] args) {
        JFrame frame = new JFrame("2.1.1 Traslación - Nivel Complejo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new Ejercicio5_GRAF());
        frame.setSize(600, 450);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
package ejerc3;

import javax.swing.*;
import java.awt.*;

public class EJERC3 extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // Convertimos a Graphics2D para acceder a mejores herramientas de trazo
        Graphics2D g2d = (Graphics2D) g;
        
        // Configurar el grosor del trazo y el suavizado de bordes (Antialiasing)
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setStroke(new BasicStroke(3)); // Grosor de 3 píxeles

        // 1. Trazo de una Línea Simple
        g2d.setColor(Color.BLUE);
        g2d.drawLine(50, 50, 300, 50);
        g2d.drawString("Línea (drawLine)", 50, 40);

        // 2. Trazo de un Polígono mediante arreglos (Triángulo)
        g2d.setColor(Color.RED);
        int[] puntosX = {100, 150, 50};
        int[] puntosY = {100, 200, 200};
        int numPuntos = 3;
        g2d.drawPolygon(puntosX, puntosY, numPuntos);
        g2d.drawString("Polígono (drawPolygon)", 50, 225);

        // 3. Trazo de un Polígono usando el objeto Polygon (Rombo relleno)
        g2d.setColor(new Color(0, 150, 0)); // Verde oscuro
        Polygon rombo = new Polygon();
        rombo.addPoint(300, 100); // Punta superior
        rombo.addPoint(350, 150); // Derecha
        rombo.addPoint(300, 200); // Punta inferior
        rombo.addPoint(250, 150); // Izquierda
        
        g2d.fillPolygon(rombo);
        g2d.setColor(Color.BLACK);
        g2d.drawString("Polígono Relleno (fillPolygon)", 220, 225);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame ventana = new JFrame("1.5 Trazo de Líneas y Polígonos");
            ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            ventana.setSize(500, 350);
            ventana.add(new EJERC3()); // Agregamos el lienzo a la ventana
            ventana.setLocationRelativeTo(null);
            ventana.setVisible(true);
        });
    }
}
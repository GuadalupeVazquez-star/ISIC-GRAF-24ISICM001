package ejerc2;
import javax.swing.*;
import java.awt.*;

public class EJERC2 extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        g.drawString("Línea generada píxel por píxel con Bresenham", 20, 30);
        g.setColor(Color.RED);
        
        // Trazar una línea desde (50, 50) hasta (350, 250)
        trazarLinea(g, 50, 50, 350, 250);
        
        // Trazar otra línea con pendiente diferente
        g.setColor(Color.BLUE);
        trazarLinea(g, 50, 250, 350, 50);
    }

    /**
     * Implementación matemática del algoritmo de Bresenham para líneas.
     */
    private void trazarLinea(Graphics g, int x0, int y0, int x1, int y1) {
        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);
        
        // Determinar la dirección del trazo (paso positivo o negativo)
        int sx = (x0 < x1) ? 1 : -1;
        int sy = (y0 < y1) ? 1 : -1;
        
        // Calcular el error inicial
        int err = dx - dy;

        while (true) {
            // Dibujar el "píxel" actual (usamos un rectángulo de 2x2 para que sea visible)
            g.fillRect(x0, y0, 2, 2);

            // Si llegamos al punto final, terminamos el ciclo
            if (x0 == x1 && y0 == y1) {
                break;
            }

            int e2 = 2 * err;

            // Ajustar el error y avanzar en X
            if (e2 > -dy) {
                err -= dy;
                x0 += sx;
            }

            // Ajustar el error y avanzar en Y
            if (e2 < dx) {
                err += dx;
                y0 += sy;
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame ventana = new JFrame("Rasterización: Algoritmo de Bresenham");
            ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            ventana.setSize(450, 350);
            ventana.add(new EJERC2());
            ventana.setLocationRelativeTo(null);
            ventana.setVisible(true);
        });
    }
}
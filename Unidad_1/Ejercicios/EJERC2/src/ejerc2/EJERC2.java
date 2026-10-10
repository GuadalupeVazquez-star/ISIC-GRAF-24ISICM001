package ejerc2;
import javax.swing.*;
import java.awt.*;

public class EJERC2 extends JPanel {
    
    private JTextField txtX0 = new JTextField("50",4);
    private JTextField txtY0 = new JTextField("50",4);
    private JTextField txtX1 = new JTextField("350",4);
    private JTextField txtY1 = new JTextField("250",4);

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        g.setColor(new Color(230,230,230));
        for(int i=0; i<getWidth(); i+=10) g.drawLine(i,0,i,getHeight());
        for(int i=0; i<getHeight(); i+=10) g.drawLine(0,i,getWidth(),i);
        g.setColor(Color.ORANGE);
        g.drawString("Línea generada píxel por píxel con Bresenham", 20, 30);
        g.setColor(Color.RED);
        
        //se comentan las lineas, cambios
        // Trazar una línea desde (50, 50) hasta (350, 250)
        //trazarLinea(g, 50, 50, 350, 250);
        
        // Trazar otra línea con pendiente diferente
        //g.setColor(Color.BLUE);
        //trazarLinea(g, 50, 250, 350, 50);
        int x0 = Integer.parseInt(txtX0.getText()); //cambio1
        int y0 = Integer.parseInt(txtY0.getText());
        int x1 = Integer.parseInt(txtX1.getText());
        int y1 = Integer.parseInt(txtY1.getText());
        trazarLinea(g, x0, y0, x1, y1);
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
            //ventana.setSize(450, 350);  //se comentan estas lineas de cambio 2
            //ventana.add(new EJERC2());
            //ventana.setLocationRelativeTo(null);
            //ventana.setVisible(true);
            ventana.setSize(500, 450);

            EJERC2 panel = new EJERC2();

            JPanel panelControles = new JPanel();
            panelControles.add(new JLabel("X0:")); panelControles.add(panel.txtX0);
            panelControles.add(new JLabel("Y0:")); panelControles.add(panel.txtY0);
            panelControles.add(new JLabel("X1:")); panelControles.add(panel.txtX1);
            panelControles.add(new JLabel("Y1:")); panelControles.add(panel.txtY1);
            JButton btn = new JButton("Dibujar");
            btn.addActionListener(e -> panel.repaint());
            panelControles.add(btn);

            ventana.setLayout(new BorderLayout());
            ventana.add(panelControles, BorderLayout.NORTH);
            ventana.add(panel, BorderLayout.CENTER);
            ventana.setLocationRelativeTo(null);
            ventana.setVisible(true);
        });
    }
}
package ejerc7;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class EJERC7 extends JFrame {

    public EJERC7() {
        setTitle("Procesamiento de Mapa de Bits: Binarización");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        try {
            BufferedImage imagenOriginal = ImageIO.read(new File("Unidad_1_Introduccion/src/data/perro.jpg"));
            // Aplicar el método de umbral (Threshold)
            BufferedImage imagenBinarizada = aplicarUmbral(imagenOriginal, 127);

            // Mostrar el resultado usando JLabel
            JLabel labelImagen = new JLabel(new ImageIcon(imagenBinarizada));
            add(labelImagen, BorderLayout.CENTER);

            pack();
            setLocationRelativeTo(null);
        } catch (IOException e) {
            System.out.println("No se pudo cargar la imagen para binarizar.");
        }
    }

    private BufferedImage aplicarUmbral(BufferedImage original, int umbral) {
        BufferedImage binarizada = new BufferedImage(
                original.getWidth(), 
                original.getHeight(), 
                BufferedImage.TYPE_INT_RGB
        );

        for (int y = 0; y < original.getHeight(); y++) {
            for (int x = 0; x < original.getWidth(); x++) {
                int pixel = original.getRGB(x, y);

                int r = (pixel >> 16) & 0xff;
                int g = (pixel >> 8) & 0xff;
                int b = pixel & 0xff;

                // Promedio simple para medir la intensidad del píxel
                int intensidad = (r + g + b) / 3;

                // Modificar a blanco o negro puro dependiendo del umbral
                int nuevoColor = (intensidad > umbral) ? 0xFFFFFF : 0x000000;
                binarizada.setRGB(x, y, nuevoColor);
            }
        }
        return binarizada;
    }

    public static void main(String[] args) {
        // Ejecutar en el Event Dispatch Thread (EDT) para seguridad de hilos en Swing
        SwingUtilities.invokeLater(() -> {
            new EJERC7().setVisible(true);
        });
    }
}
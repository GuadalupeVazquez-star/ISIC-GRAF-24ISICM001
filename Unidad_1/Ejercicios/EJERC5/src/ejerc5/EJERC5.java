package ejerc5;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class EJERC5 {
    public static void main(String[] args) {
        try {
            // 1. Cargar la imagen original en formato PNG
            File archivoEntrada = new File("Unidad_1_Introduccion/src/data/perro.png");
            BufferedImage imagenPng = ImageIO.read(archivoEntrada);

            // 2. Crear un nuevo BufferedImage tipo RGB (sin canal alfa) para el JPEG
            BufferedImage imagenJpeg = new BufferedImage(
                    imagenPng.getWidth(),
                    imagenPng.getHeight(),
                    BufferedImage.TYPE_INT_RGB
            );

            // 3. Dibujar un fondo blanco sólido y luego pintar el PNG encima
            Graphics2D g2d = imagenJpeg.createGraphics();
            g2d.setColor(Color.WHITE);
            g2d.fillRect(0, 0, imagenJpeg.getWidth(), imagenJpeg.getHeight());
            g2d.drawImage(imagenPng, 0, 0, null);
            g2d.dispose(); // Liberar los recursos gráficos

            // 4. Escribir el archivo final en formato JPEG
            File archivoSalida = new File("resultado_blanco.jpg");
            ImageIO.write(imagenJpeg, "jpg", archivoSalida);

            System.out.println("Conversión a JPEG completada con éxito.");

        } catch (IOException e) {
            System.out.println("Ocurrió un error: " + e.getMessage());
        }
    }
}
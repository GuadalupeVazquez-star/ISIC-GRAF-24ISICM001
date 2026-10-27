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
            File archivoEntrada = new File("src/ejerc5/data/Tuxpan.jpeg");
            BufferedImage imagenPng = ImageIO.read(archivoEntrada);
            boolean tieneAlfa = imagenPng.getColorModel().hasAlpha();
            System.out.println("¿Tuxpan.jpeg tiene alfa? " + tieneAlfa);

            // 2. Crear un nuevo BufferedImage tipo RGB (sin canal alfa) para el JPEG
            BufferedImage imagenJpeg = new BufferedImage(
                    imagenPng.getWidth(),
                    imagenPng.getHeight(),
                    BufferedImage.TYPE_INT_RGB
            );

            // 3. Dibujar un fondo blanco sólido y luego pintar el PNG encima
            //Graphics2D g2d = imagenJpeg.createGraphics();
            //g2d.setColor(Color.WHITE);
            //g2d.fillRect(0, 0, imagenJpeg.getWidth(), imagenJpeg.getHeight());
            //g2d.drawImage(imagenPng, 0, 0, null);
            //g2d.dispose(); // Liberar los recursos gráficos
            
            //cambio1
            Graphics2D g2d = imagenJpeg.createGraphics();
            if (tieneAlfa) {
                // Solo si tiene transparencia, pinta blanco
                g2d.setColor(Color.WHITE);
                g2d.fillRect(0, 0, imagenJpeg.getWidth(), imagenJpeg.getHeight());
                System.out.println("Tenía transparencia, se reemplazó por blanco");
            } else {
                System.out.println("No tenía transparencia, conversión directa");
            }
            g2d.drawImage(imagenPng, 0, 0, null);
            g2d.dispose();
            
            // versión pequeña al 50%
            int nuevoAncho = imagenPng.getWidth() / 2;
            int nuevoAlto = imagenPng.getHeight() / 2;
            BufferedImage imagenPequena = new BufferedImage(nuevoAncho, nuevoAlto, BufferedImage.TYPE_INT_RGB);
            Graphics2D gPequena = imagenPequena.createGraphics();
            gPequena.drawImage(imagenPng, 0, 0, nuevoAncho, nuevoAlto, null);
            gPequena.dispose();

            // 4. Escribir el archivo final en formato JPEG
            //File archivoSalida = new File("resultado_blanco.jpg");
            //ImageIO.write(imagenJpeg, "jpg", archivoSalida);
            File archivoSalida = new File("src/ejerc5/data/resultado_blanco.jpg");
            ImageIO.write(imagenJpeg, "jpg", archivoSalida);

             File archivoPng = new File("src/ejerc5/data/resultado_pequeno.png");
             ImageIO.write(imagenPequena, "png", archivoPng);

            System.out.println("Guardados: " + archivoSalida.getName() + " y " + archivoPng.getName());
            System.out.println("Original: " + imagenPng.getWidth() + "x" + imagenPng.getHeight() + " -> Pequeña: " + nuevoAncho + "x" + nuevoAlto);
            
 
            System.out.println("Conversión a JPEG completada con éxito.");

        } catch (IOException e) {
            System.out.println("Ocurrió un error: " + e.getMessage());
        }
    }
}
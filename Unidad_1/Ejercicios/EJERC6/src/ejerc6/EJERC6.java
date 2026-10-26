package ejerc6;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class EJERC6 {
    public static void main(String[] args) {
        try {
            BufferedImage imagen = ImageIO.read(new File("Unidad_1_Introduccion/src/data/perro.jpg"));

            // Recorrer la matriz bidimensional de píxeles
            for (int y = 0; y < imagen.getHeight(); y++) {
                for (int x = 0; x < imagen.getWidth(); x++) {
                    int pixel = imagen.getRGB(x, y);

                    // Desplazamiento y máscara a nivel de bits para extraer ARGB
                    int alpha = (pixel >> 24) & 0xff;
                    int red = (pixel >> 16) & 0xff;
                    int green = (pixel >> 8) & 0xff;
                    int blue = pixel & 0xff;

                    // Calcular la luminancia
                    int gris = (int)(0.299 * red + 0.587 * green + 0.114 * blue);

                    // Ensamblar de nuevo el píxel con el tono gris
                    int nuevoPixel = (alpha << 24) | (gris << 16) | (gris << 8) | gris;

                    imagen.setRGB(x, y, nuevoPixel);
                }
            }

            ImageIO.write(imagen, "jpg", new File("Unidad_1_Introduccion/src/data/resultado_grises.jpg"));
            System.out.println("Filtro de escala de grises aplicado a nivel de mapa de bits.");

        } catch (IOException e) {
            System.out.println("Error al procesar la imagen: " + e.getMessage());
        }
    }
}
package ejerc6;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class EJERC6 {
    public static void main(String[] args) {
        try {
            File archivoEntrada = new File("src/ejerc6/data/barca.jpeg");
            
            System.out.println("Buscando: " + archivoEntrada.getAbsolutePath());
            System.out.println("¿Existe? " + archivoEntrada.exists());

            BufferedImage imagen = ImageIO.read(archivoEntrada);
            
            if (imagen == null) {
                System.out.println("El archivo no es un JPG valido, renombralo");
                return;
            }

            for (int y = 0; y < imagen.getHeight(); y++) {
                // MODIFICACION 2: Solo filtra la mitad superior para comparar
                if (y > imagen.getHeight() / 2) continue; 
                for (int x = 0; x < imagen.getWidth(); x++) {
                    int pixel = imagen.getRGB(x, y);
                    int alpha = (pixel >> 24) & 0xff;
                    int red = (pixel >> 16) & 0xff;
                    int green = (pixel >> 8) & 0xff;
                    int blue = pixel & 0xff;
                    //int gris = (int)(0.299 * red + 0.587 * green + 0.114 * blue);
                    
                    // MODIFICACION 1: Filtro NEGATIVO en escala de grises
                    int gris = (int)(0.299 * red + 0.587 * green + 0.114 * blue);
                    gris = 255 - gris; // invierte el gris
                    int nuevoPixel = (alpha << 24) | (gris << 16) | (gris << 8) | gris;
                    imagen.setRGB(x, y, nuevoPixel);
                }
            }

            File archivoSalida = new File("src/ejerc6/data/barca_gris.jpeg");
            archivoSalida.getParentFile().mkdirs();
            ImageIO.write(imagen, "jpg", archivoSalida);
            
            System.out.println("Filtro gris aplicado. Guardado como: " + archivoSalida.getName());

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
package ejerc4;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class EJERC4 {
    public static void main(String[] args) {
        // Nombres de archivos de prueba
        String[] archivos = {"src/ejerc4/data/Tierra_Luna.jpeg", 
                             "src/ejerc4/data/Espacio.jpeg", 
                             "src/ejerc4/data/Vista.jpeg"};

        for (String ruta : archivos) {
            try {
                File archivo = new File(ruta);
                if (!archivo.exists()) {
                    System.out.println("No se encontró el archivo: " + ruta);
                    continue;
                }

                BufferedImage imagen = ImageIO.read(archivo);
                if (imagen != null) {
                    System.out.println("Archivo: " + ruta);
                    System.out.println("Dimensiones: " + imagen.getWidth() + "x" + imagen.getHeight() + " px");
                    System.out.println("Tipo de imagen (Constante INT): " + imagen.getType());

                    // Verificar si el modelo de color soporta el canal alfa (transparencia)
                    boolean tieneAlfa = imagen.getColorModel().hasAlpha();
                    // INICIO CAMBIO 1
                    boolean transparenciaReal = false;
                    if (tieneAlfa) {
                        for (int y = 0; y < imagen.getHeight(); y++) {
                            for (int x = 0; x < imagen.getWidth(); x++) {
                                  int pixel = imagen.getRGB(x, y);
                                  int alpha = (pixel >> 24) & 0xff;
                                  if (alpha < 255) {
                                      transparenciaReal = true;
                                      break;
                                }
                            }
                                  if (transparenciaReal) break;
                    }
                        }
                          // >>> Final cambio 1

                    System.out.println("Soporta transparencia (Alfa): " + (tieneAlfa ? "Sí" : "No"));
                    //se agrega linea para visualizar
                    System.out.println("Tiene transparencia REAL: " + (transparenciaReal ? "Si - hay pixeles transparentes" : "No - aunque soporte alfa, es opaca"));
                    // ---INICIO MOD 2 
                    String extension = ruta.substring(ruta.lastIndexOf(".") + 1);
                    System.out.println("Formato detectado: " + extension.toUpperCase());

                    // Si es PNG con transparencia, lo convierte a JPG con fondo blanco
                    if (tieneAlfa) {
                        try {
                             File salida = new File("src/ejerc4/data/convertido_a_jpg.jpg");
                             BufferedImage sinAlfa = new BufferedImage(imagen.getWidth(), imagen.getHeight(), BufferedImage.TYPE_INT_RGB);
                             sinAlfa.createGraphics().drawImage(imagen, 0, 0, java.awt.Color.WHITE, null);
                             ImageIO.write(sinAlfa, "jpg", salida);
                             System.out.println("Convertido y guardado como: " + salida.getName() + " (fondo blanco)");
                         } catch (Exception ex) {
                             System.out.println("Error al convertir: " + ex.getMessage());
                        }
                    }
                    // ---FIN MOD 2 ---
                    System.out.println("-----------------------------------");
                }
            } catch (IOException e) {
                System.out.println("Error al procesar: " + ruta);
            }
        }
    }
}
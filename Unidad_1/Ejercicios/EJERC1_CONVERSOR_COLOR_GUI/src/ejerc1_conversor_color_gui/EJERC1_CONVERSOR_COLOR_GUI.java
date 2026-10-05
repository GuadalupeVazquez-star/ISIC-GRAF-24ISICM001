package ejerc1_conversor_color_gui;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class EJERC1_CONVERSOR_COLOR_GUI extends JFrame {
    private JSlider sliderR, sliderG, sliderB;
    private JPanel panelColor;
    private JLabel lblRGB;

    public EJERC1_CONVERSOR_COLOR_GUI() {
        setTitle("Ajuste Dinámico de Color RGB");
        setSize(450, 300); // Tamaño reducido por tener menos elementos
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Panel lateral derecho: Muestra el color actual
        panelColor = new JPanel();
        panelColor.setBackground(Color.BLACK);
        panelColor.setPreferredSize(new Dimension(150, 150));
        panelColor.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        add(panelColor, BorderLayout.EAST);

        // Panel central: Controles deslizantes (Sliders) para RGB
        JPanel panelControles = new JPanel();
        panelControles.setLayout(new GridLayout(3, 1, 5, 5));
        panelControles.setBorder(BorderFactory.createTitledBorder("Ajuste de Canales RGB"));

        sliderR = crearSlider("Rojo (R)");
        sliderG = crearSlider("Verde (G)");
        sliderB = crearSlider("Azul (B)");

        panelControles.add(sliderR);
        panelControles.add(sliderG);
        panelControles.add(sliderB);
        add(panelControles, BorderLayout.CENTER);

        // Panel inferior: Etiqueta de texto con el resultado
        JPanel panelResultados = new JPanel();
        lblRGB = new JLabel("RGB: ");
        lblRGB.setFont(new Font("Monospaced", Font.BOLD, 16));
        panelResultados.add(lblRGB);
        add(panelResultados, BorderLayout.SOUTH);

        // Forzar el cálculo inicial
        actualizarValores();
    }

    private JSlider crearSlider(String nombre) {
        JSlider slider = new JSlider(0, 255, 0);
        slider.setBorder(BorderFactory.createTitledBorder(nombre));
        slider.setMajorTickSpacing(51); 
        slider.setMinorTickSpacing(17);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                actualizarValores();
            }
        });
        return slider;
    }

    private void actualizarValores() {
        int r = sliderR.getValue();
        int g = sliderG.getValue();
        int b = sliderB.getValue();

        // Refrescar el componente visual
        panelColor.setBackground(new Color(r, g, b));

        // Mostrar texto RGB
        lblRGB.setText(String.format("RGB: (%3d, %3d, %3d)", r, g, b));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            EJERC1_CONVERSOR_COLOR_GUI ventana = new EJERC1_CONVERSOR_COLOR_GUI();
            ventana.setLocationRelativeTo(null); 
            ventana.setVisible(true);
        });
    }
}
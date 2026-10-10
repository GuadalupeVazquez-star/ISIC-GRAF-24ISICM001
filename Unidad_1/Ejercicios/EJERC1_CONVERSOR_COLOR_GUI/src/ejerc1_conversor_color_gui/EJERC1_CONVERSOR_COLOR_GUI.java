package ejerc1_conversor_color_gui;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class EJERC1_CONVERSOR_COLOR_GUI extends JFrame {
    private JSlider sliderR, sliderG, sliderB;
    private JPanel panelColor;
    private JLabel lblRGB;
    private JLabel lblHEX;// <-- se agg ESTA LÍNEA cmb1
    private JLabel Brillo;
    private JButton btnAleatorio;

    public EJERC1_CONVERSOR_COLOR_GUI() {
        setTitle("Ajuste Dinámico de Color RGB");
        setSize(600, 450); // Tamaño ajustado por tener mas elementos
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Panel lateral derecho: Muestra el color actual
        panelColor = new JPanel();
        panelColor.setBackground(Color.BLACK);
        panelColor.setPreferredSize(new Dimension(150, 150));
        panelColor.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        add(panelColor, BorderLayout.EAST);
        
        JButton btnAleatorio = new JButton("Color Aleatorio");
        btnAleatorio.addActionListener(e -> {
        sliderR.setValue((int)(Math.random()*256));
        sliderG.setValue((int)(Math.random()*256));
        sliderB.setValue((int)(Math.random()*256));
        });
        JPanel panelArriba = new JPanel();
        panelArriba.add(btnAleatorio);
        add(panelArriba, BorderLayout.NORTH);

        // Panel central: Controles deslizantes (Sliders) para RGB
        JPanel panelControles = new JPanel();
        panelControles.setLayout(new GridLayout(4, 1, 5, 5)); // antes era 3,1 "panelControles.setLayout(new GridLayout(3, 1, 5, 5));"
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
        panelResultados.setLayout(new GridLayout(2,1)); // 2 filas
        lblRGB = new JLabel("RGB: ");
        lblRGB.setFont(new Font("Monospaced", Font.BOLD, 16));
        lblHEX = new JLabel("HEX: #000000");
        lblHEX.setFont(new Font("Monospaced", Font.BOLD, 16));
        panelResultados.add(lblRGB);
        panelResultados.add(lblHEX);
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
        lblHEX.setText(String.format("HEX: #%02X%02X%02X", r, g, b));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            EJERC1_CONVERSOR_COLOR_GUI ventana = new EJERC1_CONVERSOR_COLOR_GUI();
            ventana.setLocationRelativeTo(null); 
            ventana.setVisible(true);
        });
    }
}
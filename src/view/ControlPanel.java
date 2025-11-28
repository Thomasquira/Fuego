/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author thomas
 */
public class ControlPanel extends JPanel {

    private JLabel imagenLabel;
    private JButton botonMusica;

    public ControlPanel() {
        setPreferredSize(new Dimension(200, 400));
        setBackground(Color.DARK_GRAY);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        imagenLabel = new JLabel();
        imagenLabel.setAlignmentX(CENTER_ALIGNMENT);
        imagenLabel.setHorizontalAlignment(JLabel.CENTER);

        imagenLabel.setBorder(null);

        try {
            BufferedImage original = ImageIO.read(new File("src/logo.png"));

            int ancho = 180;  
            int alto = (int) (original.getHeight() * (ancho / (double) original.getWidth()));

            Image img = original.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
            imagenLabel.setIcon(new ImageIcon(img));

            imagenLabel.setMaximumSize(new Dimension(ancho, alto));
            imagenLabel.setPreferredSize(new Dimension(ancho, alto));

        } catch (Exception e) {
            imagenLabel.setText("Imagen no encontrada");
        }

        add(Box.createVerticalStrut(10));
        add(imagenLabel);

        botonMusica = new JButton("Apagar Música");
        botonMusica.setAlignmentX(CENTER_ALIGNMENT);
        botonMusica.setMaximumSize(new Dimension(180, 40));

        add(Box.createVerticalStrut(10));
        add(botonMusica);

        add(Box.createVerticalGlue());
    }

    public JButton getBotonMusica() {
        return botonMusica;
    }
}


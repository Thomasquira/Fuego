/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import controller.Controller;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author thomas
 */
public class Viewer extends JPanel implements Runnable {

    private Controller controller;
    private Thread thread;
    private BufferedImage image, fondo;

    public Viewer(Controller controller) {
        this.controller = controller;
        this.image = new BufferedImage(600, 400, BufferedImage.TYPE_INT_ARGB);
        setBackground(Color.BLACK);
        thread = new Thread(this);
        setPreferredSize(new Dimension(600, 400));


        try {
            fondo = ImageIO.read(new File("src\\esqueleto.png"));
        } catch (IOException e) {
            System.out.println("No se pudo cargar la imagen de fondo.");
        }
    }

    @Override
    public void run() {
        while (true) {
            controller.actualizarFuego();
            dibujarFuego();
            repaint();
            try { Thread.sleep(66); } catch (InterruptedException e) {}
        }
    }

    private void dibujarFuego() {
        Graphics2D g2 = image.createGraphics();
        g2.setComposite(AlphaComposite.Clear);
        g2.fillRect(0, 0, image.getWidth(), image.getHeight());
        g2.dispose();

        int[][] temperatura = controller.getTemperaturas();

        for (int y = 0; y < temperatura.length; y++) {
            for (int x = 0; x < temperatura[0].length; x++) {
                int temp = temperatura[y][x];
                if (temp > 0) {
                    Color c = controller.getColor(temp);
                    image.setRGB(x, y, c.getRGB());
                }
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        if (fondo != null) {
            g2.drawImage(fondo, 0, 0, getWidth(), getHeight(), null);
        }

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));
        g2.drawImage(image, 0, 0, getWidth(), getHeight(), null);
    }

    public Thread getThread() {
        return thread;
    }
    
    

}




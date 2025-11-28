/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import controller.Controller;
import java.awt.BorderLayout;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.JFrame;

/**
 *
 * @author thomas
 */
public class View extends JFrame {

    private Clip clip;

    public View(Controller controller) {

        Viewer viewer = new Viewer(controller);
        ControlPanel controlPanel = new ControlPanel();

        setTitle("Fuego");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        add(controlPanel, BorderLayout.WEST);
        add(viewer, BorderLayout.CENTER);

        setSize(1000, 700);
        setLocationRelativeTo(null);
        setVisible(true);
        controlPanel.getBotonMusica().addActionListener(e -> {
            if (clip != null) {
                if (clip.isRunning()) {
                    clip.stop();
                    controlPanel.getBotonMusica().setText("Encender Música");
                } else {
                    clip.start();
                    controlPanel.getBotonMusica().setText("Apagar Música");
                }
            }
        });

        reproducirSonido();
        viewer.getThread().start();
    }

    private void reproducirSonido() {

        try {
            File archivo = new File("src/StarsOfTheMidnight.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(archivo);
            clip = AudioSystem.getClip();

            clip.open(audioStream);
            FloatControl volumen = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            volumen.setValue(-10.0f);

            clip.start();
        } catch (Exception e) {
            System.out.println("No se pudo reproducir el sonido: " + e.getMessage());
        }
    }
}

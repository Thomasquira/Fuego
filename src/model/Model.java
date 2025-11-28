/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import controller.Controller;
import java.util.Random;

/**
 *
 * @author thomas
 */
public class Model {

    private final int width;
    private final int height;
    private final int[][] temperatura;
    private final PaletaColor paleta;
    private final Random random = new Random();

    public Model(int width, int height) {
        this.width = width;
        this.height = height;
        this.temperatura = new int[height][width];
        this.paleta = new PaletaColor();
    }

    public void hacerFuego() {
        encenderPixelesBase();
        apagarPixelesBase();

        for (int y = height - 2; y >= 0; y--) {
            for (int x = 0; x < width; x++) {

                if (x == 0) {
                    temperatura[y][x] = temperatura[y][x + 1];
                    continue;
                }
                if (x == width - 1) {
                    temperatura[y][x] = temperatura[y][x - 1];
                    continue;
                }

                int abajo = temperatura[y + 1][x];
                int abajoIzq = temperatura[y + 1][x - 1];
                int abajoDer = temperatura[y + 1][x + 1];
                int izq = temperatura[y][x - 1];
                int der = temperatura[y][x + 1];

                int nuevo = (int) (abajo * 0.40 + abajoIzq * 0.21 + abajoDer * 0.21 + izq * 0.10 + der * 0.10);

                if (random.nextFloat() < 0.05f) {
                    int dx = random.nextBoolean() ? 1 : -1;
                    int nx = x + dx;

                    if (nx >= 0 && nx < width) {
                        nuevo = temperatura[y + 1][nx];
                    }
                }

                nuevo -= random.nextInt(50);
                if (nuevo < 0) nuevo = 0;

                temperatura[y][x] = nuevo;
            }
        }
    }

    private void encenderPixelesBase() {
        for (int x = 0; x < width; x++) {
            if (random.nextFloat() < 0.40f) {
                temperatura[height - 1][x] = 1023;
            }
        }
    }

    private void apagarPixelesBase() {
        for (int x = 0; x < width; x++) {
            if (random.nextFloat() < 0.10f) {
                temperatura[height - 1][x] = 0;
            }
        }
    }

    public int[][] getTemperatura() {
        return temperatura;
    }

    public PaletaColor getPaleta() {
        return paleta;
    }
}


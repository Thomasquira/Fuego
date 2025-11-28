/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.Color;
import model.Model;
import view.View;

/**
 *
 * @author thomas
 */
public class Controller {

    private final Model model;

    public Controller(Model model) {
        this.model = model;
    }

    public void actualizarFuego() {
        model.hacerFuego();
    }

    public int[][] getTemperaturas() {
        return model.getTemperatura();
    }

    public Color getColor(int temp) {
        return model.getPaleta().getColorParaTempreatura(temp);
    }
}


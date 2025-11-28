/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package fuego;

import controller.Controller;
import model.Model;
import view.View;

/**
 *
 * @author thomas
 */
public class Fuego {
    public static void main(String[] args) {
        Model model = new Model(600, 400);
        Controller controller = new Controller(model);
        View view = new View(controller);
    }
}


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.awt.Color;
import java.awt.List;
import java.util.ArrayList;

/**
 *
 * @author thomas
 */
public class PaletaColor {

    private final ArrayList<ColorTargetDTO> colorTargets = new ArrayList<>();

    public PaletaColor() {
        colorTargets.add(new ColorTargetDTO(0, new Color(20, 0, 0)));
        colorTargets.add(new ColorTargetDTO(170, new Color(150, 30, 20)));
        colorTargets.add(new ColorTargetDTO(300, new Color(200, 60, 0)));
        colorTargets.add(new ColorTargetDTO(500, new Color(200, 130, 50)));
        colorTargets.add(new ColorTargetDTO(800, new Color(220, 200, 180)));
        colorTargets.add(new ColorTargetDTO(1000, new Color(255, 200, 180)));
        colorTargets.add(new ColorTargetDTO(1023, new Color(255, 200, 200)));

    }

    public Color getColorParaTempreatura(int t) {
        if (t <= 0) {
            return colorTargets.get(0).getColor();
        }
        if (t >= 1023) {
            return colorTargets.get(colorTargets.size() - 1).getColor();
        }

        for (int i = 0; i < colorTargets.size() - 1; i++) {
            ColorTargetDTO a = colorTargets.get(i);
            ColorTargetDTO b = colorTargets.get(i + 1);

            if (t >= a.getTemperatura() && t <= b.getTemperatura()) {
                float ratio = (t - a.getTemperatura()) / (float) (b.getTemperatura() - a.getTemperatura());
                int red = (int) (a.getColor().getRed() + ratio * (b.getColor().getRed() - a.getColor().getRed()));
                int greeen = (int) (a.getColor().getGreen() + ratio * (b.getColor().getGreen() - a.getColor().getGreen()));
                int blue = (int) (a.getColor().getBlue() + ratio * (b.getColor().getBlue() - a.getColor().getBlue()));

                return new Color(red, greeen, blue);
            }
        }
        return Color.BLACK;
    }

}

package com.geometric.wars.player;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;

import java.util.HashMap;
import java.util.Iterator;

public class ColorAndNameGiver {
    private static HashMap<String, Color> colors = new HashMap<>();
    static {
        colors.put("Goldenrod",Color.GOLDENROD);
        colors.put("Maroon",Color.MAROON);
        colors.put("Slate",Color.SLATE);
        colors.put("Firebrick",Color.FIREBRICK);
        colors.put("Navy",Color.NAVY);
        colors.put("Tan",Color.TAN);
        colors.put("Royal",Color.ROYAL);
        colors.put("Chartreuse",Color.CHARTREUSE);
        colors.put("Scarlet",Color.SCARLET);
    }

    public static String getRandomColorName() {
        int random = MathUtils.random(colors.size()-1);
        for(Iterator<String> key = colors.keySet().iterator(); key.hasNext();) {
            String name = key.next();
            if(random == 0) {
                return name;
            }
            random--;

        }
        return "Cube";
    }
    public static Color getColorByName(String str) {
        if(colors.containsKey(str))
            return colors.get(str);
        throw new RuntimeException("color" + str + " doesn't exist in color map");
    }
}

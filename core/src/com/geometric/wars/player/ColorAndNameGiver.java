package com.geometric.wars.player;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/**
 * only static methods
 */
public class ColorAndNameGiver {
    private static HashMap<String, Color> colors = new HashMap<>();
    private static HashSet<String> usedColors = new HashSet<>();

    public static void clearUsed() {
        usedColors.clear();
    }

    static {
        colors.put("Goldenrod",Color.GOLDENROD);
        colors.put("Maroon",Color.MAROON);
        colors.put("Slate",Color.SLATE);
        colors.put("Firebrick",Color.FIREBRICK);
        colors.put("Navy",Color.NAVY);
        colors.put("Tan",Color.TAN);
        colors.put("Royal",Color.ROYAL);
        colors.put("Chartreuse",Color.CHARTREUSE);
        colors.put("Magenta",Color.MAGENTA);
        colors.put("White",Color.WHITE);
        colors.put("Black",Color.BLACK);
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

    public static String getRandomUnusedColorName() {
        if(colors.size() == usedColors.size())
            return "Cube";
        String name;
        int ct = 0;
        do {
            name = getRandomColorName();
            ct++;
        }
        while (ct < 200 && usedColors.contains(name) );
        if(ct == 200)
            throw new RuntimeException("not found unused color");
        usedColors.add(name);
        return name;
    }

    public static Color getColorByName(String str) {
        if(colors.containsKey(str))
            return colors.get(str);
        throw new RuntimeException("color" + str + " doesn't exist in color map");
    }

    private ColorAndNameGiver(){}
}

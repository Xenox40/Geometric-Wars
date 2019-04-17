package com.geometric.wars.enviromentparts;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.geometric.wars.Values;

public class WallModel {
    private static Model instance = null;

    private static float width = Values.unit;
    private static float height = Values.unit;
    private static float depth = Values.unit;

    private static Color color = Color.GREEN;

    public static Model getModel() {
        if (instance == null)
            instance = new ModelBuilder().createBox(width, height, depth,
                    new Material(ColorAttribute.createDiffuse(color)),
                    VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
        return instance;
    }
}
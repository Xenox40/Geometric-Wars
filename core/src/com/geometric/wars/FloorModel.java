package com.geometric.wars;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;

public class FloorModel {

    private static Model instance = null;

    private static float width = Values.unit;
    private static float height = .1f * Values.unit;
    private static float depth = Values.unit;

    public static Model getModel() {
        if (instance == null)
            instance = new ModelBuilder().createBox(width, height, depth,
                    new Material(ColorAttribute.createDiffuse(Color.GREEN)),
                    VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
        return instance;
    }
}

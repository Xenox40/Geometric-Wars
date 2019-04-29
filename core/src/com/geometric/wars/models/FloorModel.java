package com.geometric.wars.models;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.geometric.wars.Values;

/**
 * Class contains instance of Floor model.
 * Do not forget to dispose an instance of the model.
 */
public class FloorModel {
    private FloorModel() { }
    private static Model instance = null;

    private static float width = Values.unit;
    private static float height = .1f * Values.unit;
    private static float depth = Values.unit;

    private static Color color = Color.LIME;

    /**
     * @return simple model of floor
     */
    public static Model getModel() {
        if (instance == null)
            instance = new ModelBuilder().createBox(width, height, depth,
                    new Material(ColorAttribute.createDiffuse(color)),
                    VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
        return instance;
    }

    public static void dispose() {
        if (instance != null)
            instance.dispose();
        instance = null;
    }
}

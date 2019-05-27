package com.geometric.wars.models;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.geometric.wars.utils.Values;

/**
 * Class contains instance of PowerUp model.
 * Do not forget to dispose an instance of the model.
 */
public class PowerUpModel {
    private PowerUpModel() { }
    private static Model instance = null;

    private final static float radius = Values.unit;

    public static Model getModel() {
        if (instance == null)
            instance = buildModel();
        return instance;
    }

    public static Model buildModel() {
        return new ModelBuilder().createSphere(radius,radius,radius,7,4,
                new Material(ColorAttribute.createDiffuse(Color.WHITE)),
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
    }

    public static void dispose() {
        if (instance != null)
            instance.dispose();
        instance = null;
    }
}

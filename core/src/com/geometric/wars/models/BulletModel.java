package com.geometric.wars.models;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;

/**
 * Class contains instance of Line model.
 * Do not forget to dispose an instance of the model.
 */
public class BulletModel {
    private BulletModel() { }
    private static Model instance = null;

    private final static Color color = Color.BLACK;
    private final static float radius = 0.35f;
    /**
     * @return simple model of line from (0,0,0) to (unit,0,0)
     */
    public static Model getModel() {
        if (instance == null)
            instance = buildModel();
        return instance;
    }

    public static Model buildModel() {
        return new ModelBuilder().createSphere(radius,radius,radius,4,2,
                new Material(ColorAttribute.createDiffuse(color)),
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
    }

    public static void dispose() {
        if (instance != null)
            instance.dispose();
        instance = null;
    }
}

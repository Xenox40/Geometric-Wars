package com.geometric.wars.models;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.geometric.wars.Values;

/**
 * Class contains instance of Line model.
 * Do not forget to dispose an instance of the model.
 */
public class LineModel {
    private LineModel() { }
    private static Model instance = null;

    private static Color color = Color.WHITE;

    /**
     * @return simple model of line from (0,0,0) to (unit,0,0)
     */
    public static Model getModel() {
        if (instance == null)
            instance = buildModelFromMesh();
        return instance;
    }

    private static Model buildModelFromMesh() {
        ModelBuilder meshModelBuilder = new ModelBuilder();
        meshModelBuilder.begin();
        MeshPartBuilder meshBuilder = meshModelBuilder.part("line", 1, 3, new Material());
        meshBuilder.setColor(color);
        meshBuilder.line(0, 0, 0, Values.unit,0,0);
        return meshModelBuilder.end();
    }
}

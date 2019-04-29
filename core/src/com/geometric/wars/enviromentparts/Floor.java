package com.geometric.wars.enviromentparts;

import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;
import com.geometric.wars.Values;
import com.geometric.wars.maps.StaticMapObject;
import com.geometric.wars.models.FloorModel;

/**
 * Represents a floor as an not-interactive model instance.
 */
public class Floor extends ModelInstance implements StaticMapObject {

    /**
     * Create new instance of Floor model, and
     * place it on given coordinates.
     *
     * @param x - x coordinate on a plane grid
     * @param y - y coordinate on a plane grid
     */
    public Floor(int x, int y) {
        //TODO constants for y cord
        super(FloorModel.getModel(), new Vector3(x * Values.unit, -(float)(Values.unit) / 2 - 0.05f, y * Values.unit));
    }

    @Override
    public void render(ModelBatch modelBatch, Environment environment) {
        modelBatch.render(this, environment);
    }
}

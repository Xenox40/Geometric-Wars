package com.geometric.wars.enviromentparts;

import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;
import com.geometric.wars.Values;

/**
 * Represents a floor as an not-interactive model instance.
 */
public class Floor extends ModelInstance {

    /**
     * Create new instance of Floor model, and
     * place it on given coordinates.
     *
     * @param x - x coordinate on a plane grid
     * @param y - y coordinate on a plane grid
     */
    public Floor(int x, int y) {
        super(FloorModel.getModel(), new Vector3(x * Values.unit, 0, y * Values.unit));
    }
}

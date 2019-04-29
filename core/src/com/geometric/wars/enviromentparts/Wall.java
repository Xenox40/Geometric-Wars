package com.geometric.wars.enviromentparts;

import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelCache;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;
import com.geometric.wars.Values;
import com.geometric.wars.maps.StaticMapObject;
import com.geometric.wars.models.WallModel;

/**
 * Represents a Wall as an not-interactive model instance.
 */
public class Wall extends ModelInstance implements StaticMapObject {

    /**
     * Create new instance of Wall model, and
     * place it on given coordinates.
     *
     * @param x - x coordinate on a plane grid
     * @param y - y coordinate on a plane grid
     */
    public Wall(int x, int y) {
        super(WallModel.getModel(), new Vector3(x * Values.unit,0, y * Values.unit));
    }

    @Override
    public void render(ModelBatch modelBatch, Environment environment) {
        modelBatch.render(this, environment);
    }

    @Override
    public void cache(ModelCache cache) {
        cache.add(this);
    }
}

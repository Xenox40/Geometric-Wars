package com.geometric.wars.gameobjects.enviromentparts;

import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelCache;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;
import com.geometric.wars.collisions.DynamicBody;
import com.geometric.wars.gameobjects.StaticGameObject;
import com.geometric.wars.models.WallModel;
import com.geometric.wars.utils.Values;

/**
 * Represents a Wall as an not-interactive model instance.
 */
public class Wall extends ModelInstance implements StaticGameObject {

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

    @Override
    public boolean canCollideWith(DynamicBody object) {
        return false;
    }

    @Override
    public void onCollisionWith(DynamicBody object) {}

    @Override
    public boolean exists() {
        return true;
    }
}

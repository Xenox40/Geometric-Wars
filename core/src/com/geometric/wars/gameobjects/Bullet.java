package com.geometric.wars.gameobjects;

import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;
import com.geometric.wars.collisions.DynamicBody;
import com.geometric.wars.models.BulletModel;
import com.geometric.wars.utils.Values;

public class Bullet extends ModelInstance implements DynamicBody {

    /**
     * Create new instance of Bullet model, and
     * place it on given coordinates.
     *
     * @param x - x coordinate on a plane grid
     * @param y - y coordinate on a plane grid
     */
    public Bullet(int x, int y) {
        super(BulletModel.getModel(), new Vector3(x * Values.unit, 0, y * Values.unit));
    }

    public void update() {

    }

    @Override
    public boolean canCollideWith(DynamicBody object) {
        return false;
    }

    @Override
    public void onCollisionWith(DynamicBody object) {}
}
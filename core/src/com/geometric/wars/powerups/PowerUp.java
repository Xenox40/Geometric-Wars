package com.geometric.wars.powerups;

import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.math.Vector3;
import com.geometric.wars.collisions.DynamicBody;
import com.geometric.wars.gameobjects.StaticGameObject;
import com.geometric.wars.models.PowerUpModel;
import com.geometric.wars.utils.Values;

public class PowerUp  extends ModelInstance implements StaticGameObject {

    public PowerUp(int x, int y) {
        super(PowerUpModel.getModel(), new Vector3(x * Values.unit,0, y * Values.unit));
    }
    private boolean isAlive = true;

    public void remove() {
        isAlive = false;
    }

    @Override
    public void render(ModelBatch modelBatch, Environment environment) {
        if(exists())
            modelBatch.render(this, environment);
    }

    @Override
    public void cache(ModelCache cache) {
        cache.add(this);
    }

    @Override
    public boolean canCollideWith(DynamicBody object) {
        return true;
    }

    @Override
    public void onCollisionWith(DynamicBody object) {
        System.out.println("removing me");
        remove();
    }

    @Override
    public boolean exists() {
        return isAlive;
    }
}
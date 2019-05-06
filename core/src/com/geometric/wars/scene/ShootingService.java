package com.geometric.wars.scene;

import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.utils.Array;
import com.geometric.wars.gameobjects.Bullet;
import com.geometric.wars.gameobjects.DynamicGameObject;

public class ShootingService implements DynamicGameObject {
    private Array<Bullet> bullets;

    @Override
    public void render(ModelBatch modelBatch, Environment environment) {
        for(Bullet bullet : bullets)
            modelBatch.render(bullet,environment);
    }

    @Override
    public void update() {
        for(Bullet bullet : bullets)
            bullet.update();
    }
}

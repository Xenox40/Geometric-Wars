package com.geometric.wars.gameobjects;

import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.geometric.wars.collisions.Collidable;

public interface DynamicGameObject {
    void render(ModelBatch modelBatch, Environment environment);
    void update();
}

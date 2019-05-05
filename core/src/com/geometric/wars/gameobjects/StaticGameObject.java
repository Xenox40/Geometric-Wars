package com.geometric.wars.gameobjects;

import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelCache;
import com.geometric.wars.collisions.Collidable;

public interface StaticGameObject extends Collidable {
    void render(ModelBatch modelBatch, Environment environment);
    void cache(ModelCache cache);
}

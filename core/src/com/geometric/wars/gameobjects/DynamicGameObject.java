package com.geometric.wars.gameobjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;

public interface DynamicGameObject {
    void render(ModelBatch modelBatch, Environment environment);
    void renderGUI(SpriteBatch batch);
    void update();
}

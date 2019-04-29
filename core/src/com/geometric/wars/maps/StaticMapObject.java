package com.geometric.wars.maps;

import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;

public interface StaticMapObject {
    void render(ModelBatch modelBatch, Environment environment);
}

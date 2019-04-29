package com.geometric.wars.maps;

import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelCache;

public interface StaticMapObject {
    void render(ModelBatch modelBatch, Environment environment);
    void cache(ModelCache cache);
}

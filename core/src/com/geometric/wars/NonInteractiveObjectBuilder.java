package com.geometric.wars;

import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.utils.Array;


public interface NonInteractiveObjectBuilder {
    ModelInstance getModelInstance(int x, int y);
}

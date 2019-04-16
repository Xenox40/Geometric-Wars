package com.geometric.wars;

import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;

public class FloorBuilder implements NonInteractiveObjectBuilder {
    @Override
    public ModelInstance getModelInstance(int x, int y) {
        return new ModelInstance(FloorModel.getModel(), new Vector3(x * Values.unit, 0, y * Values.unit));
    }
}

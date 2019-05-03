package com.geometric.wars.cube;


import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.geometric.wars.Direction2D;
import com.geometric.wars.Values;
import com.geometric.wars.math.RotationCalculator;

/**
 * should be built only with DynamicCubeBuilder
 */
public class DynamicCubeView extends ModelInstance {

    private static final float size = Values.unit;

    DynamicCubeView(Model cubeModel) {
        super(cubeModel);
    }

    void rotate(Direction2D direction2D, float degrees, int px, int pz) {
       this.transform = RotationCalculator.transformAfterRoll(this.transform, direction2D, degrees, size, px,pz);
    }

}

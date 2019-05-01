package com.geometric.wars.cube;


import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.geometric.wars.Direction2D;
import com.geometric.wars.Values;
import com.geometric.wars.models.CubeModel;


public class DynamicCubeView extends ModelInstance {

    private static final float size = Values.unit;

    public DynamicCubeView() {
        super(CubeModel.getModel());
    }


    public void rotate(Direction2D direction2D, float degrees, int px, int pz) {
       this.transform = RotationCalculator.transformAfterRoll(this.transform, direction2D, degrees, size, px,pz);
    }

}

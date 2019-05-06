package com.geometric.wars.cube;

public abstract class DynamicCubeController {
    protected DynamicCube dynamicCube;

    public void setControlledCube(DynamicCube dynamicCube) {
        this.dynamicCube = dynamicCube;
    }
    /**
     *  should call dynamicCube.move(Direction2D) according to detected input or AI strategy
     */
    public abstract void processMoving();
}


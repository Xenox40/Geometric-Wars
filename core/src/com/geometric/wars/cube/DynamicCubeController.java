package com.geometric.wars.cube;

public abstract class DynamicCubeController {
    protected final DynamicCube dynamicCube;
    public DynamicCubeController(DynamicCube dynamicCube){
        this.dynamicCube = dynamicCube;
    }

    /**
     *  should call dynamicCube.move(Direction) according to detected input or AI strategy
     */
    public abstract void processMoving();
}


package com.geometric.wars.cube;

public abstract class CubeController {
    protected final Cube cube;
    public CubeController(Cube cube){
        this.cube = cube;
    }

    /**
     *  should call cube.move(Direction) according to detected input or AI strategy
     */
    public abstract void processMoving();
}


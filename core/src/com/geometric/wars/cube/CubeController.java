package com.geometric.wars.cube;

import com.geometric.wars.Direction;
import com.geometric.wars.input.ArrowInputController;
import com.geometric.wars.input.InputController;

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


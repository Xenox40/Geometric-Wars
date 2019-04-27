package com.geometric.wars.cube;

import com.geometric.wars.Direction;
import com.geometric.wars.input.ArrowInputController;
import com.geometric.wars.input.InputController;

public class CubeController {
    private final InputController inputController;
    private final Cube cube;
    public CubeController(Cube cube, InputController inputController) {
        this.cube = cube;
        this.inputController = inputController;
    }

    public CubeController(final Cube cube) {
        this(cube, new ArrowInputController());
    }

    public void processKeyInput() {
        if(inputController.moveUp()) {
            cube.move(Direction.UP);
        }
        else if(inputController.moveRight()) {
            cube.move(Direction.RIGHT);
        }
        else if(inputController.moveDown()) {
            cube.move(Direction.DOWN);
        }
        else if(inputController.moveLeft()) {
            cube.move(Direction.LEFT);
        }
    }
}

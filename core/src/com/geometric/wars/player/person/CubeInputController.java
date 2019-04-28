package com.geometric.wars.player.person;

import com.geometric.wars.Direction;
import com.geometric.wars.cube.Cube;
import com.geometric.wars.cube.CubeController;
import com.geometric.wars.input.InputController;

public class CubeInputController extends CubeController {

    private InputController inputController;

    public CubeInputController(Cube cube, InputController inputController) {
        super(cube);
        this.inputController = inputController;
    }

    @Override
    public void processMoving() {
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

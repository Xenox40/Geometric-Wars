package com.geometric.wars.player.person;

import com.geometric.wars.Direction;
import com.geometric.wars.cube.DynamicCube;
import com.geometric.wars.cube.DynamicCubeController;
import com.geometric.wars.input.InputController;

public class DynamicCubeInputController extends DynamicCubeController {

    private InputController inputController;

    public DynamicCubeInputController(DynamicCube dynamicCube, InputController inputController) {
        super(dynamicCube);
        this.inputController = inputController;
    }

    @Override
    public void processMoving() {
        if(inputController.moveUp()) {
            dynamicCube.move(Direction.UP);
        }
        else if(inputController.moveRight()) {
            dynamicCube.move(Direction.RIGHT);
        }
        else if(inputController.moveDown()) {
            dynamicCube.move(Direction.DOWN);
        }
        else if(inputController.moveLeft()) {
            dynamicCube.move(Direction.LEFT);
        }
    }

}

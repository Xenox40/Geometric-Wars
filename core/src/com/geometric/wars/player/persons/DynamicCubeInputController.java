package com.geometric.wars.player.persons;

import com.geometric.wars.cube.DynamicCubeController;
import com.geometric.wars.input.InputController;
import com.geometric.wars.player.ShootingPlayersCube;
import com.geometric.wars.utils.Direction2D;

public class DynamicCubeInputController extends DynamicCubeController {

    private InputController inputController;

    public DynamicCubeInputController(InputController inputController) {
        super();
        this.inputController = inputController;
    }

    @Override
    public void processMoving() {
        if(inputController.moveUp()) {
            dynamicCube.move(Direction2D.UP);
        }
        else if(inputController.moveRight()) {
            dynamicCube.move(Direction2D.RIGHT);
        }
        else if(inputController.moveDown()) {
            dynamicCube.move(Direction2D.DOWN);
        }
        else if(inputController.moveLeft()) {
            dynamicCube.move(Direction2D.LEFT);
        }
        else if(inputController.shoot()) {
            if(dynamicCube instanceof ShootingPlayersCube) {
                ((ShootingPlayersCube) dynamicCube).shoot();
            }
        }
    }

}

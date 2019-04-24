package com.geometric.wars;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class CubeController {
    private KeyInputListener arrowAction;
    private InputController inputController;

    public CubeController(final Cube cube, InputController inputController) {
        this.inputController = inputController;
        arrowAction = new KeyInputListener() {
            @Override
            public void doAction(int key) {
                if (key == Input.Keys.UP) {
                    cube.moveUp();
                } else if (key == Input.Keys.RIGHT) {
                    cube.moveRight();
                } else if (key == Input.Keys.DOWN) {
                    cube.moveDown();
                } else if (key == Input.Keys.LEFT) {
                    cube.moveLeft();
                }
            }
        };
    }

    public CubeController(final Cube cube) {
        this(cube, new ArrowInputController());
    }

    public void processKeyInput() {
        if(inputController.moveUp()) {
            arrowAction.doAction(Input.Keys.UP);
        }
        else if(inputController.moveRight()) {
            arrowAction.doAction(Input.Keys.RIGHT);
        }
        else if(inputController.moveDown()) {
            arrowAction.doAction(Input.Keys.DOWN);
        }
        else if(inputController.moveLeft()) {
            arrowAction.doAction(Input.Keys.LEFT);
        }
    }
}

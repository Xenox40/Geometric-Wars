package com.geometric.wars;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class CubeController {
    private KeyInputListener arrowAction;

    public CubeController(final Cube cube) {
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

    public void setArrowAction(KeyInputListener arrowAction) {
        this.arrowAction = arrowAction;
    }

    public void processKeyInput() {
        if(Gdx.input.isKeyPressed(Input.Keys.UP))
            arrowAction.doAction(Input.Keys.UP);
        else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            arrowAction.doAction(Input.Keys.RIGHT);
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            arrowAction.doAction(Input.Keys.DOWN);
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            arrowAction.doAction(Input.Keys.LEFT);
        }
    }
}

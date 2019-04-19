package com.geometric.wars;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class CubeController {
    public CubeController(final CubeView cubeView, final Cube cube) {
        cubeView.setArrowAction(new KeyInputListener() {
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
        });
    }

}

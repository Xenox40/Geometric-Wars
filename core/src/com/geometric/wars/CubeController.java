package com.geometric.wars;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class CubeController {
    private CubeView cubeView;
    private Cube cube;
    public CubeController(CubeView cubeView, Cube cube) {
        this.cubeView = cubeView;
        this.cube = cube;
        this.cubeView.setArrowAction( key -> {
            if (key == Input.Keys.UP) {
                cube.moveUp();
            } else if (key == Input.Keys.RIGHT) {
                cube.moveRight();
            } else if (key == Input.Keys.DOWN) {
                cube.moveDown();
            } else if (key == Input.Keys.LEFT) {
                cube.moveLeft();
            }
        });
    }

}

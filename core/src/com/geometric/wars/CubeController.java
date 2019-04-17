package com.geometric.wars;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class CubeController {
    private CubeView cubeView;
    private Cube cube;
    public CubeController(CubeView cubeView, Cube cube) {
        this.cubeView = cubeView;
        this.cube = cube;
    }

    void processKeyInput() {
        if(Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            cube.moveUp();
            return;
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
            cube.moveRight();
            return;
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            cube.moveDown();
            return;
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
            cube.moveLeft();
            return;
        }
    }


}

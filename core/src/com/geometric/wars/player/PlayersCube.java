package com.geometric.wars.player;

import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.geometric.wars.cube.*;
import com.geometric.wars.input.InputController;

class PlayersCube {
    private CubeController cubeController;
    private Cube cube;
    private CubeView cubeView;

    public PlayersCube(int x, int y, InputController input) {
        cubeView = new CubeView();
        cube = new Cube(cubeView);
        cube.setPosition(x,y);
        cubeController = new CubeInputController(cube,input);
    }

    /**
     * Should be called every frame.
     * Updates rotation and position of player.
     */
    public void update() {
        cubeController.processMoving();
        cube.updateRotationAndPosition();
    }


    /**
     * Renders player on screen.
     *
     * @param batch ModelBatch using to render.
     * @param environment Environment
     */
    public void render(ModelBatch batch, Environment environment) {
        batch.render(cubeView, environment);
    }

}
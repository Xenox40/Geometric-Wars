package com.geometric.wars.player;

import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.geometric.wars.cube.Cube;
import com.geometric.wars.cube.CubeController;
import com.geometric.wars.cube.CubeView;

public abstract class PlayersCube {
    protected CubeController cubeController;
    protected Cube cube;
    protected CubeView cubeView;


    public void setPosition(int x,int y) {
        cube.setPosition(x,y);
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
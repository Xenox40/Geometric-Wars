package com.geometric.wars.player;

import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.geometric.wars.cube.DynamicCube;
import com.geometric.wars.cube.DynamicCubeController;
import com.geometric.wars.cube.DynamicCubeView;

public abstract class PlayersCube {
    protected DynamicCubeController dynamicCubeController;
    protected DynamicCube dynamicCube;
    protected DynamicCubeView dynamicCubeView;


    public void setPosition(int x,int y) {
        dynamicCube.setPosition(x,y);
    }

    /**
     * Should be called every frame.
     * Updates rotation and position of player.
     */
    public void update() {
        dynamicCubeController.processMoving();
        dynamicCube.updateRotationAndPosition();
    }

    /**
     * Renders player on screen.
     *
     * @param batch ModelBatch using to render.
     * @param environment Environment
     */
    public void render(ModelBatch batch, Environment environment) {
        batch.render(dynamicCubeView, environment);
    }

}
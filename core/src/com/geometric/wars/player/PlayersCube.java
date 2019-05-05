package com.geometric.wars.player;

import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.geometric.wars.cube.CollidableDynamicCube;
import com.geometric.wars.cube.DynamicCubeController;

public abstract class PlayersCube {
    protected DynamicCubeController dynamicCubeController;
    protected CollidableDynamicCube dynamicCube;


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
        batch.render(dynamicCube.getView(), environment);
    }

}
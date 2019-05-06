package com.geometric.wars.player;

import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.geometric.wars.cube.CollidableDynamicCube;
import com.geometric.wars.cube.DynamicCubeController;

public abstract class PlayersCube extends CollidableDynamicCube {
    protected DynamicCubeController dynamicCubeController;

    public PlayersCube(DynamicCubeController controller) {
        this.dynamicCubeController = controller;
        controller.setControlledCube(this);
    }

    /**
     * Should be called every frame.
     * Updates rotation and position of player.
     */
    public void update() {
        dynamicCubeController.processMoving();
        this.updateRotationAndPosition();
    }

    /**
     * Renders player on screen.
     *
     * @param batch ModelBatch using to render.
     * @param environment Environment
     */
    public void render(ModelBatch batch, Environment environment) {
        batch.render(this.getView(), environment);
    }
}
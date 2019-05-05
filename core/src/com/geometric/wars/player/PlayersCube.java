package com.geometric.wars.player;

import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.geometric.wars.collisions.DynamicBody;
import com.geometric.wars.cube.DynamicCube;
import com.geometric.wars.cube.DynamicCubeController;

public abstract class PlayersCube implements DynamicBody {
    protected DynamicCubeController dynamicCubeController;
    protected DynamicCube dynamicCube;


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


    @Override
    public boolean canCollideWith(DynamicBody object) {
        if(object instanceof PlayersController)
            return false;
        throw new RuntimeException("dynamicGameObject "+object+" not supported by PlayersController collisions");
    }

    @Override
    public void onCollisionWith(DynamicBody object) {}
}
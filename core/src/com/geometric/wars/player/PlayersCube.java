package com.geometric.wars.player;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.math.Vector2;
import com.geometric.wars.collisions.DynamicBody;
import com.geometric.wars.cube.DynamicCube;
import com.geometric.wars.cube.DynamicCubeController;
import com.geometric.wars.maps.MapService;
import com.geometric.wars.scene.SceneManager;
import com.geometric.wars.utils.Direction2D;
import com.geometric.wars.utils.Position;

/**
 * Dynamic cube that interacts with collision system and has own controller
 */
public abstract class PlayersCube extends DynamicCube implements DynamicBody {
    private MapService service = SceneManager.getInstance().getCurrentMapService();

    protected DynamicCubeController dynamicCubeController;
    private String name;


    public PlayersCube(DynamicCubeController controller) {
        super();
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


    /**
     * also checks collisions
     * @param direction
     */
    public boolean canMove(Direction2D direction) {
        Position newPos = new Position(getPosition().x + (int)direction.toVector2().x, (getPosition().y + (int)direction.toVector2().y));
        return (!isMoving() && service.isMoveAllowed(this,(int)newPos.x,(int)newPos.y));
    }


    @Override
    public void move(Direction2D direction) {
        if(canMove(direction)){
            super.move(direction);
            service.extendCollisionArea(this,(int) getApproachingPosition().x, (int) getApproachingPosition().y);
        }
    }

    @Override
    protected void finishRotating() {
        service.decreaseCollisionArea(this,(int)getPosition().x,(int)getPosition().y);
        super.finishRotating();
    }

    @Override
    public boolean canCollideWith(DynamicBody object) {
        if(object instanceof DynamicCube) {
            return object.equals(this); //self collisions are allowed
        }
        throw new RuntimeException("dynamicGameObject "+object+" not supported by PlayersCube collisions");
    }

    @Override
    public void onCollisionWith(DynamicBody object) {}

    @Override
    public boolean exists() {
        return true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Color getColor()  {
        return color;
    }
}


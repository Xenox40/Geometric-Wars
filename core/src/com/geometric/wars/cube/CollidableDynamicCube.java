package com.geometric.wars.cube;

import com.badlogic.gdx.math.Vector2;
import com.geometric.wars.collisions.DynamicBody;
import com.geometric.wars.player.PlayersController;
import com.geometric.wars.scene.SceneManager;
import com.geometric.wars.utils.Direction2D;

/**
 * should be built only with DynamicCubeBuilder, manages its view
 */
public class CollidableDynamicCube extends DynamicCube implements DynamicBody {

    CollidableDynamicCube() {
        super();
    }

    private boolean moveIsAllowed(Direction2D direction2D) {
        Vector2 nextPosition = direction2D.toVector2();
        return !(SceneManager.getInstance().getCurrentMapService().checkIfIsOccupied((int)getPosition().x + (int)nextPosition.x, (int)getPosition().y + (int)nextPosition.y));
    }

    @Override
    public void move(Direction2D direction) {
        if(moveIsAllowed(direction))
            super.move(direction);
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

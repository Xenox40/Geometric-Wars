package com.geometric.wars.cube;

import com.badlogic.gdx.math.Vector2;
import com.geometric.wars.collisions.DynamicBody;
import com.geometric.wars.maps.MapService;
import com.geometric.wars.scene.SceneManager;
import com.geometric.wars.utils.Direction2D;

/**
 * should be built only with DynamicCubeBuilder, manages its view
 */
public class CollidableDynamicCube extends DynamicCube implements DynamicBody {
    private MapService service = SceneManager.getInstance().getCurrentMapService();
    CollidableDynamicCube() {
        super();
    }


    @Override
    public void move(Direction2D direction) {
        Vector2 newPos = getPosition().cpy().add(direction.toVector2());
        if(!isMoving() && service.isMoveAllowed(this,(int)newPos.x,(int)newPos.y)) {
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
        if(object instanceof DynamicCube)
            return false;
        throw new RuntimeException("dynamicGameObject "+object+" not supported by PlayersController collisions");
    }

    @Override
    public void onCollisionWith(DynamicBody object) {}

}

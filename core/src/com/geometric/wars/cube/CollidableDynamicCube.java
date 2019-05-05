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


    @Override
    public void move(Direction2D direction) {
        Vector2 newPos = getPosition().cpy().add(direction.toVector2());
        if(SceneManager.getInstance().getCurrentMapService().isMoveAllowed(this,(int)newPos.x,(int)newPos.y)) {
            super.move(direction);
            SceneManager.getInstance().getCurrentMapService().updatePosition(this,(int)getPosition().x,(int)getPosition().y,(int)newPos.x,(int)newPos.y);
        }
    }

    @Override
    public boolean canCollideWith(DynamicBody object) {
        return true;
        //if(object instanceof DynamicCube)
       //     return false;
       // throw new RuntimeException("dynamicGameObject "+object+" not supported by PlayersController collisions");
    }

    @Override
    public void onCollisionWith(DynamicBody object) {}

}

package com.geometric.wars.player.bots.randomactingbot;

import com.badlogic.gdx.math.MathUtils;
import com.geometric.wars.utils.Direction2D;
import com.geometric.wars.cube.DynamicCubeController;


public class DynamicCubeRandomController extends DynamicCubeController {

    public DynamicCubeRandomController() {
        super();
    }

    @Override
    public void processMoving() {
        int val = MathUtils.random(0, 3);
        if(val == 0){
            dynamicCube.move(Direction2D.UP);
        }
        else if(val == 1){
            dynamicCube.move(Direction2D.RIGHT);
        }
        else if(val == 2) {
            dynamicCube.move(Direction2D.DOWN);
        }
        else {
            dynamicCube.move(Direction2D.LEFT);
        }
    }
}

package com.geometric.wars.player.bots.randomactingbot;

import com.badlogic.gdx.math.MathUtils;
import com.geometric.wars.Direction;
import com.geometric.wars.cube.DynamicCube;
import com.geometric.wars.cube.DynamicCubeController;


public class DynamicCubeRandomController extends DynamicCubeController {

    public DynamicCubeRandomController(DynamicCube dynamicCube) {
        super(dynamicCube);
    }

    @Override
    public void processMoving() {
        int val = MathUtils.random(0, 3);
        if(val == 0){
            dynamicCube.move(Direction.UP);
        }
        else if(val == 1){
            dynamicCube.move(Direction.RIGHT);
        }
        else if(val == 2) {
            dynamicCube.move(Direction.DOWN);
        }
        else {
            dynamicCube.move(Direction.LEFT);
        }
    }
}

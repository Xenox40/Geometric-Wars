package com.geometric.wars.player.bots.randomactingbot;

import com.badlogic.gdx.math.MathUtils;
import com.geometric.wars.Direction;
import com.geometric.wars.cube.Cube;
import com.geometric.wars.cube.CubeController;


public class CubeRandomController extends CubeController {

    public CubeRandomController(Cube cube) {
        super(cube);
    }

    @Override
    public void processMoving() {
        int val = MathUtils.random(0, 3);
        if(val == 0){
            cube.move(Direction.UP);
        }
        else if(val == 1){
            cube.move(Direction.RIGHT);
        }
        else if(val == 2) {
            cube.move(Direction.DOWN);
        }
        else {
            cube.move(Direction.LEFT);
        }
    }
}

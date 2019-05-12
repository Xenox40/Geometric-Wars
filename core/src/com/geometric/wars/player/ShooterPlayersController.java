package com.geometric.wars.player;

import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;

public class ShooterPlayersController extends PlayersController {
    /**
     * @param x               - x coordinate of dynamicCube on a grid
     * @param y               - y coordinate of dynamicCube on a grid
     */
    public ShooterPlayersController(int x, int y, PlayersCubeFactory cubeFactory) {
        super(1,cubeFactory, new int[]{x}, new int[]{y});
        if(!(getCube() instanceof  ShootingPlayersCube)) {
            throw new RuntimeException("ShooterPlayersController cannot controll "+getCube());
        }
    }

    private PlayersCube getCube() {
        return cubes.get(0);
    }

    @Override
    public void update() {
        if(!((ShootingPlayersCube) getCube()).isAlive())
            return;
        super.update();
    }

    @Override
    public void render(ModelBatch batch, Environment environment) {
        if(getCube() instanceof ShootingPlayersCube &&
                !((ShootingPlayersCube) getCube()).isAlive())
            return;
        super.render(batch, environment);
    }
}

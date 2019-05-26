package com.geometric.wars.player;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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

    public ShootingPlayersCube getCube() { return (ShootingPlayersCube) cubes.get(0); }


    @Override
    public void update() {
        if(!(getCube()).isAlive())
            return;
        super.update();
    }

    @Override
    public void render(ModelBatch batch, Environment environment) {
        if (!(getCube().isAlive()))
            return;
        super.render(batch, environment);
    }

}

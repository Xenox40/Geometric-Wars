package com.geometric.wars.player;

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

    public void shoot() {
        ((ShootingPlayersCube)getCube()).shoot();
    }

    @Override
    public void update() {
        super.update();
    }


}

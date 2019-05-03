package com.geometric.wars.player;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.geometric.wars.Direction2D;
import com.geometric.wars.Direction3D;
import com.geometric.wars.cube.CubeFace;
import com.geometric.wars.cube.DynamicCube;
import com.geometric.wars.cube.mountables.MountableGun;

public class ShooterPlayersController extends PlayersController {

    private int healthPoints;
    private int ammo;
    private MountableGun gun;

    /**
     * @param x               - x coordinate of dynamicCube on a grid
     * @param y               - y coordinate of dynamicCube on a grid
     */
    public ShooterPlayersController(int x, int y, PlayersCubeFactory cubeFactory) {
        super(1,cubeFactory, new int[]{x}, new int[]{y});

        for(int i=0;i<6;i++){
            CubeFace face = getCube().dynamicCube.getFaceAt(Direction3D.values()[i]);
            if(face.getMountedObject() != null && face.getMountedObject() instanceof MountableGun) {
                gun = (MountableGun) face.getMountedObject();
                break;
            }
        }
    }

    private PlayersCube getCube() {
        return cubes.get(0);
    }

    public void shoot() {
        if(gun == null)
            return;
        Direction3D shootingDirection = getCube().dynamicCube.getFaceOrientation(gun.getFaceMountedAt());
        Vector3 gunPosition = new Vector3(getCube().dynamicCube.getApproachingPosition().x,0,getCube().dynamicCube.getApproachingPosition().y).add(shootingDirection.toVector3());
        // TODO implement shooting
    }

    @Override
    public void update() {
        super.update();
    }

    public boolean isAlive() {
        return healthPoints > 0;
    }

    public void takeHp(int hp) {
        healthPoints -= hp;
        if (healthPoints < 0)
            healthPoints = 0;
    }
}

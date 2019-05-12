package com.geometric.wars.player;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.TimeUtils;
import com.geometric.wars.collisions.DynamicBody;
import com.geometric.wars.cube.CubeFace;
import com.geometric.wars.cube.DynamicCubeController;
import com.geometric.wars.cube.mountables.MountableGun;
import com.geometric.wars.gameobjects.Bullet;
import com.geometric.wars.scene.SceneManager;
import com.geometric.wars.utils.Direction3D;

public class ShootingPlayersCube extends PlayersCube {
    public ShootingPlayersCube(DynamicCubeController controller) {
        super(controller);
    }

    private void findGun() {
        for(int i=0;i<6;i++) {
            CubeFace face = getFaceAt(Direction3D.values()[i]);
            if(face.getMountedObject() != null && face.getMountedObject() instanceof MountableGun) {
                gun = (MountableGun) face.getMountedObject();
                break;
            }
        }
    }

    private int healthPoints = 50;
    private long lastShootTimeInMillis;
    private MountableGun gun;

    public boolean isAlive() {
        return healthPoints > 0;
    }

    public void takeHp(int hp) {
        healthPoints -= hp;
        if (healthPoints < 0)
            healthPoints = 0;
    }

    public void shoot() {
        if(gun == null)
            findGun();

        if(isMoving() || TimeUtils.timeSinceMillis(lastShootTimeInMillis) < gun.getWaitingTimeInMillis())
            return;

        lastShootTimeInMillis = TimeUtils.millis();

        Direction3D shootingDirection = getFaceOrientation(gun.getFaceMountedAt());
        Vector3 gunPosition = new Vector3(getApproachingPosition().x,0,getApproachingPosition().y).add(shootingDirection.toVector3());

        if(shootingDirection != Direction3D.BOTTOM && shootingDirection != Direction3D.TOP) {
            SceneManager.getInstance().getCurrentShootingService().shootBullet(gun, (int) gunPosition.x, (int) gunPosition.z, shootingDirection.toDirection2D());
        }
    }


    @Override
    public boolean canCollideWith(DynamicBody object) {
        if(object instanceof Bullet) {
            return true;
        }
        return super.canCollideWith(object);
    }

    @Override
    public void onCollisionWith(DynamicBody object) {
        if(object instanceof Bullet) {
            object.onCollisionWith(this);
            return;
        }
        super.onCollisionWith(object);
    }
}

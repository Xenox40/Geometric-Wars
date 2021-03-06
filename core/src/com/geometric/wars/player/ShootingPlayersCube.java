package com.geometric.wars.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.TimeUtils;
import com.geometric.wars.collisions.DynamicBody;
import com.geometric.wars.cube.CubeFace;
import com.geometric.wars.cube.DynamicCubeController;
import com.geometric.wars.cube.mountables.MountableGun;
import com.geometric.wars.gameobjects.Bullet;
import com.geometric.wars.scene.OverheatCalculator;
import com.geometric.wars.scene.SceneManager;
import com.geometric.wars.utils.Direction3D;

public class ShootingPlayersCube extends PlayersCube {
    public static final int startingHp = 25;
    public boolean isInvincible = false;

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
        if(gun == null)
            throw new RuntimeException("trying to get gun before it's created");
    }

    private int healthPoints = startingHp;
    private long lastShootTimeInMillis;
    private MountableGun gun;

    public MountableGun getGun() {
        if(gun == null)
            findGun();
        return gun;
    }

    public boolean isAlive() {
        return healthPoints > 0;
    }


    public void setGunHeatLevel(float heat) {
        try {
            getGun().setHeatLevel(heat);
        }
        catch (RuntimeException e) {}
    }
    public int getHealthPoints() {
        return healthPoints;
    }

    public float getGunHeatLevel() {
        try {
            return getGun().getHeatLevel();
        }
        catch (RuntimeException e) {
            return 0;
        }
    }

    public void takeHp(int hp) {
        if(!isInvincible)
            setHealthPoints(getHealthPoints()-hp);
    }
    public void setHealthPoints(int hp) {
        healthPoints = hp;
        if (healthPoints <= 0) {
            healthPoints = 0;
        }
    }

    public boolean canShoot(){
        return !(isMoving() || !SceneManager.getInstance().getCurrentShootingService().canShoot(getGun(),lastShootTimeInMillis));
    }

    public void shoot() {
        if(!canShoot())
            return;
        lastShootTimeInMillis = TimeUtils.millis();

        Direction3D shootingDirection = getFaceOrientation(getGun().getFaceMountedAt());
        Vector3 gunPosition = new Vector3(getApproachingPosition().x,0,getApproachingPosition().y).add(shootingDirection.toVector3());

        if(shootingDirection != Direction3D.BOTTOM && shootingDirection != Direction3D.TOP) {
            SceneManager.getInstance().getCurrentShootingService().shootBullet(this, (int) gunPosition.x, (int) gunPosition.z, shootingDirection.toDirection2D());
        }
    }


    @Override
    public void update() {
        super.update();
        getGun().setHeatLevel(OverheatCalculator.getHeatLevelAfterCooling(getGun(), Gdx.graphics.getDeltaTime()));
    }

    @Override
    public boolean canCollideWith(DynamicBody object) {
        if(!isAlive())
            return true;
        if(object instanceof Bullet) {
            return true;
        }
        return super.canCollideWith(object);
    }

    @Override
    public void onCollisionWith(DynamicBody object) {
        if(!isAlive())
            return;
        if(object instanceof Bullet) {
            ((Bullet) object).onHit(this);
        }
        super.onCollisionWith(object);
    }
}

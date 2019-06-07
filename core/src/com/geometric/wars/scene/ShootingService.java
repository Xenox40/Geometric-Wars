package com.geometric.wars.scene;

import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.utils.Array;
import com.geometric.wars.cube.mountables.MountableGun;
import com.geometric.wars.gameobjects.Bullet;
import com.geometric.wars.gameobjects.DynamicGameObject;
import com.geometric.wars.player.ShootingPlayersCube;
import com.geometric.wars.utils.Direction2D;

import java.util.Iterator;

public class ShootingService implements DynamicGameObject {
    private Array<Bullet> bullets = new Array<>();
    /**
     * for optimization purposes, bullets aren't actually removed an disposed
     * instead, they are move to removeBullets Array where they can be reused
     */
    private Array<Bullet> removedBullets = new Array<>();

    public boolean canShoot(MountableGun gun, long lastShootingTime) {
        return OverheatCalculator.canShoot(gun,lastShootingTime);
    }

    public void shootBullet(ShootingPlayersCube cube, int startX, int startY, Direction2D direction2D) {
        Bullet bullet;
        if(removedBullets.size == 0)
            bullet = new Bullet(startX,startY, cube.getGun().getDamage());
        else {
            bullet = removedBullets.pop();
            bullet.setParams(startX,startY, cube.getGun().getDamage());
        }
        bullet.setOwner(cube);
        SceneManager.getInstance().getCurrentMapService().extendCollisionArea(bullet,startX,startY);
        bullet.shoot(cube.getGun().getBulletSpeed(), direction2D.toDirection3D());
        cube.getGun().setHeatLevel(OverheatCalculator.getHeatLevelAfterShoot(cube.getGun()));
        bullets.add(bullet);
    }

    @Override
    public void render(ModelBatch modelBatch, Environment environment) {
        for(Bullet bullet : bullets)
            modelBatch.render(bullet,environment);
    }

    @Override
    public void update() {
        for(Iterator<Bullet> it = bullets.iterator(); it.hasNext();){
            Bullet bullet = it.next();
            bullet.update();
            if(!bullet.exists()) {
                removedBullets.add(bullet);
                it.remove();
            }

        }
    }

    public void dispose() {
        while (!removedBullets.isEmpty())
            removedBullets.pop();
    }
}

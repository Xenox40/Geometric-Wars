package com.geometric.wars.scene;

import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.utils.Array;
import com.geometric.wars.cube.mountables.MountableGun;
import com.geometric.wars.gameobjects.Bullet;
import com.geometric.wars.gameobjects.DynamicGameObject;
import com.geometric.wars.utils.Direction2D;

import java.util.Iterator;

public class ShootingService implements DynamicGameObject {
    private Array<Bullet> bullets = new Array<>();

    public void shootBullet(MountableGun gun, int startX, int startY, Direction2D direction2D) {
        Bullet bullet = new Bullet(startX,startY,gun.getDamage());
        SceneManager.getInstance().getCurrentMapService().extendCollisionArea(bullet,startX,startY);
        bullet.shoot(gun.getBulletSpeed(), direction2D.toDirection3D());
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
            if(bullet.isDestroyed())
                it.remove();
            else
                bullet.update();
        }
    }
}

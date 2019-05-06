package com.geometric.wars.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;
import com.geometric.wars.collisions.DynamicBody;
import com.geometric.wars.gameobjects.enviromentparts.Wall;
import com.geometric.wars.models.BulletModel;
import com.geometric.wars.player.ShootingPlayersCube;
import com.geometric.wars.scene.SceneManager;
import com.geometric.wars.utils.Direction3D;
import com.geometric.wars.utils.Values;

public class Bullet extends ModelInstance implements DynamicBody {

    /**
     * speed is in cells per seconds
     */
    private float speed;
    private int damage;
    private int startX,startY;


    /**
     * current position x and position y
     */
    private int px, py;
    private boolean startedMoving;
    private Direction3D direction;
    private int lastX,lastY;

    private boolean destroyed;

    /**
     * Create new instance of Bullet model, and
     * place it on given coordinates.
     *
     * @param x - x coordinate on a plane grid
     * @param y - y coordinate on a plane grid
     */
    public Bullet(int x, int y, int damage) {
        super(BulletModel.getModel(), new Vector3(x * Values.unit, 0, y * Values.unit));
        this.startX = this.px = this.lastX = x;
        this.startY = this.py = this.lastY = y;
        this.damage = damage;
        this.speed = speed;
    }

    public void shoot(float speed, Direction3D shootingDirection) {
        this.speed = speed;
        this.direction = shootingDirection;
        this.startedMoving = true;
    }

    public void update() {
        if(startedMoving) {
            float deltaTranslation = speed * Gdx.graphics.getDeltaTime();
            transform.translate(direction.toVector3().scl(deltaTranslation));

            Vector3 translation = transform.getTranslation(new Vector3());

            px = Math.round(translation.x);
            py = Math.round(translation.z);

            if(px != lastX || py != lastY){
                SceneManager.getInstance().getCurrentMapService().decreaseCollisionArea(this,lastX,lastY);
                lastX = px;
                lastY = py;
                SceneManager.getInstance().getCurrentMapService().extendCollisionArea(this,px,py);
            }
            if(!SceneManager.getInstance().getCurrentMapService().isMoveAllowed(this,px,py)) {
                destroy();
                return;
            }
        }
    }

    private void destroy() {
        SceneManager.getInstance().getCurrentMapService().decreaseCollisionArea(this,px,py);
        this.startedMoving = false;
        destroyed = true;
    }

    @Override
    public boolean canCollideWith(DynamicBody object) {
        if (object instanceof Wall)
            return false;
        return true;
    }

    @Override
    public void onCollisionWith(DynamicBody object) {
        if(object instanceof ShootingPlayersCube){
            ((ShootingPlayersCube) object).takeHp(damage);
            destroy();
            return;
        }
    }

    @Override
    public boolean exists() {
        return !destroyed;
    }

}
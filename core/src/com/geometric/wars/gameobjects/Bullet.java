package com.geometric.wars.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.geometric.wars.collisions.DynamicBody;
import com.geometric.wars.cube.CollidableDynamicCube;
import com.geometric.wars.models.BulletModel;
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
    private float totalDistance;



    /**
     * Create new instance of Bullet model, and
     * place it on given coordinates.
     *
     * @param x - x coordinate on a plane grid
     * @param y - y coordinate on a plane grid
     */
    public Bullet(int x, int y, int damage) {
        super(BulletModel.getModel(), new Vector3(x * Values.unit, 0, y * Values.unit));
        this.startX = x;
        this.startY = y;
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
            if(Math.round(totalDistance+deltaTranslation) != Math.round(deltaTranslation)){
                Vector2 mv = direction.toDirection2D().toVector2().scl(Math.round(totalDistance+deltaTranslation));
                SceneManager.getInstance().getCurrentMapService().decreaseCollisionArea(this,px,py);
                px = startX + (int)mv.x;
                py = startY + (int)mv.y;
                SceneManager.getInstance().getCurrentMapService().extendCollisionArea(this,px,py);
            }
            totalDistance += deltaTranslation;
        }
    }

    @Override
    public boolean canCollideWith(DynamicBody object) {
        return true;
    }

    @Override
    public void onCollisionWith(DynamicBody object) {
        if(object instanceof CollidableDynamicCube) {

        }
    }
}
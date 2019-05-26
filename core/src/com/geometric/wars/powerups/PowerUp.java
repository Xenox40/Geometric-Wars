package com.geometric.wars.powerups;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.utils.ObjectMap;
import com.geometric.wars.collisions.DynamicBody;
import com.geometric.wars.gameobjects.StaticGameObject;
import com.geometric.wars.models.PowerUpModel;
import com.geometric.wars.player.ShootingPlayersCube;
import com.geometric.wars.utils.Position;
import com.geometric.wars.utils.Values;

public abstract class PowerUp  extends ModelInstance implements StaticGameObject {
    public PowerUp() {
        super(PowerUpModel.getModel());
    }
    private Position position;
    private boolean isAlive = true;

    public void remove() {
        isAlive = false;
    }

    /**
     * the bigger power is, the less chance of spawning this powerUp have
     * @return power (in range [1,10]
     */
    public abstract int getPower();

    protected abstract void applyEffectTo(ShootingPlayersCube cube);

    @Override
    public void render(ModelBatch modelBatch, Environment environment) {
        if(exists())
            modelBatch.render(this, environment);
    }

    @Override
    public void cache(ModelCache cache) {
        cache.add(this);
    }

    @Override
    public boolean canCollideWith(DynamicBody object) {
        return true;
    }

    @Override
    public void onCollisionWith(DynamicBody object) {
        if(object instanceof ShootingPlayersCube) {
            applyEffectTo((ShootingPlayersCube)object);
            remove();
        }
    }

    @Override
    public boolean exists() {
        return isAlive;
    }

    public void setPosition(int x, int y) {
        this.position = new Position(x,y);
        transform.translate(x * Values.unit,0, y * Values.unit);
    }

    public Position getPosition() {
        return position;
    }

    protected void setColor(Color color) {
        this.materials.get(0).set(ColorAttribute.createDiffuse(color));
    }
}
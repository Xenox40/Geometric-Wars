package com.geometric.wars.powerups;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.geometric.wars.collisions.DynamicBody;
import com.geometric.wars.gameobjects.StaticGameObject;
import com.geometric.wars.models.PowerUpModel;
import com.geometric.wars.player.ShootingPlayersCube;
import com.geometric.wars.scene.SceneManager;
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

    protected abstract void applyEffectTo(ShootingPlayersCube cube, EffectApplicator applicator);

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
            applyEffectTo((ShootingPlayersCube)object, SceneManager.getInstance().getCurrentScene().getPowerUpService().getEffectApplicator());
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
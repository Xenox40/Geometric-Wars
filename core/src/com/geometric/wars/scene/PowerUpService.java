package com.geometric.wars.scene;

import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelCache;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.geometric.wars.gameobjects.DynamicGameObject;
import com.geometric.wars.player.ShootingPlayersCube;
import com.geometric.wars.powerups.*;
import com.geometric.wars.utils.Position;

import java.util.Iterator;

public class PowerUpService implements DynamicGameObject {
    private EffectApplicator applicator = new EffectApplicator();
    public PowerUpService() {

    }

    Array<PowerUp> powerUps = new Array<>();
    private ModelCache powerUpCache = new ModelCache();
    private boolean cacheUpdatingRequired = false;


    private static final int maxiPowerUpCountPer100emptyCells = 1;
    private int relativeMaxiPowerUpCount = -1;


    public void addPowerUp(PowerUp powerUp, int x,int y) {
        powerUp.setPosition(y,x);
        powerUps.add(powerUp);
        SceneManager.getInstance().getCurrentMapService().addCollidable(powerUp,x,y);
        cacheUpdatingRequired = true;
    }

    @Override
    public void render(ModelBatch modelBatch, Environment environment) {
        modelBatch.render(powerUpCache,environment);
    }

    @Override
    public void update() {
        if(relativeMaxiPowerUpCount == -1)
            updateMaxiPowerUpCount();

        if(powerUps.size < relativeMaxiPowerUpCount) {
            addRandomPowerUp();
        }

        applicator.update();

        for (Iterator<PowerUp> it = powerUps.iterator(); it.hasNext();) {
            PowerUp powerUp = it.next();
            if(!powerUp.exists()) {
                cacheUpdatingRequired = true;
                SceneManager.getInstance().getCurrentMapService().removeCollidable(powerUp,powerUp.getPosition().x,powerUp.getPosition().y);
                it.remove();
            }
        }

        if(cacheUpdatingRequired) {
            updateMaxiPowerUpCount();
            updateCache();
        }
    }

    public void applyPowerUpTo(ShootingPlayersCube cube, PowerUp powerUp) {
        powerUp.onCollisionWith(cube);
    }

    private void addRandomPowerUp() {
        PowerUp powerUp;
        int rand = MathUtils.random(200);
        if(rand <= 40) {
            powerUp = new DamagePowerUp(2);
        }
        else if(rand <= 90) {
            powerUp = new NoOverHeatPowerUp();
        }
        else if(rand <= 140) {
            powerUp = new HealingPowerUp();
        }
        else if (rand <= 190) {
            powerUp = new MovementSpeedPowerUp();
        }
        else {
            powerUp = new InvincibilityPowerUp(8000);
        }

        if(SceneManager.getInstance().getCurrentMapService().getEmptyCells().size == 0)
            return;
        Position position = SceneManager.getInstance().getCurrentMapService().getEmptyCells().random();
        addPowerUp(powerUp,position.x,position.y);
    }

    private void updateMaxiPowerUpCount() {
        int emptyCells = SceneManager.getInstance().getCurrentMapService().getEmptyCells().size;
        relativeMaxiPowerUpCount =  emptyCells*maxiPowerUpCountPer100emptyCells/100 + MathUtils.random(1) + 1;
    }

    private void updateCache() {
        if(cacheUpdatingRequired) {
            powerUpCache.dispose();
            powerUpCache = new ModelCache();
            powerUpCache.begin();
            for (PowerUp powerUp : powerUps)
                powerUp.cache(powerUpCache);
            powerUpCache.end();
            cacheUpdatingRequired = false;
        }
    }
    public void dispose() {
        if(powerUpCache != null) {
            powerUpCache.dispose();
        }
    }

    public EffectApplicator getEffectApplicator() {
        return applicator;
    }
}

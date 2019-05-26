package com.geometric.wars.scene;

import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelCache;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.geometric.wars.gameobjects.DynamicGameObject;
import com.geometric.wars.powerups.DoubleDamagePowerUp;
import com.geometric.wars.powerups.EffectApplicator;
import com.geometric.wars.powerups.NoOverHeatPowerUp;
import com.geometric.wars.powerups.PowerUp;
import com.geometric.wars.utils.Position;

import java.util.Iterator;

public class PowerUpService implements DynamicGameObject {
    Array<PowerUp> powerUps = new Array<>();
    private ModelCache powerUpCache = new ModelCache();
    private boolean cacheUpdatingRequired = false;


    private static final int maxiPowerUpCountPer100emptyCells = 5;
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

        EffectApplicator.getInstance().update();

        for (Iterator<PowerUp> it = powerUps.iterator(); it.hasNext();) {
            PowerUp powerUp = it.next();
            if(!powerUp.exists()) {
                cacheUpdatingRequired = true;
                SceneManager.getInstance().getCurrentMapService().removeCollidable(powerUp,powerUp.getPosition().x,powerUp.getPosition().y);
                it.remove();
            }
        }

        if(cacheUpdatingRequired) {
            updateCache();
        }
    }

    private void addRandomPowerUp() {
        updateMaxiPowerUpCount();
        if(powerUps.size < relativeMaxiPowerUpCount) {
            PowerUp powerUp;
            int rand = MathUtils.random(100);
            if(rand <= 40) {
                powerUp = new DoubleDamagePowerUp();
            }
            else {
                powerUp = new NoOverHeatPowerUp();
            }

            Position position = SceneManager.getInstance().getCurrentMapService().getEmptyCells().random();
            addPowerUp(powerUp,position.x,position.y);
        }
    }

    private void updateMaxiPowerUpCount() {
        int emptyCells = SceneManager.getInstance().getCurrentMapService().getEmptyCells().size;
        relativeMaxiPowerUpCount =  emptyCells*maxiPowerUpCountPer100emptyCells/100;
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
    private void dispose() {
        if(powerUpCache != null)
            powerUpCache.dispose();
    }
}

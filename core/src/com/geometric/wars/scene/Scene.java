package com.geometric.wars.scene;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelCache;
import com.badlogic.gdx.utils.Array;
import com.geometric.wars.gameobjects.DynamicGameObject;
import com.geometric.wars.gameobjects.StaticGameObject;
import com.geometric.wars.models.BarModel;
import com.geometric.wars.player.ShooterPlayersController;
import com.geometric.wars.player.ShootingPlayersCube;

public class Scene {
    private Array<StaticGameObject> staticMapObjects;
    private Array<DynamicGameObject> dynamicMapObjects;
    private ModelCache staticModelsCache;
    private NinePatch healthBarModel;
    private NinePatch overheatBarModel;
    private BitmapFont font;
    private static final float barHeight = 10f;

    private boolean areStaticObjectsCacheUpdated;

    public Scene() {
        staticMapObjects = new Array<>();
        dynamicMapObjects = new Array<>();
        staticModelsCache = new ModelCache();
        healthBarModel = BarModel.getInstance().newBar(Color.GREEN);
        overheatBarModel = BarModel.getInstance().newBar(Color.RED);
        font = new BitmapFont();
    }


    public void addStaticGameObject(StaticGameObject object) {
        staticMapObjects.add(object);
        areStaticObjectsCacheUpdated = false;
    }

    public void addDynamicGameObject(DynamicGameObject object) {
        dynamicMapObjects.add(object);
    }

    public void update() {
        for(DynamicGameObject dynamicGameObject : dynamicMapObjects) {
            dynamicGameObject.update();
        }

        if (!areStaticObjectsCacheUpdated)
            updateStaticObjectCache();
    }

    public void renderGUI(SpriteBatch batch) {
        float posX = 20, posY = 40;
        for(DynamicGameObject dynamicGameObject : dynamicMapObjects) {
            if(dynamicGameObject instanceof ShooterPlayersController) {
                ShootingPlayersCube cube = (ShootingPlayersCube) ((ShooterPlayersController) dynamicGameObject).getCube(0);
                int hp = cube.getHealthPoints();
                float overheat = cube.getGunHeatLevel();
                if(hp > 0) {
                    font.draw(batch, ((ShooterPlayersController) dynamicGameObject).getCube(0).getName(), posX, posY+barHeight);
                    healthBarModel.draw(batch, posX+120f, posY, 5f*hp, barHeight);
                    overheatBarModel.draw(batch, posX+120,posY+barHeight*1.2f,5*overheat*35,barHeight);
                    posY += 2*barHeight+20;
                }
            }
        }
    }

    public void render(ModelBatch modelBatch, Environment environment) {
        modelBatch.render(staticModelsCache, environment);

        for(DynamicGameObject dynamicGameObject : dynamicMapObjects) {
            dynamicGameObject.render(modelBatch, environment);
        }
    }

    public void dispose() {
        staticMapObjects.clear();
        dynamicMapObjects.clear();
        staticModelsCache.dispose();
        BarModel.dispose();
        font.dispose();
    }

    private void updateStaticObjectCache() {
        areStaticObjectsCacheUpdated = true;
        staticModelsCache.begin();
        for (StaticGameObject object: staticMapObjects)
            object.cache(staticModelsCache);
        staticModelsCache.end();
    }
}

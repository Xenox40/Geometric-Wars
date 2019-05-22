package com.geometric.wars.scene;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelCache;
import com.badlogic.gdx.utils.Array;
import com.geometric.wars.gameobjects.DynamicGameObject;
import com.geometric.wars.gameobjects.StaticGameObject;
import com.geometric.wars.models.HealthBarModel;
import com.geometric.wars.player.ShooterPlayersController;

public class Scene {
    private Array<StaticGameObject> staticMapObjects;
    private Array<DynamicGameObject> dynamicMapObjects;
    private ModelCache staticModelsCache;
    private HealthBarModel healthBarModel;
    private BitmapFont font;

    private boolean areStaticObjectsCacheUpdated;

    public Scene() {
        staticMapObjects = new Array<>();
        dynamicMapObjects = new Array<>();
        staticModelsCache = new ModelCache();
        healthBarModel = new HealthBarModel();
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
                int hp = ((ShooterPlayersController) dynamicGameObject).getCubeHealthPoints();
                if(hp > 0) {
                    font.draw(batch, ((ShooterPlayersController) dynamicGameObject).getCube(0).getName(), posX, posY+10f);
                    healthBarModel.getBar().draw(batch, posX+120f, posY, 5f*hp, 10f);
                    posY += 30f;
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
        healthBarModel.dispose();
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

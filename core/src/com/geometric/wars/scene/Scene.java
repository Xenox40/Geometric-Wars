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
    Array<StaticGameObject> staticMapObjects;
    Array<DynamicGameObject> dynamicMapObjects;
    ModelCache staticModelsCache;
    NinePatch healthBarModel;
    NinePatch overheatBarModel;
    BitmapFont font;
    static final float barHeight = 10f;
    boolean isEndOfScene;
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

    public void renderGUI(SpriteBatch batch) {}

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

    public boolean hasEnded() {
        return isEndOfScene;
    }
}

package com.geometric.wars.scene;

import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelCache;
import com.badlogic.gdx.utils.Array;
import com.geometric.wars.gameobjects.DynamicGameObject;
import com.geometric.wars.gameobjects.StaticGameObject;

public class Scene {
    private Array<StaticGameObject> staticMapObjects;
    private Array<DynamicGameObject> dynamicMapObjects;
    private ModelCache staticModelsCache;

    private boolean areStaticObjectsCacheUpdated;

    public Scene() {
        staticMapObjects = new Array<>();
        dynamicMapObjects = new Array<>();
        staticModelsCache = new ModelCache();
    }


    public void addStaticGameOject(StaticGameObject object) {
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

    public void render(ModelBatch modelBatch, Environment environment) {
        modelBatch.render(staticModelsCache, environment);

        for(DynamicGameObject dynamicGameObject : dynamicMapObjects) {
            dynamicGameObject.render(modelBatch, environment);
        }
    }

    public void dispose() {
        staticModelsCache.dispose();
    }

    private void updateStaticObjectCache() {
        areStaticObjectsCacheUpdated = true;
        staticModelsCache.begin();
        for (StaticGameObject object: staticMapObjects)
            object.cache(staticModelsCache);
        staticModelsCache.end();
    }
}

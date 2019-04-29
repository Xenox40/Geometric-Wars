package com.geometric.wars.maps;

import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelCache;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.utils.Array;

public class Map {
    Array<StaticMapObject> staticMapObjects;
    ModelCache staticModelsCache;
    Array<DynamicMapObject> dynamicMapObjects;

    int width, height;

    public Map() {
        staticMapObjects = new Array<>();
        dynamicMapObjects = new Array<>();
    }

    public void update() {
        for(DynamicMapObject dynamicMapObject : dynamicMapObjects) {
            dynamicMapObject.update();
        }
    }

    public void render(ModelBatch modelBatch, Environment environment) {

        modelBatch.render(staticModelsCache,environment);

        for(DynamicMapObject dynamicMapObject : dynamicMapObjects) {
            dynamicMapObject.render(modelBatch, environment);
        }
    }
    
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }

    public void dispose() {
        staticModelsCache.dispose();
    }
}

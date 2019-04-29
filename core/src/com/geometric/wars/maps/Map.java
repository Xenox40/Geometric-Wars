package com.geometric.wars.maps;

import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.utils.Array;

public class Map {
    Array<StaticMapObject> staticMapObjects;
    Array<DynamicMapObject> dynamicMapObjects;
    int width, height;
    Array<Array<Boolean> > occupied;

    public Map() {
        staticMapObjects = new Array<>();
        dynamicMapObjects = new Array<>();
        occupied = new Array<>();
    }

    public void update() {
        for(DynamicMapObject dynamicMapObject : dynamicMapObjects) {
            dynamicMapObject.update();
        }
    }

    public void render(ModelBatch modelBatch, Environment environment) {
        for(StaticMapObject staticMapObject : staticMapObjects) {
            staticMapObject.render(modelBatch, environment);
        }
        for(DynamicMapObject dynamicMapObject : dynamicMapObjects) {
            dynamicMapObject.render(modelBatch, environment);
        }
    }
    public boolean isOccupied(int x, int y){
        if(x < 0 || y < 0 || x >= width || y >= height)
            return true;
        return occupied.get(y).get(x);
    }

    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
}

package com.geometric.wars.maps;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class MapService {

    public MapService() {
        mapObjects = new Array<>();
    }

    private Array<Array<MapObjectType>> mapObjects;
    private int width;
    private int height;


    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setMapObjects(Array<Array<MapObjectType>> mapObjects) {
        this.mapObjects = mapObjects;
    }

    public boolean checkIfIsOccupied(Vector2 vector2) {
        return checkIfIsOccupied((int)vector2.x,(int)vector2.y);
    }

    public boolean checkIfIsOccupied(int x,int y) {
        if(x < 0 || y < 0 || x >= width || y >= height)
            return true;
        return mapObjects.get(y).get(x) == MapObjectType.WALL; //TODO implement collision with players
    }

}

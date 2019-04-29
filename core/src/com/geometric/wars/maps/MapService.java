package com.geometric.wars.maps;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class MapService {
    private Map map;

    Array<Array<MapObjectType>> mapObjects;

    private static MapService instance;

    public static MapService getInstance() {
        if(instance == null)
            instance = new MapService();
        return instance;
    }

    private MapService(){
        mapObjects = new Array<>();
    }

    void setMap(Map map) {
        this.map = map;
    }

    public boolean checkIfIsOccupied(Vector2 vector2) {
        return checkIfIsOccupied((int)vector2.x,(int)vector2.y);
    }

    public boolean checkIfIsOccupied(int x,int y) {
        if(x < 0 || y < 0 || x >= map.getWidth() || y >= map.getHeight())
            return true;
        return mapObjects.get(y).get(x) == MapObjectType.WALL; //TODO implement collision with players
    }

}

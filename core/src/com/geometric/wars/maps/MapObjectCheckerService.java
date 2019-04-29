package com.geometric.wars.maps;

import com.badlogic.gdx.math.Vector2;

public class MapObjectCheckerService {
    private Map map;
    private boolean[][] ocuppedCells;
    private static MapObjectCheckerService instance;

    public static MapObjectCheckerService getInstance() {
        if(instance == null)
            instance = new MapObjectCheckerService();
        return instance;
    }

    private  MapObjectCheckerService(){}

    void loadMap(Map map) {
        this.map = map;
    }

    public boolean checkIfIsOccupied(Vector2 vector2) {
        return checkIfIsOccupied((int)vector2.x,(int)vector2.y);
    }

    public boolean checkIfIsOccupied(int x,int y) {
        return map.isOccupied(x,y);
    }

}

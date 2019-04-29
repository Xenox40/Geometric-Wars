package com.geometric.wars.maps;

import com.badlogic.gdx.math.Vector2;

public class MapObjectCheckerService {
    private Map map;
    private boolean[][] ocuppedCells;

    public MapObjectCheckerService(Map map){
        this.map = map;
    }

    public boolean checkIfIsOccupied(Vector2 vector2) {
        return checkIfIsOccupied((int)vector2.x,(int)vector2.y);
    }

    public boolean checkIfIsOccupied(int x,int y) {
        return map.isOccupied(x,y);
    }

}

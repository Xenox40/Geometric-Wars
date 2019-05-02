package com.geometric.wars.scene;

import com.geometric.wars.maps.MapService;

public class GameScene extends Scene {

    private MapService mapService;

    GameScene() {
        super();
        mapService = new MapService();
    }

    public MapService getMapService() {
        return mapService;
    }
}

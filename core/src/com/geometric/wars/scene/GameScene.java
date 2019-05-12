package com.geometric.wars.scene;

import com.geometric.wars.maps.MapService;

public class GameScene extends Scene {

    private MapService mapService;
    private ShootingService shootingService;

    GameScene() {
        super();
        mapService = new MapService();
        shootingService = new ShootingService();
        addDynamicGameObject(shootingService);
    }

    public MapService getMapService() {
        return mapService;
    }
    public ShootingService getShootingService() {
        return shootingService;
    }
}

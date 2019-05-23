package com.geometric.wars.scene;

import com.geometric.wars.maps.MapService;

public class GameScene extends Scene {

    private MapService mapService;
    private ShootingService shootingService;
    private RespawningService respawningService;

    GameScene() {
        super();
        mapService = new MapService();
        shootingService = new ShootingService();
        respawningService = new RespawningService();
        addDynamicGameObject(shootingService);
        addDynamicGameObject(respawningService);
    }

    public MapService getMapService() {
        return mapService;
    }
    public ShootingService getShootingService() {
        return shootingService;
    }
    public RespawningService getRespawningService() {
        return respawningService;
    }
}

package com.geometric.wars.scene;

import com.geometric.wars.gameobjects.DynamicGameObject;
import com.geometric.wars.maps.MapService;
import com.geometric.wars.player.ShooterPlayersController;
import com.geometric.wars.utils.Action;

public class GameScene extends Scene {

    private MapService mapService;
    private ShootingService shootingService;
    private RespawningService respawningService;
    private Scoreboard scoreboard;

    GameScene() {
        super();
        mapService = new MapService();
        shootingService = new ShootingService();
        respawningService = new RespawningService();
        scoreboard = new Scoreboard();
        scoreboard.addActionOnEndOfGame(new Action(){
            @Override
            public void doAction() {
                isEndOfScene = true;
            }
        });
        addDynamicGameObject(shootingService);
        addDynamicGameObject(respawningService);
        addDynamicGameObject(scoreboard);
    }

    @Override
    public void addDynamicGameObject(DynamicGameObject object) {
        super.addDynamicGameObject(object);
        if(object instanceof ShooterPlayersController)
            scoreboard.addPlayer((ShooterPlayersController)object);
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
    public Scoreboard getScoreboard() {return scoreboard;}
}

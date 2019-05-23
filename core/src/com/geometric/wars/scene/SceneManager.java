package com.geometric.wars.scene;

import com.geometric.wars.maps.GameMap;
import com.geometric.wars.GeometricWars;
import com.geometric.wars.maps.MapLoader;
import com.geometric.wars.maps.MapService;

public class SceneManager {

    private static SceneManager instance = null;
    private SceneManager() {

    }

    public static SceneManager getInstance() {
        if (instance == null)
            instance = new SceneManager();
        return instance;
    }

    private GeometricWars game;
    private GameScene currentScene;



    public GameScene getCurrentScene() {
        return currentScene;
    }

    public MapService getCurrentMapService() {
        return getCurrentScene().getMapService();
    }

    public ShootingService getCurrentShootingService() {
        return getCurrentScene().getShootingService();
    }

    public RespawningService getCurrentRespawningService() {
        return getCurrentScene().getRespawningService();
    }

    public SceneManager setGame(GeometricWars game) {
        this.game = game;
        return this;
    }

    public void loadGame(GameMap map) {
        if(currentScene != null) {
            currentScene.dispose();
            currentScene = null;
        }

        MapLoader mapLoader = new MapLoader();
        currentScene = new GameScene();
        mapLoader
                .setScene(currentScene)
                .load(map);
    }
}

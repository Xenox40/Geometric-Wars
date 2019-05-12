package com.geometric.wars.scene;

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
    private MapLoader mapLoader;

    public void loadGame(String mapName) {
        if (mapLoader == null)
            mapLoader = new MapLoader();
        currentScene = new GameScene();

        mapLoader.setFileName("maps/"+mapName)
                .setInputController(game.getInputController())
                .setScene(currentScene)
                .load();
    }

    public GameScene getCurrentScene() {
        return currentScene;
    }

    public MapService getCurrentMapService() {
        return getCurrentScene().getMapService();
    }

    public ShootingService getCurrentShootingService() {
        return getCurrentScene().getShootingService();
    }

    public SceneManager setGame(GeometricWars game) {
        this.game = game;
        return this;
    }
}

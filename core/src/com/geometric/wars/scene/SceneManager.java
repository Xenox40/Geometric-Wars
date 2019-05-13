package com.geometric.wars.scene;

import com.geometric.mapgenerators.GameMap;
import com.geometric.wars.GeometricWars;
import com.geometric.wars.maps.GeneratedMapLoader;
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


    public void loadGame(String mapName) {
        MapLoader mapLoader = new MapLoader();
        if(currentScene != null) {
            currentScene.dispose();
        }
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

    public void loadGame(GameMap map) {
        if(currentScene != null) {
            currentScene.dispose();
            currentScene = null;
        }

        MapLoader mapLoader = new GeneratedMapLoader(map);
        currentScene = new GameScene();
        mapLoader.setInputController(game.getInputController())
                .setScene(currentScene)
                .load();
    }
}

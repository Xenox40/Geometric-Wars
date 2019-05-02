package com.geometric.wars.screens;

import com.badlogic.gdx.Gdx;
import com.geometric.wars.GeometricWars;
import com.geometric.wars.TypeOfGame;
import com.geometric.wars.debug.axes.CoordinateAxes3D;
import com.geometric.wars.maps.MapService;
import com.geometric.wars.scene.Scene;
import com.geometric.wars.maps.MapLoader;
import com.geometric.wars.scene.SceneManager;

public class GameScreen extends AbstractScreen {
    CoordinateAxes3D axes;
    SceneManager sceneManager;

    public GameScreen(GeometricWars game, String mapName) {
        super(game);
        axes = new CoordinateAxes3D();
        sceneManager = SceneManager.getInstance();
        sceneManager.setGame(game).loadGame(mapName);

        camera.position.set(sceneManager.getCurrentMapService().getHeight() / 2f, 21f, sceneManager.getCurrentMapService().getHeight() * 7 / 4f);
        camera.lookAt(sceneManager.getCurrentMapService().getHeight()/2.0f,0.5f, sceneManager.getCurrentMapService().getHeight()/2.0f);
        camera.update();
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        //System.out.println(" FPS: "+ Gdx.graphics.getFramesPerSecond());

        sceneManager.getCurrentScene().update();

        batch.begin(camera);
        sceneManager.getCurrentScene().render(batch, environment);
        //axes.render(batch, environment);
        batch.end();
    }
    @Override
    public void dispose() {
        super.dispose();
        sceneManager.getCurrentScene().dispose();
    }
}

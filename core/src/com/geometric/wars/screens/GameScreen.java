package com.geometric.wars.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.geometric.wars.maps.GameMap;
import com.geometric.wars.GeometricWars;
import com.geometric.wars.scene.SceneManager;
import com.geometric.wars.utils.axes.CoordinateAxes3D;

public class GameScreen extends AbstractScreen {
    CoordinateAxes3D axes;
    SceneManager sceneManager;
    private Stage stage;
    private SpriteBatch spriteBatch;
    private ModelBatch batch;


    public GameScreen(GeometricWars game, GameMap map) {
        super(game);
        batch = new ModelBatch();
        spriteBatch = new SpriteBatch();

        axes = new CoordinateAxes3D();
        sceneManager = SceneManager.getInstance();
        sceneManager.setGame(game).loadGame(map);

        camera.position.set(sceneManager.getCurrentMapService().getWidth() / 2f, 21f,
                sceneManager.getCurrentMapService().getHeight() * 7 / 4f);

        Vector3 lootAtPoint = new Vector3(sceneManager.getCurrentMapService().getWidth() / 2f, 0.5f,
                sceneManager.getCurrentMapService().getHeight() / 2f);

        camera.lookAt(lootAtPoint);
        cameraController.target = lootAtPoint;
        camera.update();


    }

    @Override
    protected void createCamera() {
        if(game.isAndroidPlatform())
            camera = new PerspectiveCamera(65, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        else
            camera = new PerspectiveCamera(35, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        camera.near = 1f;
        camera.far = 300f;
        camera.update();
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        // System.out.println(" FPS: "+ Gdx.graphics.getFramesPerSecond());

        sceneManager.getCurrentScene().update();

        batch.begin(camera);
        sceneManager.getCurrentScene().render(batch, environment);
        batch.end();
        spriteBatch.begin();
        sceneManager.getCurrentScene().renderGUI(spriteBatch);
        spriteBatch.end();
    }

    @Override
    public void dispose() {
        super.dispose();
        sceneManager.getCurrentScene().dispose();
        batch.dispose();
        spriteBatch.dispose();
        sceneManager.getCurrentScene().dispose();
    }
}

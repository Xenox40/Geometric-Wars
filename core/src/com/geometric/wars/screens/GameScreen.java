package com.geometric.wars.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.geometric.wars.GeometricWars;
import com.geometric.wars.scene.SceneManager;
import com.geometric.wars.utils.axes.CoordinateAxes3D;

public class GameScreen extends AbstractScreen {
    CoordinateAxes3D axes;
    SceneManager sceneManager;
    private Stage stage;
    private SpriteBatch spriteBatch;
    private ModelBatch batch;
    private NinePatch health;
    private Texture bad;

    public GameScreen(GeometricWars game, String mapName) {
        super(game);
        batch = new ModelBatch();
        spriteBatch = new SpriteBatch();
        bad = new Texture(Gdx.files.internal("healthBar.jpg"));

        health = new NinePatch(bad, 0, 0, 0, 0);
        axes = new CoordinateAxes3D();
        sceneManager = SceneManager.getInstance();
        sceneManager.setGame(game).loadGame(mapName);

        camera.position.set(sceneManager.getCurrentMapService().getHeight() / 2f, 21f,
                sceneManager.getCurrentMapService().getHeight() * 7 / 4f);
        camera.lookAt(sceneManager.getCurrentMapService().getHeight() / 2.0f, 0.5f,
                sceneManager.getCurrentMapService().getHeight() / 2.0f);
        camera.update();
    }

    @Override
    protected void createCamera() {
        camera = new PerspectiveCamera(55, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
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
        health.draw(spriteBatch, 100f, 100f, 80f, 20f);
        spriteBatch.end();
    }

    @Override
    public void dispose() {
        super.dispose();
        bad.dispose();
        batch.dispose();
        spriteBatch.dispose();
        sceneManager.getCurrentScene().dispose();
    }
}

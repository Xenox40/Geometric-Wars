package com.geometric.wars.screens;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.geometric.wars.maps.GameMap;
import com.geometric.wars.GeometricWars;
import com.geometric.wars.scene.SceneManager;
import com.geometric.wars.utils.axes.CoordinateAxes3D;

public class GameScreen extends AbstractScreen {
    CoordinateAxes3D axes;
    SceneManager sceneManager;
    private Stage stage;

    private Environment environment;

    private SpriteBatch spriteBatch;
    private ModelBatch batch;


    public GameScreen(GeometricWars game) {
        super(game);

        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
        environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));
        viewport = new ExtendViewport(1, 1, camera);

        batch = new ModelBatch();
        spriteBatch = new SpriteBatch();



        axes = new CoordinateAxes3D();
        sceneManager = SceneManager.getInstance();
    }

    public void setMap(GameMap map) {
        sceneManager.setGame(game).loadGame(map);
    }


    @Override
    public void show() {

        if (Gdx.app.getType() == Application.ApplicationType.Desktop)
            Gdx.input.setInputProcessor(cameraController);
        else
            game.getInputController().activate();

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

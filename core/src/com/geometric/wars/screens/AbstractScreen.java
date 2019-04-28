package com.geometric.wars.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.geometric.wars.GeometricWars;

public abstract class AbstractScreen implements Screen {

    protected GeometricWars game;
    protected CameraInputController cameraController;
    protected PerspectiveCamera camera;

    protected ModelBatch batch;
    protected Environment environment;

    public AbstractScreen(GeometricWars game) {
        this.game = game;

        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
        environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));

        createCamera();
        cameraController = new CameraInputController(camera);
        Gdx.input.setInputProcessor(cameraController);

        batch = new ModelBatch();
    }

    private void createCamera() {
        camera = new PerspectiveCamera(70, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(0f, 7f, 10f);
        camera.lookAt(0,0,0);
        camera.near = 1f;
        camera.far = 300f;
        camera.update();
    }

    @Override
    public void render(float delta) {
        cameraController.update();
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
    }

    @Override
    public void pause() {
        //TODO: pause state handling
    }

    @Override
    public void resume() {
        //TODO: pause state handling
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    @Override
    public void show() { }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void hide() {

    }

}

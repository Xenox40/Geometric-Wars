package com.geometric.wars.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.geometric.wars.GeometricWars;

public class SplashScreen extends AbstractScreen {

    private Stage stage;
    private SpriteBatch spriteBatch;
    private Texture texture;

    public SplashScreen(GeometricWars game) {
        super(game);
        stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera));
        spriteBatch = new SpriteBatch();

        texture = new Texture(Gdx.files.internal("splash.png"));
        Image image1 = new Image(texture);
        image1.setPosition(0, 0);
        image1.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage.addActor(image1);
    }

    @Override
    protected void createCamera() {
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.update();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void dispose() {
        super.dispose();
        spriteBatch.dispose();
        texture.dispose();
        stage.dispose();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
    }

}
package com.geometric.wars.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.geometric.wars.GeometricWars;

public abstract class AbstractMenuScreen extends AbstractScreen {

    protected Stage stage;
    protected SpriteBatch spriteBatch;
    private TextureAtlas skinAtlas;
    protected Skin skin;

    public AbstractMenuScreen(GeometricWars game) {
        super(game);
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
        stage = new Stage(viewport);
        spriteBatch = new SpriteBatch();
        skinAtlas = new TextureAtlas("skins/uiskin.atlas");
        skin = new Skin(Gdx.files.internal("skins/uiskin.json"), skinAtlas);
    }

    @Override
    public void show() {
        stage.clear();
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    protected void createCamera() {
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width,height);
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void dispose() {
        super.dispose();
        spriteBatch.dispose();
        stage.dispose();
        skin.dispose();
        skinAtlas.dispose();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
    }
}

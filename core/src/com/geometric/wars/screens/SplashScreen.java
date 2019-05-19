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

public class SplashScreen extends AbstractMenuScreen {

    private Texture texture;
    private Image image;

    public SplashScreen(GeometricWars game) {
        super(game);

        texture = new Texture(Gdx.files.internal("splash.png"));
        image = new Image(texture);
        image.setPosition(0, 0);
        image.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        stage.addActor(image);
    }

    @Override
    public void dispose() {
        super.dispose();
        texture.dispose();
    }
}
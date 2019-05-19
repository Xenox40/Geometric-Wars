package com.geometric.wars.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.geometric.wars.GeometricWars;

public class SplashScreen extends AbstractMenuScreen {

    private Texture texture;
    private Image image;
    private long elapsedTimeInMillis;
    private long durationInMillis = 2000;
    private AbstractScreen nextScreen;

    public SplashScreen(GeometricWars game) {
        super(game);

        texture = new Texture(Gdx.files.internal("splash.png"));
        image = new Image(texture);
        image.setPosition(0, 0);
        image.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage.addActor(image);
    }

    public void setNextScreen(AbstractScreen nextScreen) {
        this.nextScreen = nextScreen;
    }

    @Override
    public void show() {
        super.show();
        elapsedTimeInMillis = 0;
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        elapsedTimeInMillis += delta*1000;
        if(elapsedTimeInMillis >= durationInMillis) {
            if(nextScreen == null)
                throw new RuntimeException("next screen after splash not found");
            game.setScreen(nextScreen);
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        texture.dispose();
    }
}
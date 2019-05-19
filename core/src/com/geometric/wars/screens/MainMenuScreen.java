package com.geometric.wars.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.geometric.wars.GeometricWars;

public class MainMenuScreen extends AbstractMenuScreen {

    private Table table;

    public MainMenuScreen(GeometricWars game) {
        super(game);
        table = new Table();
    }

    @Override
    public void show() {
        super.show();
        table.setFillParent(true);
        table.top();

        TextButton playButton = new TextButton("Play!", skin);
        TextButton customGameButton = new TextButton("Custom game", skin);
        TextButton howToPlayButton = new TextButton("How to play", skin);
        TextButton aboutButton = new TextButton("About", skin);
        TextButton exitButton = new TextButton("Exit", skin);

        playButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(game.splashScreen);
                game.splashScreen.setNextScreen(game.gameScreen);
            }
        });
        exitButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        table.add(playButton).minSize(500,80).expand();
        table.row();
        table.add(customGameButton).minSize(500,80).expand();
        table.row();
        table.add(howToPlayButton).minSize(500,80).expand();
        table.row();
        table.add(aboutButton).minSize(500,80).expand();
        table.row();
        table.add(exitButton).minSize(500,80).expand();

        //Add table to stage
        stage.addActor(table);
    }


    @Override
    public void dispose() {
        super.dispose();
        skin.dispose();
        skinAtlas.dispose();
    }

}

package com.geometric.wars.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.geometric.wars.GeometricWars;

public class MainMenuScreen extends AbstractMenuScreen {



    public MainMenuScreen(GeometricWars game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();
        Table table = new Table();
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
                game.setScreen(game.gameScreen);
            }
        });

        customGameButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(game.customGameScreen);
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


}

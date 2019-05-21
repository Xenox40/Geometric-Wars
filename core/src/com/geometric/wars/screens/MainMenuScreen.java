package com.geometric.wars.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.geometric.wars.GeometricWars;
import com.geometric.wars.maps.GameMap;

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

        final TextButton playButton = new TextButton("Play!", skin);
        final TextButton customGameButton = new TextButton("Custom game", skin);
        final TextButton optionsButton = new TextButton("Options", skin);
        final TextButton exitButton = new TextButton("Exit", skin);

        playButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.gameScreen.setMap(new GameMap("map2"));
                game.setScreen(game.gameScreen);
            }
        });

        customGameButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(game.customGameScreen);
            }
        });

        optionsButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(game.optionsScreen);
            }
        });

        exitButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        for (Button button : new Button[]{playButton,customGameButton,optionsButton,exitButton}){
            table.add(button).minSize(500, 80).expand();
            table.row();
        }

        stage.addActor(table);
    }


}

package com.geometric.wars.screens;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.geometric.wars.GeometricWars;

public class CustomGameScreen extends AbstractMenuScreen{

    public CustomGameScreen(GeometricWars game) {
        super(game);

    }

    @Override
    public void show() {
        super.show();
        Table table = new Table();
        table.setFillParent(true);
        table.top();

        TextButton playButton = new TextButton("Play!", skin);
        TextButton customGameButton = new TextButton("Map settings", skin);
        TextButton backButton = new TextButton("Back", skin);

        playButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(game.splashScreen);
                game.splashScreen.setNextScreen(game.gameScreen);
            }
        });
        backButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(game.mainMenuScreen);
            }
        });

        table.add(playButton).minSize(500,80).expand();
        table.row();
        table.add(customGameButton).minSize(500,80).expand();
        table.row();
        table.add(backButton).minSize(500,80).expand();

        stage.addActor(table);
    }


}
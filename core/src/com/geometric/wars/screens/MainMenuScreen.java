package com.geometric.wars.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
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

        final TextButton playButton = new TextButton("Play!", skin);
        final TextButton optionsButton = new TextButton("Options", skin);
        final TextButton howToPlayButton = new TextButton("How to play", skin);
        final TextButton exitButton = new TextButton("Exit", skin);

        playButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(game.gameCustomizeScreen);
            }
        });

        howToPlayButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(game.howToPlayScreen);
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

        for (Button button : new Button[]{playButton,optionsButton,howToPlayButton,exitButton}){
            table.add(button).minSize(500, 80).expand();
            table.row();
        }

        stage.addActor(table);
    }


}

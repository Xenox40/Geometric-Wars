package com.geometric.wars.screens;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.geometric.wars.GeometricWars;

public class OptionsScreen extends AbstractMenuScreen {
    public OptionsScreen(GeometricWars game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();

        Button controlPickButton = new TextButton("Controls",skin);
        Button aboutButton = new TextButton("About",skin);
        Button backButton = new TextButton("Back",skin);

        controlPickButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(game.controlPickScreen);
            }
        });

        backButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(game.mainMenuScreen);
            }
        });

        aboutButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(game.aboutScreen);
            }
        });

        Table table = new Table();
        table.setFillParent(true);
        table.top();
        table.add(controlPickButton).minSize(500,80).expand();
        table.row();
        table.add(aboutButton).minSize(500,80).expand();
        table.row();
        table.add(backButton).minSize(500,80).expand();
        table.row();

        stage.addActor(table);
    }
}

package com.geometric.wars.screens;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.geometric.wars.GeometricWars;
import com.geometric.wars.utils.Values;

public class AboutScreen extends AbstractMenuScreen {
    public AboutScreen(GeometricWars game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();

        Label label = new Label(Values.aboutText,skin);
        label.setWrap(true);
        Button backButton = new TextButton("Back",skin);
        backButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(game.mainMenuScreen);
            }
        });

        Table table = new Table();
        table.setFillParent(true);
        table.top();

        table.add(label).minSize(400,600).expand();
        table.row();
        table.add(backButton).minSize(500,80).align(Align.center).expand();
        table.row();

        stage.addActor(table);
    }
}

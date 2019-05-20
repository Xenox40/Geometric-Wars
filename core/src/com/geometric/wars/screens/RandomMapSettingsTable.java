package com.geometric.wars.screens;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class RandomMapSettingsTable {
    private CustomGameScreen screen;

    RandomMapSettingsTable(CustomGameScreen screen){
        this.screen = screen;
    }

    public Cell<Table> addTo(Table outerTable) {

        final Slider widthSlider = new Slider(5, 25, 1, false, screen.skin);
        widthSlider.setValue(screen.width);
        final Label widthSliderLabel = new Label("Width", screen.skin);
        widthSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                screen.width = (int) widthSlider.getValue();
            }
        });

        final Slider heightSlider = new Slider(5, 25, 1, false, screen.skin);
        heightSlider.setValue(screen.height);
        final Label heightSliderLabel = new Label("Height", screen.skin);
        heightSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                screen.height = (int) heightSlider.getValue();
            }
        });

        final Slider wallThresholdSlider = new Slider(0.4f, 0.6f, 0.01f, false, screen.skin);
        wallThresholdSlider.setValue(screen.wallThreshold);
        final Label wallThresholdSliderLabel = new Label("Walls", screen.skin);
        wallThresholdSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                screen.wallThreshold = wallThresholdSlider.getValue();
            }
        });


        final Table table = new Table();
        table.top();

        table.add(widthSliderLabel).minHeight(60).expandX();
        table.add(widthSlider).minSize(200,60).expandX();
        table.row();

        table.add(heightSliderLabel).minHeight(60).expandX();
        table.add(heightSlider).minSize(200,60).expandX();
        table.row();

        table.add(wallThresholdSliderLabel).minHeight(60).expandX();
        table.add(wallThresholdSlider).minSize(200,60).expandX();
        table.row();

        return outerTable.add(table);
    }
}

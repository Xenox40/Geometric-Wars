package com.geometric.wars.screens;

import com.geometric.wars.Cube;
import com.geometric.wars.GeometricWars;

public class GameScreen extends AbstractScreen {
    public GameScreen(GeometricWars game) {
        super(game);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        batch.begin(camera);
        batch.end();;
    }
}

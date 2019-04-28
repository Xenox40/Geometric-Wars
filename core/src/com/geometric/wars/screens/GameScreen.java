package com.geometric.wars.screens;

import com.geometric.wars.GeometricWars;
import com.geometric.wars.debug.axises.CoordinateAxises3D;

public class GameScreen extends AbstractScreen {
    CoordinateAxises3D axises;

    public GameScreen(GeometricWars game) {
        super(game);
        axises = new CoordinateAxises3D();
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        game.player.update();

        batch.begin(camera);
        axises.render(batch, environment);
        batch.render(game.floor1, environment);
        batch.render(game.floor2, environment);
        game.player.render(batch, environment);
        batch.end();
    }
}

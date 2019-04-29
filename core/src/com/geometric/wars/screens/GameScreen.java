package com.geometric.wars.screens;

import com.geometric.wars.GeometricWars;
import com.geometric.wars.debug.axes.CoordinateAxes3D;
import com.geometric.wars.maps.Map;
import com.geometric.wars.maps.MapLoader;

public class GameScreen extends AbstractScreen {
    CoordinateAxes3D axises;
    Map map;

    public GameScreen(GeometricWars game, String mapName) {
        super(game);
        axises = new CoordinateAxes3D();
        map = new MapLoader().setFileName("maps/"+mapName).setInputController(game.getInputController()).load();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        map.update();

        batch.begin(camera);
        map.render(batch, environment);
        axises.render(batch, environment);
        batch.end();
    }
}

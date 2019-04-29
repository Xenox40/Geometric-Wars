package com.geometric.wars.screens;

import com.geometric.wars.GeometricWars;
import com.geometric.wars.debug.axises.CoordinateAxises3D;
import com.geometric.wars.maps.Map;
import com.geometric.wars.maps.MapLoader;

public class GameScreen extends AbstractScreen {
    CoordinateAxises3D axises;
    Map map;

    public GameScreen(GeometricWars game, String mapName) {
        super(game);
        axises = new CoordinateAxises3D();
        map = new MapLoader().setFileName("maps/"+mapName).load();
        System.out.println(new MapLoader().setFileName("maps/"+mapName).load().staticMapObjects.size);
        System.out.println(map.staticMapObjects.size);
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

package com.geometric.wars.screens;

import com.geometric.wars.GeometricWars;
import com.geometric.wars.debug.axes.CoordinateAxes3D;
import com.geometric.wars.maps.Map;
import com.geometric.wars.maps.MapLoader;

public class GameScreen extends AbstractScreen {
    CoordinateAxes3D axes;
    Map map;

    public GameScreen(GeometricWars game, String mapName) {
        super(game);
        axes = new CoordinateAxes3D();
        map = new MapLoader().setFileName("maps/"+mapName).setInputController(game.getInputController()).load();
        camera.position.set(map.getHeight()*1/2f, 12f, map.getWidth()*5/4f);
        camera.lookAt(map.getHeight()/2.0f,0,map.getWidth()/2.0f);
        camera.update();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        map.update();

        batch.begin(camera);
        map.render(batch, environment);
        //axes.render(batch, environment);
        batch.end();
    }
}

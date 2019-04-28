package com.geometric.wars.player.bots.randomactingbot;

import com.geometric.wars.cube.Cube;
import com.geometric.wars.cube.CubeView;
import com.geometric.wars.player.PlayersCube;

public class RandomBotCube extends PlayersCube {
    public RandomBotCube() {
        cubeView = new CubeView();
        cube = new Cube(cubeView);
        cubeController = new CubeRandomController(cube);
    }

    public RandomBotCube(int x, int y) {
        this();
        setPosition(x, y);
    }
}

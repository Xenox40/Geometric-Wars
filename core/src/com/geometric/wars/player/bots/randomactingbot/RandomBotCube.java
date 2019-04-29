package com.geometric.wars.player.bots.randomactingbot;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.geometric.wars.cube.Cube;
import com.geometric.wars.cube.CubeView;
import com.geometric.wars.player.PlayersCube;

public class RandomBotCube extends PlayersCube {
    public RandomBotCube()  {
        cubeView = new CubeView();
        cube = new Cube(cubeView);
        cubeController = new CubeRandomController(cube);
        cubeView.materials.get(0).set(ColorAttribute.createDiffuse(Color.PURPLE));
    }

    public RandomBotCube(int x, int y) {
        this();
        setPosition(x, y);
    }
}

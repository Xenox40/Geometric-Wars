package com.geometric.wars.player.bots.randomactingbot;

import com.badlogic.gdx.graphics.Color;
import com.geometric.wars.cube.DynamicCube;
import com.geometric.wars.cube.DynamicCubeView;
import com.geometric.wars.cube.DynamicCubeBuilder;
import com.geometric.wars.player.PlayersCube;

public class RandomBotCube extends PlayersCube {
    public RandomBotCube()  {
        DynamicCubeBuilder builder = new DynamicCubeBuilder();
        dynamicCube = builder.createCube().build();
        dynamicCubeView = dynamicCube.getView();
        dynamicCubeController = new DynamicCubeRandomController(dynamicCube);
        dynamicCubeView.setColor(Color.PURPLE);
    }

    public RandomBotCube(int x, int y) {
        this();
        setPosition(x, y);
    }
}

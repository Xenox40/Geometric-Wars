package com.geometric.wars.player.bots.randomactingbot;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.geometric.wars.cube.DynamicCube;
import com.geometric.wars.cube.DynamicCubeView;
import com.geometric.wars.player.PlayersCube;

public class RandomBotCube extends PlayersCube {
    public RandomBotCube()  {
        dynamicCubeView = new DynamicCubeView();
        dynamicCube = new DynamicCube(dynamicCubeView);
        dynamicCubeController = new DynamicCubeRandomController(dynamicCube);
        dynamicCubeView.materials.get(0).set(ColorAttribute.createDiffuse(Color.PURPLE));
    }

    public RandomBotCube(int x, int y) {
        this();
        setPosition(x, y);
    }
}

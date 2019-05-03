package com.geometric.wars.player.bots.randomactingbot;

import com.badlogic.gdx.graphics.Color;
import com.geometric.wars.Direction3D;
import com.geometric.wars.cube.DynamicCube;
import com.geometric.wars.cube.DynamicCubeView;
import com.geometric.wars.cube.DynamicCubeBuilder;
import com.geometric.wars.cube.mountables.guns.SimpleGun;
import com.geometric.wars.player.PlayersCube;

public class RandomBotCube extends PlayersCube {
    public RandomBotCube()  {
        DynamicCubeBuilder builder = new DynamicCubeBuilder();
        dynamicCube = builder.createCube(Color.PURPLE).addMountable(Direction3D.TOP, new SimpleGun()).build();
        dynamicCubeController = new DynamicCubeRandomController(dynamicCube);
    }

    public RandomBotCube(int x, int y) {
        this();
        setPosition(x, y);
    }
}

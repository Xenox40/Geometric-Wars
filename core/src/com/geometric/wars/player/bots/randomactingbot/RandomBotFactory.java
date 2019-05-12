package com.geometric.wars.player.bots.randomactingbot;

import com.badlogic.gdx.graphics.Color;
import com.geometric.wars.cube.DynamicCubeBuilder;
import com.geometric.wars.cube.mountables.guns.SimpleGun;
import com.geometric.wars.player.PlayersCubeFactory;
import com.geometric.wars.utils.Direction3D;

public class RandomBotFactory extends PlayersCubeFactory {

    public RandomBotFactory(){
        super();
        builder = new DynamicCubeBuilder(new RandomBotCube(new DynamicCubeRandomController()));
    }

    @Override
    public RandomBotCube createCube() {
        return (RandomBotCube) builder.createCube(Color.PURPLE).addMountable(Direction3D.DOWN, new SimpleGun()).build();
    }

    @Override
    public void endOfUpdatingAllCubes() {}
}
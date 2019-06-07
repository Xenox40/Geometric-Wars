package com.geometric.wars.player.bots.randomactingbot;

import com.badlogic.gdx.math.MathUtils;
import com.geometric.wars.cube.DynamicCubeBuilder;
import com.geometric.wars.cube.mountables.guns.DoubleRifledGun;
import com.geometric.wars.cube.mountables.guns.SimpleGun;
import com.geometric.wars.player.ColorAndNameGiver;
import com.geometric.wars.player.PlayersCubeFactory;
import com.geometric.wars.utils.Direction3D;

public class RandomBotFactory extends PlayersCubeFactory {

    public RandomBotFactory(){
        super();
        builder = new DynamicCubeBuilder(new RandomBotCube(new DynamicCubeRandomController()));
    }

    @Override
    public RandomBotCube createCube() {
        String name = ColorAndNameGiver.getRandomUnusedColorName();
        builder.createCube(ColorAndNameGiver.getColorByName(name));
        if(MathUtils.randomBoolean())
            builder.addMountable(Direction3D.DOWN, new SimpleGun());
        else
            builder.addMountable(Direction3D.DOWN,new DoubleRifledGun());
        RandomBotCube cube = (RandomBotCube)builder.build();
        cube.setName(name + " [E]");
        return cube;
    }

    @Override
    public void endOfUpdatingAllCubes() {}
}
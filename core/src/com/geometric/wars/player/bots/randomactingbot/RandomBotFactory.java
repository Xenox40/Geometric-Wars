package com.geometric.wars.player.bots.randomactingbot;

import com.badlogic.gdx.graphics.Color;
import com.geometric.wars.cube.DynamicCubeBuilder;
import com.geometric.wars.cube.mountables.guns.SimpleGun;
import com.geometric.wars.player.ColorAndNameGiver;
import com.geometric.wars.player.PlayersCubeFactory;
import com.geometric.wars.player.persons.shooting.ShootingPersonsCube;
import com.geometric.wars.utils.Direction3D;

public class RandomBotFactory extends PlayersCubeFactory {

    public RandomBotFactory(){
        super();
        builder = new DynamicCubeBuilder(new RandomBotCube(new DynamicCubeRandomController()));
    }

    @Override
    public RandomBotCube createCube() {
        String name = ColorAndNameGiver.getRandomColorName();
        RandomBotCube cube = (RandomBotCube) builder.createCube(ColorAndNameGiver.getColorByName(name)).addMountable(Direction3D.DOWN, new SimpleGun()).build();
        cube.setName(name);
        return cube;
    }

    @Override
    public void endOfUpdatingAllCubes() {}
}
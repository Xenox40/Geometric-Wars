package com.geometric.wars.player.bots.mediumbot;

import com.badlogic.gdx.math.MathUtils;
import com.geometric.wars.cube.DynamicCubeBuilder;
import com.geometric.wars.cube.mountables.guns.DoubleRifledGun;
import com.geometric.wars.cube.mountables.guns.SimpleGun;
import com.geometric.wars.player.ColorAndNameGiver;
import com.geometric.wars.player.PlayersCubeFactory;
import com.geometric.wars.utils.Direction3D;

public class MediumBotFactory extends PlayersCubeFactory {

    public MediumBotFactory(){
        super();
        builder = new DynamicCubeBuilder(new MediumBotCube(new MediumBotController()));
    }

    @Override
    public MediumBotCube createCube() {
        String name = ColorAndNameGiver.getRandomUnusedColorName();
        builder.createCube(ColorAndNameGiver.getColorByName(name));
        builder.addMountable(Direction3D.DOWN,new DoubleRifledGun());
        MediumBotCube cube = (MediumBotCube)builder.build();
        cube.setName(name);
        return cube;
    }

    @Override
    public void endOfUpdatingAllCubes() {}
}
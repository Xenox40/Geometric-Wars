package com.geometric.wars.player.persons.shooting;

import com.geometric.wars.cube.DynamicCubeBuilder;
import com.geometric.wars.cube.mountables.guns.DoubleRifledGun;
import com.geometric.wars.cube.mountables.guns.SimpleGun;
import com.geometric.wars.input.InputController;
import com.geometric.wars.player.ColorAndNameGiver;
import com.geometric.wars.player.PlayersCubeFactory;
import com.geometric.wars.player.persons.DynamicCubeInputController;
import com.geometric.wars.utils.Direction3D;

public class ShootingPersonsCubeFactory extends PlayersCubeFactory {
    InputController inputController;
    public ShootingPersonsCubeFactory(InputController controller){
        super();
        this.inputController = controller;
        builder = new DynamicCubeBuilder(new ShootingPersonsCube(new DynamicCubeInputController(inputController)));
    }

    @Override
    public ShootingPersonsCube createCube() {
        String name = ColorAndNameGiver.getRandomUnusedColorName();
        ShootingPersonsCube cube = (ShootingPersonsCube) builder.createCube(ColorAndNameGiver.getColorByName(name)).addMountable(Direction3D.DOWN, new DoubleRifledGun()).build();
        cube.setName(name+" [P]");
        return cube;
    }

    @Override
    public void endOfUpdatingAllCubes() {
        inputController.endOnProcessingInput();
    }
}
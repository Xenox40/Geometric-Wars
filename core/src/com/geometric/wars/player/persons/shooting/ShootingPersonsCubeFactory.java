package com.geometric.wars.player.persons.shooting;

import com.badlogic.gdx.graphics.Color;
import com.geometric.wars.cube.DynamicCubeBuilder;
import com.geometric.wars.cube.mountables.guns.SimpleGun;
import com.geometric.wars.input.InputController;
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
        return (ShootingPersonsCube) builder.createCube(Color.RED).addMountable(Direction3D.DOWN, new SimpleGun()).build();
    }

    @Override
    public void endOfUpdatingAllCubes() {
        inputController.endOnProcessingInput();
    }
}
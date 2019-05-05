package com.geometric.wars.player.person;

import com.badlogic.gdx.graphics.Color;
import com.geometric.wars.utils.Direction3D;
import com.geometric.wars.cube.DynamicCubeBuilder;
import com.geometric.wars.cube.mountables.guns.SimpleGun;
import com.geometric.wars.input.InputController;
import com.geometric.wars.player.PlayersCube;

public class PersonsCube extends PlayersCube {
    public PersonsCube(InputController inputController) {
        DynamicCubeBuilder builder = new DynamicCubeBuilder();
        dynamicCube = builder.createCube(Color.RED).addMountable(Direction3D.DOWN, new SimpleGun()).build();
        dynamicCubeController = new DynamicCubeInputController(dynamicCube, inputController);
    }
    public PersonsCube(InputController inputController, int x, int y) {
        this(inputController);
        setPosition(x,y);
    }
}

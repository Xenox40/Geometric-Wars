package com.geometric.wars.player.person;

import com.badlogic.gdx.graphics.Color;
import com.geometric.wars.Direction3D;
import com.geometric.wars.cube.DynamicCube;
import com.geometric.wars.cube.DynamicCubeView;
import com.geometric.wars.cube.Mountable;
import com.geometric.wars.cube.SimpleGun;
import com.geometric.wars.input.InputController;
import com.geometric.wars.player.PlayersCube;

public class PersonsCube extends PlayersCube {
    public PersonsCube(InputController inputController) {
        dynamicCubeView = new DynamicCubeView();
        dynamicCube = new DynamicCube(dynamicCubeView);
        dynamicCubeController = new DynamicCubeInputController(dynamicCube, inputController);
        dynamicCubeView.setColor(Color.RED);
        dynamicCube.addMountable(Direction3D.TOP, new SimpleGun());
    }
    public PersonsCube(InputController inputController, int x, int y) {
        this(inputController);
        setPosition(x,y);
    }
}

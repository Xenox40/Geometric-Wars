package com.geometric.wars.player.person;

import com.geometric.wars.cube.Cube;
import com.geometric.wars.cube.CubeView;
import com.geometric.wars.input.InputController;
import com.geometric.wars.player.PlayersCube;

public class PersonsCube extends PlayersCube {
    public PersonsCube(InputController inputController) {
        cubeView = new CubeView();
        cube = new Cube(cubeView);
        cubeController = new CubeInputController(cube, inputController);
    }
    public PersonsCube(InputController inputController, int x, int y) {
        this(inputController);
        setPosition(x,y);
    }
}

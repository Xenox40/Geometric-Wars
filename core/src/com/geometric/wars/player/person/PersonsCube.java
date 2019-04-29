package com.geometric.wars.player.person;

import com.geometric.wars.cube.Cube;
import com.geometric.wars.cube.CubeView;
import com.geometric.wars.input.InputController;
import com.geometric.wars.maps.MapObjectCheckerService;
import com.geometric.wars.player.PlayersCube;

public class PersonsCube extends PlayersCube {
    public PersonsCube(MapObjectCheckerService mapObjectCheckerService, InputController inputController) {
        cubeView = new CubeView();
        cube = new Cube(cubeView,mapObjectCheckerService);
        cubeController = new CubeInputController(cube, inputController);
    }
    public PersonsCube(MapObjectCheckerService mapObjectCheckerService, InputController inputController, int x, int y) {
        this(mapObjectCheckerService, inputController);
        setPosition(x,y);
    }
}

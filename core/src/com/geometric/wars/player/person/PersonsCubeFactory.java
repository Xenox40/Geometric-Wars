package com.geometric.wars.player.person;

import com.geometric.wars.input.InputController;
import com.geometric.wars.maps.MapObjectCheckerService;
import com.geometric.wars.player.PlayersCube;
import com.geometric.wars.player.PlayersCubeFactory;

public class PersonsCubeFactory extends PlayersCubeFactory {
    InputController inputController;
    public PersonsCubeFactory(MapObjectCheckerService mapObjectCheckerService, InputController controller){
        super(mapObjectCheckerService);
        this.inputController = controller;
    }

    @Override
    public PlayersCube createCube(int x,int y) {
        return new PersonsCube(mapObjectCheckerService, inputController, x, y);
    }

    @Override
    public void endOfUpdatingAllCubes() {
        inputController.endOnProcessingInput();
    }
}

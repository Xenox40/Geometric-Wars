package com.geometric.wars.player.person;

import com.geometric.wars.input.InputController;
import com.geometric.wars.player.PlayersCubeFactory;
import com.geometric.wars.player.PlayersCube;

public class PersonsCubeFactory extends PlayersCubeFactory {
    InputController inputController;
    public PersonsCubeFactory(InputController controller){
        this.inputController = controller;
    }

    @Override
    public PlayersCube createCube(int x,int y) {
        return new PersonsCube(inputController, x, y);
    }
}

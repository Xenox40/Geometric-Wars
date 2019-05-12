package com.geometric.wars.player.persons.logical;

import com.badlogic.gdx.graphics.Color;
import com.geometric.wars.cube.DynamicCubeBuilder;
import com.geometric.wars.input.InputController;
import com.geometric.wars.player.PlayersCubeFactory;
import com.geometric.wars.player.persons.DynamicCubeInputController;


public class LogicalPersonsCubeFactory extends PlayersCubeFactory {
    InputController inputController;
    public LogicalPersonsCubeFactory(InputController controller){
        super();
        this.inputController = controller;
        builder = new DynamicCubeBuilder(new LogicalPersonsCube(new DynamicCubeInputController(inputController)));
    }

    @Override
    public LogicalPersonsCube createCube() {
        return (LogicalPersonsCube) builder.createCube(Color.RED).build();
    }

    @Override
    public void endOfUpdatingAllCubes() {
        inputController.endOnProcessingInput();
    }
}

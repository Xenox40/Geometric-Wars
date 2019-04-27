package com.geometric.wars.player;

import com.geometric.wars.cube.Cube;
import com.geometric.wars.cube.CubeController;
import com.geometric.wars.cube.CubeView;
import com.geometric.wars.input.InputController;

class PlayersCube extends CubeView {
    private CubeController cubeController;
    private Cube cube;

    public PlayersCube(int x, int y, InputController input) {
        cube = new Cube(this);
        cubeController = new CubeController(cube, input);
    }

    public PlayersCube(int x, int y) {
        cube = new Cube(this);
        cubeController = new CubeController(cube);
    }

    /**
     * Should be called every frame.
     * Updates rotation and position of player.
     */
    public void update() {
        cubeController.processKeyInput();
        cube.updateRotationAndPosition();
    }
}
package com.geometric.wars.player;

import com.geometric.wars.input.InputController;

public class ShooterPlayersController extends PlayersController {

    private int healthPoints;
    private int ammo;

    /**
     * @param inputController - a way in which player input his moves
     * @param x               - x coordinate of cube on a grid
     * @param y               - y coordinate of cube on a grid
     */
    public ShooterPlayersController(int x, int y, InputController inputController) {
        super(1, inputController, new int[]{x}, new int[]{y});
    }

    public void shoot() {
        // TODO implement shooting
    }

    public boolean isAlive() {
        return healthPoints > 0;
    }

    public void takeHp(int hp) {
        healthPoints -= hp;
        if (healthPoints < 0)
            healthPoints = 0;
    }
}

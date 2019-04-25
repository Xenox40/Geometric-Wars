package com.geometric.wars.player;

import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.geometric.wars.input.InputController;

import java.util.ArrayList;
import java.util.List;

/**
 * Class represents player.
 * Player is build with a number of cubes (for example in logic mode),
 * which are represents by PlayersCube objects.
 */
public class PlayersControler {

    private List<PlayersCube> cubes;

    /**
     * @param numberOfCubes - number of cubes which player moves
     * @param inputController - a way in which player input his moves
     * @param X - array of x coordinates of cubes on a grid
     * @param Y - array of y coordinates of cubes on a grid
     */
    public PlayersControler(int numberOfCubes, InputController inputController, int[] X, int[] Y) {
        cubes = new ArrayList<>();
        for (int i = 0; i < numberOfCubes; i++)
            cubes.add(new PlayersCube(X[i], Y[i], inputController));
    }

    /**
     * Should be called every frame.
     * Updates rotation and position of player.
     */
    public void update() {
        for (PlayersCube p : cubes)
            p.update();
    }

    /**
     * Renders player on screen.
     *
     * @param batch ModelBatch using to render.
     * @param environment Environment
     */
    public void render(ModelBatch batch, Environment environment) {
        for (PlayersCube p : cubes)
            batch.render(p, environment);
    }
}


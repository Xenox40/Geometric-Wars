package com.geometric.wars.player;

import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.geometric.wars.gameobjects.DynamicGameObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Class represents player.
 * Player is build with a number of cubes (for example in logic mode),
 * which are represents by PlayersCube objects.
 */
public class PlayersController implements DynamicGameObject {

    protected List<PlayersCube> cubes;
    private PlayersCubeFactory cubeFactory;
    /**
     * @param numberOfCubes - number of cubes which player moves
     * @param X - array of x coordinates of cubes on a grid
     * @param Y - array of y coordinates of cubes on a grid
     */
    public PlayersController(int numberOfCubes, PlayersCubeFactory cubeFactory, int[] X, int[] Y) {
        this.cubeFactory = cubeFactory;
        cubes = new ArrayList<>();
        for (int i = 0; i < numberOfCubes; i++)
            cubes.add(cubeFactory.createCube(X[i],Y[i]));
    }

    /**
     * Should be called every frame.
     * Updates rotation and position of player.
     */
    public void update() {
        for (PlayersCube p : cubes)
            p.update();
        cubeFactory.endOfUpdatingAllCubes();
    }

    /**
     * Renders player on screen.
     *
     * @param batch ModelBatch using to render.
     * @param environment Environment
     */
    public void render(ModelBatch batch, Environment environment) {
        for (PlayersCube p : cubes)
            p.render(batch,environment);
    }

    public PlayersCube getCube(int i) {
        return cubes.get(i);
    }
}


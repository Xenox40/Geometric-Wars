package com.geometric.wars.player;


import com.geometric.wars.cube.DynamicCubeBuilder;

/**
 * abstract factory for cubes
 */
public abstract class PlayersCubeFactory {
    protected DynamicCubeBuilder builder;

    public abstract PlayersCube createCube();
    public final PlayersCube createCube(int x,int y) {
        PlayersCube res = createCube();
        res.setPosition(x,y);
        return res;
    }

    /**
     * will be called after updating all cubes
     */
    public void endOfUpdatingAllCubes(){}
}

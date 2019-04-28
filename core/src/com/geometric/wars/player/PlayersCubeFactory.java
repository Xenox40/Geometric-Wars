package com.geometric.wars.player;

public abstract class PlayersCubeFactory {
    public final PlayersCube createCube() {
        return createCube(0,0);
    }
    public abstract PlayersCube createCube(int x,int y);

    /**
     * will be called after updating all cubes
     */
    public void endOfUpdatingAllCubes(){}
}

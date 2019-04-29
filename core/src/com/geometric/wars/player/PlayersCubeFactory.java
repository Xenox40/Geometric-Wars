package com.geometric.wars.player;

import com.geometric.wars.maps.MapObjectCheckerService;

public abstract class PlayersCubeFactory {
    protected MapObjectCheckerService mapObjectCheckerService;

    public PlayersCubeFactory(MapObjectCheckerService mapObjectCheckerService) {
        this.mapObjectCheckerService = mapObjectCheckerService;
    }

    public final PlayersCube createCube() {
        return createCube(0,0);
    }
    public abstract PlayersCube createCube(int x,int y);

    /**
     * will be called after updating all cubes
     */
    public void endOfUpdatingAllCubes(){}
}

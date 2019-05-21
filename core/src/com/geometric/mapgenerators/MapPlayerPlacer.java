package com.geometric.mapgenerators;

import com.geometric.wars.maps.GameMap;

/**
 * places players on map
 */
public interface MapPlayerPlacer {
    void place(GameMap map) throws NoFreeSpaceForPlayersException;
}

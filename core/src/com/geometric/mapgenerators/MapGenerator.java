package com.geometric.mapgenerators;

import com.geometric.wars.maps.GameMap;

/**
 * generates random map with walls and ways between them
 */
public interface MapGenerator {
    GameMap generate(int mapWidth, int mapHeight);
}

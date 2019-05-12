package com.geometric.mapgenerators;

/**
 * generates random map with walls and ways between them
 */
public interface MapGenerator {
    GameMap generate(int mapWidth, int mapHeight);
}

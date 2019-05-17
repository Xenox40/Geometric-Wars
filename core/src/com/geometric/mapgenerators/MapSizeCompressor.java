package com.geometric.mapgenerators;

import com.geometric.wars.maps.GameMap;

/**
 * decreases size of map
 * can be used e.g. when map generator is better at generating bigger maps
 * and one also needs to have good small maps
 */
public interface MapSizeCompressor {
    GameMap compress(GameMap map, int width, int height);
}

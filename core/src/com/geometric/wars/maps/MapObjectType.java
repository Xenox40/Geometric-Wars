package com.geometric.wars.maps;

public enum MapObjectType {
    EMPTY,
    WALL,
    PLAYER,
    /**
     * map object that represents cell where player already has been but now is moving to different cell
     */
    PLAYERSPECTRUM;
}

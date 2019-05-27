package com.geometric.mapgenerators;

import com.geometric.wars.maps.GameMap;

/**
 * picks one of pre-defined maps
 * ignores map width and height
 * doesn't need Connector, PlayerPlacer etc.
 */
public class StockMapPicker implements MapGenerator {
    private static int mapId = 1;
    private static final int mapCount = 3;

    @Override
    public GameMap generate(int mapWidth, int mapHeight) {
        GameMap map = new GameMap("map"+mapId);
        setMapId(getMapId()+1);
        return map;
    }

    @Override
    public boolean isSizeControllable() {
        return false;
    }

    public static void setMapId(int mapId) {
        StockMapPicker.mapId = mapId;
        if(StockMapPicker.mapId >= mapCount)
            StockMapPicker.mapId %= mapCount;
        if(StockMapPicker.mapId < 0)
            StockMapPicker.mapId = (StockMapPicker.mapId%mapCount+mapCount);
    }
    public static int getMapId() {
        return mapId;
    }

}

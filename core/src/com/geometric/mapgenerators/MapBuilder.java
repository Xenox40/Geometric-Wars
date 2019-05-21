package com.geometric.mapgenerators;

import com.geometric.wars.maps.GameMap;

public class MapBuilder {
    private MapGenerator generator;
    private CuttingMapSizeCompressor compressor;
    private DefaultMapConnector connector;
    private CornerMapPlayerPlacer playerPlacer;
    private int scaleW, scaleH;
    private static final int maximumTries = 10;

    public MapBuilder setGenerator(MapGenerator generator) {
        this.generator = generator;
        return this;
    }

    public MapGenerator getGenerator(){
        return generator;
    }

    public MapBuilder setConnector(DefaultMapConnector connector) {
        this.connector = connector;
        return this;
    }

    public MapBuilder setCompressor(CuttingMapSizeCompressor compressor, int scaleW, int scaleH) {
        this.compressor = compressor;
        this.scaleW = scaleW;
        this.scaleH = scaleH;

        return this;
    }

    public MapBuilder setPlayerPlacer(CornerMapPlayerPlacer playerPlacer) {
        this.playerPlacer = playerPlacer;
        return this;
    }

    public GameMap build(int width, int height){
        if(generator == null)
            throw new RuntimeException("Map generator for map builder must be specified");

        for(int i=0;i<maximumTries;i++) {
            GameMap map;
            if (compressor != null) {
                map = generator.generate(width * scaleW, height * scaleH);
                compressor.compress(map, width, height);
            } else
                map = generator.generate(width, height);
            if (connector != null) {
                connector.connectAllComponents(map);
            }
            if (playerPlacer != null) {
                try {
                    playerPlacer.place(map);
                    return map;
                } catch (NoFreeSpaceForPlayersException e) {
                    if(i+1 == maximumTries)
                        throw new RuntimeException(e);
                }
            }
            else
                return map;
        }
        return null;
    }

    public void clear() {
        generator = null;
        connector = null;
        compressor = null;
        playerPlacer = null;
        scaleH = scaleW = 1;
    }


    public static void main(String[] args) {
        MapBuilder builder = new MapBuilder();

        builder.setGenerator(new CellularMapGenerator(100,0.5f)).setCompressor(new CuttingMapSizeCompressor(),3,3).setConnector(new DefaultMapConnector(5)).setPlayerPlacer(new CornerMapPlayerPlacer(4,3));
        //builder.setGenerator(new TunnelingMapGenerator(0.55f, 4)).setPlayerPlacer(new CornerMapPlayerPlacer(4,3));
        builder.build(30,30).saveAs("map3",true);


    }

}

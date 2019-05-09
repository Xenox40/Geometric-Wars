package com.geometric.mapgenerators;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MapBuilder {
    private MapGenerator generator;
    private MapSizeCompressor compressor;
    private MapConnector connector;
    private MapPlayerPlacer playerPlacer;
    private int scaleW, scaleH;

    public MapBuilder setGenerator(MapGenerator generator) {
        this.generator = generator;
        return this;
    }
    public MapBuilder setConnector(MapConnector connector) {
        this.connector = connector;
        return this;
    }

    public MapBuilder setCompressor(MapSizeCompressor compressor, int scaleW, int scaleH) {
        this.compressor = compressor;
        this.scaleW = scaleW;
        this.scaleH = scaleH;

        return this;
    }

    public MapBuilder setPlayerPlacer(MapPlayerPlacer playerPlacer) {
        this.playerPlacer = playerPlacer;
        return this;
    }

    public GameMap build(int width, int height){
        if(generator == null)
            throw new RuntimeException("Map generator for map builder must be specified");
        GameMap map;
        if(compressor != null) {
            map = generator.generate(width*scaleW, height*scaleH);
            compressor.compress(map,width,height);
        }
        else
            map = generator.generate(width,height);
        if(connector != null) {
            connector.connectAllComponents(map);
        }
        if(playerPlacer != null)
            playerPlacer.place(map);

        return map;
    }




    public static void main(String[] args) {
        MapBuilder builder = new MapBuilder();
        builder.setGenerator(new ShooterMapGenerator()).setCompressor(new MapSizeCompressor(),3,3).setConnector(new MapConnector(10)).setPlayerPlacer(new MapPlayerPlacer(4,3));
        builder.build(20,40).saveAs("map3",true);


    }

}

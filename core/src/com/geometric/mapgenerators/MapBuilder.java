package com.geometric.mapgenerators;

public class MapBuilder {
    private MapGenerator generator;
    private CuttingMapSizeCompressor compressor;
    private DefaultMapConnector connector;
    private CornerMapPlayerPlacer playerPlacer;
    private int scaleW, scaleH;

    public MapBuilder setGenerator(MapGenerator generator) {
        this.generator = generator;
        return this;
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
        builder.setGenerator(new TunnelingMapGenerator(0.3f)).setConnector(new DefaultMapConnector(10)).setPlayerPlacer(new CornerMapPlayerPlacer(4,3));
        builder.build(10,10).saveAs("map3",true);


    }

}

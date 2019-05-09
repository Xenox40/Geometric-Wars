package com.geometric.mapgenerators;

import com.badlogic.gdx.math.MathUtils;

/**
 * cuts random fragment of map
 */
public class CuttingMapSizeCompressor implements MapSizeCompressor{
    @Override
    public GameMap compress(GameMap map, int width, int height) {
        if(map.getWidth() < width || map.getHeight() < height) {
            throw new RuntimeException("compressed map cannot be greater than original");
        }
        GameMap newMap = new GameMap(width,height);

        int sx = MathUtils.random(0,map.getHeight()-height);
        int sy = MathUtils.random(0,map.getWidth()-width);

        for(int i=0;i<height;i++) {
            for(int j=0;j<width;j++) {
                newMap.put(i,j,map.get(i+sx,j+sy));
            }
        }
        map.setTo(newMap);
        return map;
    }
}

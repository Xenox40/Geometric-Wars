package com.geometric.mapgenerators;

import com.badlogic.gdx.math.MathUtils;

public class TunnelingMapGenerator implements MapGenerator {
    private GameMap map;
    private float tunnelsPerSquareUnit;
    private int x,y;

    public TunnelingMapGenerator(float tunnelsPerSquareUnit) {
        this.tunnelsPerSquareUnit = tunnelsPerSquareUnit;
    }

    @Override
    public GameMap generate(int mapWidth, int mapHeight) {
        map = new GameMap(mapWidth,mapHeight);
        for(int i=0;i<mapHeight;i++) {
            for (int j = 0; j < mapWidth; j++) {
                map.putWall(i,j);
            }
        }
        x = MathUtils.random(map.getHeight()-1);
        y = MathUtils.random(map.getWidth()-1);

        int tunnelsCount = (int) (tunnelsPerSquareUnit * (double)map.getWidth()* (double)map.getHeight());

        while (tunnelsCount --> 0) {
            int len = MathUtils.random(20);

            int direction = MathUtils.random(3);
            if(direction == 0)
                digTunnel(len,1,0);
            if(direction == 1)
                digTunnel(len,-1,0);
            if(direction == 2)
                digTunnel(len,0,1);
            if(direction == 3)
                digTunnel(len,0,-1);

        }
        return map;
    }


    private void digTunnel(int len, int deltaX, int deltaY) {
        for(int i=0;i<len;i++){
            map.putEmpty(x,y);
            if(!map.isIn(x+deltaX,y+deltaY))
                return;
            x+=deltaX;
            y+=deltaY;
        }
    }
}

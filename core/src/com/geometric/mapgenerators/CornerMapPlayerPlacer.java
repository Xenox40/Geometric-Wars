package com.geometric.mapgenerators;

import com.badlogic.gdx.math.MathUtils;
import com.geometric.wars.maps.GameMap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class CornerMapPlayerPlacer implements MapPlayerPlacer{
    private GameMap map;
    private int botCount;
    private int totalPlayersCount;

    private int currentTotalPlayersCount;
    private int currentBotCount;

    public CornerMapPlayerPlacer(int totalPlayersCount, int botCount) {
        setPlayerCount(totalPlayersCount,botCount);
    }

    public CornerMapPlayerPlacer setPlayerCount(int totalPlayersCount, int botCount) {
        if(totalPlayersCount < botCount){
            throw new RuntimeException("totalPlayerCount must be greater or equal botCount");
        }
        this.totalPlayersCount = totalPlayersCount;
        this.botCount = botCount;
        return this;
    }

    @Override
    public void place(GameMap map) throws NoFreeSpaceForPlayersException {
        this.map = map;
        this.currentBotCount = botCount;
        this.currentTotalPlayersCount = totalPlayersCount;
        ArrayList<GameMap.WeightedPoint> emptyCells = new ArrayList<>();
        for(int i=0;i<map.getHeight();i++){
            for(int j=0;j<map.getWidth();j++){
                if(map.isEmpty(i,j))
                    emptyCells.add(new GameMap.WeightedPoint(i,j,1));
            }
        }
        if(emptyCells.size() < totalPlayersCount) {
            throw new NoFreeSpaceForPlayersException("not enough empty cells for "+totalPlayersCount+" players, available: "+emptyCells.size());
        }

        ArrayList<GameMap.WeightedPoint> emptyCellsDiagonallySorted = new ArrayList<>(emptyCells);

        Collections.sort(emptyCells, new Comparator<GameMap.WeightedPoint>() {
            @Override
            public int compare(GameMap.WeightedPoint t1, GameMap.WeightedPoint t2) {
                return Integer.compare(t1.x+t1.y,t2.x+t2.y);
            }
        });

        Collections.sort(emptyCellsDiagonallySorted, new Comparator<GameMap.WeightedPoint>() {
            @Override
            public int compare(GameMap.WeightedPoint t1, GameMap.WeightedPoint t2) {
                return Integer.compare(t1.x - t1.y, t2.x - t2.y);
            }
        });

        putPlayerOrFalse(emptyCells.get(0).x,emptyCells.get(0).y);
        putPlayerOrFalse(emptyCells.get(emptyCells.size()-1).x,emptyCells.get(emptyCells.size()-1).y);


        for(int i=0;i<emptyCellsDiagonallySorted.size();i++) {
            GameMap.WeightedPoint point = emptyCellsDiagonallySorted.get(i);
            if(putPlayerOrFalse(point.x, point.y))
                break;
        }

        for(int i=emptyCellsDiagonallySorted.size()-1;i>=0;i--) {
            GameMap.WeightedPoint point = emptyCellsDiagonallySorted.get(i);
            if(putPlayerOrFalse(point.x,point.y))
                break;
        }


        while (currentTotalPlayersCount > 4) {
            int ind = MathUtils.random(emptyCells.size());
            GameMap.WeightedPoint cell = emptyCells.get(ind);
            if(map.isEmpty(cell.x,cell.y)) {
                putPlayerOrFalse(cell.x,cell.y);
            }
        }
    }

    private boolean putPlayerOrFalse(int x,int y) {
        if(!map.isEmpty(x,y))
            return false;
        if(currentBotCount == 0)
            putPerson(x,y);
        else if(currentBotCount == currentTotalPlayersCount)
            putBot(x,y);
        else if(MathUtils.randomBoolean())
            putBot(x,y);
        else
            putPerson(x,y);
        return true;
    }
    private void putBot(int x, int y){
        currentTotalPlayersCount--;
        currentBotCount--;
        map.putBot(x,y);
    }

    private void putPerson(int x, int y){
        currentTotalPlayersCount--;
        map.putPerson(x,y);
    }

}

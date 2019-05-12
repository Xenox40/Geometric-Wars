package com.geometric.mapgenerators;

import com.badlogic.gdx.math.MathUtils;
import java.util.ArrayList;

public class DefaultMapGenerator implements MapGenerator{

    public DefaultMapGenerator(int steps, float startingWallChance) {
        this.steps = steps;
        this.startingWallChance = startingWallChance;
    }

    private GameMap map;
    private int steps;
    private float startingWallChance;
    private float intoEmptyThreshold = 0.7f, intoWallThreshold = 0.7f;
    private float intoEmptyBound = 1f, intoWallBound = 0.9f;


    private GameMap.WeightedPoint[] neighbours;

    @Override
    public GameMap generate(int width, int height) {
        map = new GameMap(width,height);
        setNeighbours();
        createRandomMap();
        for(int i=0;i<steps;i++){
            nextMapStep();
        }
        return map;
    }

    private void setNeighbours() {
        ArrayList<GameMap.WeightedPoint> neighboursList = new ArrayList<>();
        neighboursList.add(new GameMap.WeightedPoint(-1,0,3));
        neighboursList.add(new GameMap.WeightedPoint(1,0,3));
        neighboursList.add(new GameMap.WeightedPoint(0,1,3));
        neighboursList.add(new GameMap.WeightedPoint(0,-1,3));
        neighboursList.add(new GameMap.WeightedPoint(-2,0,2));
        neighboursList.add(new GameMap.WeightedPoint(2,0,2));
        neighboursList.add(new GameMap.WeightedPoint(0,2,2));
        neighboursList.add(new GameMap.WeightedPoint(0,-2,2));
        neighboursList.add(new GameMap.WeightedPoint(-3,0,1));
        neighboursList.add(new GameMap.WeightedPoint(3,0,1));
        neighboursList.add(new GameMap.WeightedPoint(0,3,1));
        neighboursList.add(new GameMap.WeightedPoint(0,-3,1));

        neighbours = new GameMap.WeightedPoint[neighboursList.size()];
        int ct=0;
        for(GameMap.WeightedPoint p : neighboursList)
            neighbours[ct++] = p;
    }


    private void createRandomMap() {
        for(int i=0;i<map.getHeight();i++){
            for(int j=0;j<map.getWidth();j++){
                if(MathUtils.random(1f) <= startingWallChance) {
                   map.putWall(i,j);
                }
                else{
                    map.putEmpty(i,j);
                }
            }
        }
    }

    private void nextMapStep() {
        GameMap newMap = new GameMap(map.getWidth(),map.getHeight());

        for(int i=0;i<map.getHeight();i++) {
            for (int j = 0; j < map.getWidth(); j++) {
                int totalWeightSum = 0, wallsWeightSum = 0;
                for(GameMap.WeightedPoint point : neighbours) {
                    if(i+point.x >= 0 && i + point.x < map.getHeight() && j+point.y >= 0 && j+point.y < map.getWidth()) {
                        totalWeightSum += point.weight;
                        if(map.isWall(i+point.x,j+point.y)) {
                            wallsWeightSum += point.weight;
                        }
                    }
                }
                int emptiesWeightSum = totalWeightSum - wallsWeightSum;
                float random = MathUtils.random(1f);
                if(map.isWall(i,j)) {
                    if(emptiesWeightSum*1f/totalWeightSum >= intoEmptyThreshold && emptiesWeightSum*1f/totalWeightSum <= intoEmptyBound) {
                        newMap.putEmpty(i,j);
                    }
                    else
                        newMap.putWall(i,j);
                }
                else{
                    if(wallsWeightSum*1f/totalWeightSum >= intoWallThreshold && emptiesWeightSum*1f/totalWeightSum <= intoWallBound) {
                        newMap.putWall(i,j);
                    }
                    else
                        newMap.putEmpty(i,j);
                }
            }
        }
        map = newMap;
    }
}

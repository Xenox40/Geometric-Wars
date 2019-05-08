package com.geometric.mapgenerators;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class ShooterMapGenerator {

    private GameMap map;
    private int steps = 50;
    private float startingWallChance = 0.5f;
    private float intoEmptyThreshold = 0.5f, intoWallThreshold = 0.5f;


    private static class WeightedPoint{
        public int x,y;
        public int weight;
        public WeightedPoint(int x,int y, int weight){
            this.x = x;
            this.y = y;
            this.weight = weight;
        }
    }

    private WeightedPoint[] neighbours;

    private static Random randomGenerator = new Random();

    private void setNeighbours() {
        ArrayList<WeightedPoint> neighboursList = new ArrayList<>();
        for(int i=-1;i<=1;i++)
            for(int j=-1;j<=1;j++)
                if(i != 0 || j != 0) {
                    if(i == 0 || j == 0)
                        neighboursList.add(new WeightedPoint(i, j, 10));
                    else
                        neighboursList.add(new WeightedPoint(i, j, 1));
                }

        neighbours = new WeightedPoint[neighboursList.size()];
        int ct=0;
        for(WeightedPoint p : neighboursList)
            neighbours[ct++] = p;
    }


    public ShooterMapGenerator generate(int width, int height) {
        map = new GameMap(width,height);
        setNeighbours();
        createRandomMap();
        for(int i=0;i<steps;i++){
            nextMapStep();
        }
        return this;
    }


    private void createRandomMap() {
        for(int i=0;i<map.getHeight();i++){
            for(int j=0;j<map.getWidth();j++){
                if(randomGenerator.nextFloat() <= startingWallChance) {
                   map.putWall(i,j);
                }
                else{
                    map.putEmpty(i,j);
                }
            }
        }
        System.out.println(map);
        MapConnector connector = new MapConnector(map);
        connector.connectAllComponents();
        System.out.println("***");
        System.out.println(map);
    }

    private void nextMapStep() {
        GameMap newMap = new GameMap(map.getWidth(),map.getHeight());

        for(int i=0;i<map.getHeight();i++) {
            for (int j = 0; j < map.getWidth(); j++) {
                int totalWeightSum = 0, wallsWeightSum = 0;
                for(WeightedPoint point : neighbours) {
                    if(i+point.x >= 0 && i + point.x < map.getHeight() && j+point.y >= 0 && j+point.y < map.getWidth()) {
                        totalWeightSum += point.weight;
                        if(map.isWall(i+point.x,j+point.y)) {
                            wallsWeightSum += point.weight;
                        }
                    }
                }
                int emptiesWeightSum = totalWeightSum - wallsWeightSum;
                if(map.isWall(i,j)) {
                    if(emptiesWeightSum*1f/totalWeightSum >= intoEmptyThreshold) {
                        newMap.putEmpty(i,j);
                    }
                    else
                        newMap.putWall(i,j);
                }
                else{
                    if(wallsWeightSum*1f/totalWeightSum >= intoWallThreshold) {
                        newMap.putWall(i,j);
                    }
                    else
                        newMap.putEmpty(i,j);
                }
            }
        }
        map = newMap;
    }


    public void saveAs(String simpleNameWithoutExt, boolean overwrite) {

        try {
            File file = new File("android/assets/maps/" + simpleNameWithoutExt + ".txt");
            if(file.createNewFile() || overwrite) {
                FileOutputStream outputStream = new FileOutputStream(file, false);
                outputStream.write(map.toString().getBytes());
                outputStream.close();
            }
            else {
                System.out.println("map with given name already exists, cancelling saveToFile\n");
                return;
            }

        }
        catch (IOException e){
            System.out.println(e);
        }
    }
    public void saveAs(String simpleNameWithoutExt){
        saveAs(simpleNameWithoutExt,false);
    }

    public static void main(String[] args) {
        ShooterMapGenerator generator = new ShooterMapGenerator();
        generator.generate(15,15).saveAs("map7",true);

    }
}

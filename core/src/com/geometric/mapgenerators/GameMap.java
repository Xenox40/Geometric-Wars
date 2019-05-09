package com.geometric.mapgenerators;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class GameMap {
    private final char wall = '#', empty = '.', person = 'P', bot = 'B';
    private int width;
    private int height;
    private char[][] map;

    public void setTo(GameMap other) {
        this.width = other.width;
        this.height = other.height;
        for(int i=0;i<height;i++) {
            for(int j=0;j<width;j++)
                map[i][j] = other.map[i][j];
        }
    }

    public static class WeightedPoint{
        public int x,y;
        public int weight;
        public WeightedPoint(int x,int y, int weight){
            this.x = x;
            this.y = y;
            this.weight = weight;
        }
    }

    public GameMap(int width, int height) {
        this.width = width;
        this.height = height;
        map = new char[height][width];
    }

    char get(int i, int j) {
        return map[i][j];
    }
    void put(int i,int j, char c) {
        map[i][j] = c;
    }

    public boolean isWall(int i, int j) {
        return get(i,j) == wall;
    }
    public boolean isEmpty(int i,int j) {
        return get(i,j) == empty;
    }



    public void putWall(int i,int j) {
        put(i,j,wall);
    }
    public void putEmpty(int i,int j) {
        put(i,j,empty);
    }
    public void putBot(int i, int j) {
        put(i,j,bot);
    }
    public void putPerson(int i, int j) {
        put(i,j,person);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isIn(int i, int j){
        return i >= 0 && j >= 0 && i < getHeight() && j < getWidth();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                builder.append(map[i][j]);
            }
            builder.append('\n');
        }
        return builder.toString();
    }

    public void saveAs(String simpleNameWithoutExt, boolean overwrite) {
        try {
            File file = new File("android/assets/maps/" + simpleNameWithoutExt + ".txt");
            if(file.createNewFile() || overwrite) {
                FileOutputStream outputStream = new FileOutputStream(file, false);
                System.out.println("pp");
                outputStream.write(this.toString().getBytes());
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


}

package com.geometric.mapgenerators;

public class GameMap {
    private final char wall = '#', empty = '.', person = 'P', bot = 'B';
    private int width;
    private int height;
    private char[][] map;

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


}

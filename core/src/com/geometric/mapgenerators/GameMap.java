package com.geometric.mapgenerators;

public class GameMap {
    private final char wall = '#', empty = '.';
    private int width;
    private int height;
    private char[][] map;

    public GameMap(int width, int height) {
        this.width = width;
        this.height = height;
        map = new char[height][width];
    }

    public boolean isWall(int i, int j) {
        return map[i][j] == wall;
    }
    public boolean isEmpty(int i,int j) {
        return map[i][j] == empty;
    }

    public void putWall(int i,int j) {
        map[i][j] = wall;
    }
    public void putEmpty(int i,int j) {
        map[i][j] = empty;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
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

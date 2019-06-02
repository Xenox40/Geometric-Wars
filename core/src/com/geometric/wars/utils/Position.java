package com.geometric.wars.utils;


public class Position {
    public int x,y;
    public Position(int x,int y) {
        this.x = x;
        this.y = y;
    }

    public int getManhattanDistance(Position other) {
        return Math.abs(x-other.x) + Math.abs(y-other.y);
    }

    public Direction2D getDirection(Position other) {
        if(other.x > x)
            return Direction2D.RIGHT;
        if(other.y > y)
            return Direction2D.DOWN;
        if(other.x < x)
            return Direction2D.LEFT;
        return Direction2D.UP;
    }
}

package com.geometric.wars;

public enum Direction3D {
    UP,
    RIGHT,
    DOWN,
    LEFT,
    TOP,
    BOTTOM;
    public Direction2D toDirection2D() {
        if(this == UP)
            return Direction2D.UP;
        if(this == RIGHT)
            return Direction2D.RIGHT;
        if(this == DOWN)
            return Direction2D.DOWN;
        if(this == LEFT)
            return Direction2D.LEFT;
        throw new DirectionConversionException();
    }
}

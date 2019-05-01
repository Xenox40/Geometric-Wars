package com.geometric.wars;

import com.badlogic.gdx.math.Quaternion;

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


    public Direction3D opposite() {
        if(this == UP)
            return Direction3D.DOWN;
        if(this == DOWN)
            return Direction3D.UP;
        if(this == RIGHT)
            return Direction3D.LEFT;
        if(this == LEFT)
            return Direction3D.RIGHT;
        if(this == TOP)
            return Direction3D.BOTTOM;
        if(this == BOTTOM)
            return Direction3D.TOP;
        throw new NullPointerException();
    }

}

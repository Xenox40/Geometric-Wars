package com.geometric.wars.utils;


import com.badlogic.gdx.math.Vector3;

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
        throw new RuntimeException("Direction3D.opposite is not any of enum values");
    }

    public Vector3 toVector3(){
        if(this == UP)
            return new Vector3(0,0,-1);
        if(this == DOWN)
            return new Vector3(0,0,1);
        if(this == RIGHT)
            return new Vector3(1,0,0);
        if(this == LEFT)
            return new Vector3(-1,0,0);
        if(this == TOP)
            return new Vector3(0,1,0);
        if(this == BOTTOM)
            return new Vector3(0,-1,0);
        throw new RuntimeException("Direction3D.toVector3 is not any of enum values");
    }

}

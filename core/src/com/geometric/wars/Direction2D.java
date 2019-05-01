package com.geometric.wars;

import com.badlogic.gdx.math.Vector2;

public enum Direction2D {
    UP,
    RIGHT,
    DOWN,
    LEFT;
    public Vector2 toVector2(){
        if(this == UP)
            return new Vector2(0,-1);
        else if (this == RIGHT)
            return new Vector2(1,0);
        else if(this == DOWN)
            return new Vector2(0,1);
        else
            return new Vector2(-1,0);
    }

    public Direction3D toDirection3D() {
        if(this == UP)
            return Direction3D.UP;
        if(this == RIGHT)
            return Direction3D.RIGHT;
        if(this == DOWN)
            return Direction3D.DOWN;
        if(this == LEFT)
            return Direction3D.LEFT;
        throw new DirectionConversionException();
    }
}


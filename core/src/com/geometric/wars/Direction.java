package com.geometric.wars;

import com.badlogic.gdx.math.Vector2;

public enum Direction {
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
}

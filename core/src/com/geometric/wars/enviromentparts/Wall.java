package com.geometric.wars.enviromentparts;

import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;
import com.geometric.wars.Values;

public class Wall extends ModelInstance {

    public Wall(int x, int y) {
        super(WallModel.getModel(), new Vector3(x * Values.unit,0, y * Values.unit));
    }

}

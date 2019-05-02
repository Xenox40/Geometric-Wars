package com.geometric.wars.cube;

import com.geometric.wars.Direction2D;

public class CubeFace {
    private Mountable mountedObject;
    private int direction;
    private DynamicCubeView view;
    public void setMountedObject(Mountable mountable) {
        this.mountedObject = mountable;
        view.setTexture(direction, mountable.getView().getTexture());
    }
    CubeFace(DynamicCubeView view, int i) {
        this.direction = i;
        this.view = view;
    }
}

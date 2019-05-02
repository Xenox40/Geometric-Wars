package com.geometric.wars.cube;

import com.geometric.wars.Direction2D;

public class CubeFace {
    private Mountable mountedObject;
    private CubeFaceView view;
    private int direction;
    public void setMountedObject(Mountable mountable) {
        this.mountedObject = mountable;
    }

    public CubeFaceView getView() {
        return view;
    }

    public CubeFace(DynamicCubeView view, int i) {
        this.direction = i;
    }
}

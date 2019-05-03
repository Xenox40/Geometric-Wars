package com.geometric.wars.cube;


public class CubeFace {
    private Mountable mountedObject;

    public void setMountedObject(Mountable mountable) {
        this.mountedObject = mountable;
    }
    public Mountable getMountedObject() {
        return mountedObject;
    }

    public CubeFace() {
    }
}

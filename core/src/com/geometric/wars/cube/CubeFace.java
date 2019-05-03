package com.geometric.wars.cube;


import com.geometric.wars.cube.mountables.Mountable;
import com.geometric.wars.cube.mountables.MountableView;

public class CubeFace {
    private Mountable mountedObject;

    void setMountedObject(Mountable mountable) {
        this.mountedObject = mountable;
        mountable.setFaceMountedAt(this);
    }
    public Mountable getMountedObject() {
        return mountedObject;
    }
    public MountableView getMountedObjectView() {
        return getMountedObject().getView();
    }

}

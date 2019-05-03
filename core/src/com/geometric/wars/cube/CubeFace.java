package com.geometric.wars.cube;


import com.geometric.wars.cube.mountables.Mountable;
import com.geometric.wars.cube.mountables.MountableView;

class CubeFace {
    private com.geometric.wars.cube.mountables.Mountable mountedObject;

    void setMountedObject(com.geometric.wars.cube.mountables.Mountable mountable) {
        this.mountedObject = mountable;
    }
    Mountable getMountedObject() {
        return mountedObject;
    }
    public MountableView getMountedObjectView() {
        return getMountedObject().getView();
    }

}

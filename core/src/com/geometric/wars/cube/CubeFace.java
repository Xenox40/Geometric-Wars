package com.geometric.wars.cube;


class CubeFace {
    private Mountable mountedObject;

    void setMountedObject(Mountable mountable) {
        this.mountedObject = mountable;
    }
    Mountable getMountedObject() {
        return mountedObject;
    }
    public MountableView getMountedObjectView() {
        return getMountedObject().getView();
    }

}

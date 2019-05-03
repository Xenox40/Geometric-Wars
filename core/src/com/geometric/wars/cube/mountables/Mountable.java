package com.geometric.wars.cube.mountables;


import com.geometric.wars.cube.CubeFace;

public abstract class Mountable {
    private CubeFace faceMountedAt;
    protected MountableView view;

    public final MountableView getView() {
        return view;
    }
    public final CubeFace getFaceMountedAt() {
        return faceMountedAt;
    }
    public final void setFaceMountedAt(CubeFace face) {
        this.faceMountedAt = face;
    }
}


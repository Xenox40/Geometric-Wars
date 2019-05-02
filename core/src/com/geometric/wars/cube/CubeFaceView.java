package com.geometric.wars.cube;

import com.badlogic.gdx.graphics.g3d.model.Node;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;


public class CubeFaceView {
    private MountableView view;
    public Node getNode() {
        return view.getNode();
    }

    public MountableView getView() {
        return view;
    }

    public void buildMeshPart(ModelBuilder modelBuilder) {
        view.buildMeshPart(modelBuilder);
    }

    public void setMountedObject(MountableView mountableView) {
        view = mountableView;
    }
}

package com.geometric.wars.cube.mountables;

import com.badlogic.gdx.graphics.g3d.model.Node;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;

public abstract class MountableView {
    protected Node node;
    protected final Vector3 size;

    public MountableView(Vector3 size) {
        this.size = size;
    }

    public final void createNode(ModelBuilder modelBuilder) {
        node = modelBuilder.node();
    }

    public final Node getNode() {
        return node;
    }

    public Vector3 getSize() {
        return size;
    }

    public abstract void buildMeshPart(ModelBuilder modelBuilder);

}


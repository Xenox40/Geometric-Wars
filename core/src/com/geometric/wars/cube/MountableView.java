package com.geometric.wars.cube;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.model.Node;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.geometric.wars.Direction3D;

public interface MountableView {
    Material getMaterial();

    Node getNode();

    /**
     * should contain rules to build part of model
     **/
    void buildMeshPart(ModelBuilder modelBuilder);
}


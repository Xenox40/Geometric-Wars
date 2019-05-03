package com.geometric.wars.cube;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.model.Node;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;
import com.geometric.wars.models.BoxBuilder;

public class SimpleGun implements Mountable {
    private MountableView view = new MountableView() {
        private final Vector3 size = new Vector3(0.4f,0.2f,0.2f);
        private Node node;
        @Override
        public Material getMaterial() {
            return null;
        }

        @Override
        public Node getNode() {
            return node;
        }

        @Override
        public void buildMeshPart(ModelBuilder modelBuilder) {
            node = modelBuilder.node();
            node = BoxBuilder.addColoredBoxNode(modelBuilder,"gun1", Color.PINK,size.x,size.y,size.z);
        }

        @Override
        public Vector3 getSize() {
            return size;
        }


    };
    public MountableView getView() {
        return view;
    }

}

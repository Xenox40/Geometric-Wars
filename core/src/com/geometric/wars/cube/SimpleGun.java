package com.geometric.wars.cube;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.model.Node;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.geometric.wars.models.BoxBuilder;

public class SimpleGun implements Mountable {
    private MountableView view = new MountableView() {
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
            node = BoxBuilder.addColoredBoxNode(modelBuilder,"gun1", Color.WHITE,0.4f,0.2f,0.2f);
        }


    };
    public MountableView getView() {
        return view;
    }

}

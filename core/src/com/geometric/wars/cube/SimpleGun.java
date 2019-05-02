package com.geometric.wars.cube;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.model.Node;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.geometric.wars.Direction3D;
import com.geometric.wars.models.BoxBuilder;
import com.geometric.wars.models.DynamicCubeModel;

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

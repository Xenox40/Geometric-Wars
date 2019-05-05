package com.geometric.wars.cube.mountables.guns;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;
import com.geometric.wars.cube.mountables.MountableGun;
import com.geometric.wars.cube.mountables.MountableView;
import com.geometric.wars.models.BoxBuilder;



public class SimpleGun extends MountableGun {
    public SimpleGun() {
        this.view = new MountableView(new Vector3(0.4f, 0.2f, 0.2f)) {
            @Override
            public void buildMeshPart(ModelBuilder modelBuilder) {
                super.createNode(modelBuilder);
                node = BoxBuilder.addColoredBoxNode(modelBuilder, "gun1", Color.PINK, size.x, size.y, size.z);
            }
        };
    }

    @Override
    public int getDamage() {
        return 0;
    }

    @Override
    public int getWaitingTime() {
        return 0;
    }
}

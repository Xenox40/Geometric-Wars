package com.geometric.wars.cube.mountables.guns;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;
import com.geometric.wars.cube.mountables.MountableGun;
import com.geometric.wars.cube.mountables.MountableView;
import com.geometric.wars.models.BoxBuilder;



public class SimpleGun extends MountableGun {
    public SimpleGun() {
        this.view = new MountableView(new Vector3(0.3f, 0.2f, 0.2f)) {
            @Override
            public void buildMeshPart(ModelBuilder modelBuilder) {
                super.createNode(modelBuilder);
                node = BoxBuilder.addColoredBoxNode(modelBuilder, "gun1", Color.CORAL, size.x, size.y, size.z);
            }
        };
    }

    @Override
    public int getDamage() {
        return 5;
    }

    @Override
    public int getWaitingTimeInMillis() {
        return 250;
    }

    @Override
    public float getBulletSpeed() {
        return 20f;
    }

    @Override
    public float getOverheatAbsoluteGrowth() { return 0.06f; }

    @Override
    public float getOverheatRelativeGrowth() { return 0.20f; }
}

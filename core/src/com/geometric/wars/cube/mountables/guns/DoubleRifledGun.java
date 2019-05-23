package com.geometric.wars.cube.mountables.guns;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.model.Node;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;
import com.geometric.wars.cube.mountables.MountableGun;
import com.geometric.wars.cube.mountables.MountableView;
import com.geometric.wars.models.BoxBuilder;
import com.geometric.wars.utils.GameResourceDisposer;

public class DoubleRifledGun extends MountableGun {
    private static final Vector3 rifleSize = new Vector3(0.45f,0.1f,0.1f);
    private static final float spaceBetweenRifles = 0.05f;



    public DoubleRifledGun() {
        this.view = new MountableView(new Vector3(rifleSize.x, rifleSize.y, 2*rifleSize.z+spaceBetweenRifles)) {

            private TextureRegion getTextureRegion() {
                final String name = "rifleTexture";
                if (!GameResourceDisposer.contains(name)){
                    GameResourceDisposer.addResource(name, new Texture(Gdx.files.internal("rifle.png")));
                }
                return new TextureRegion((Texture)GameResourceDisposer.getResource(name));
            }

            @Override
            public void buildMeshPart(ModelBuilder modelBuilder) {
                super.createNode(modelBuilder);
                node = modelBuilder.node();
                Node rifle1 = BoxBuilder.addTexturedBoxNode(modelBuilder, "rifle1", getTextureRegion(), rifleSize.x, rifleSize.y, rifleSize.z);
                Node rifle2 = BoxBuilder.addTexturedBoxNode(modelBuilder, "rifle2", getTextureRegion(), rifleSize.x, rifleSize.y, rifleSize.z);
                rifle1.translation.add(0f,0f,-(rifleSize.z+spaceBetweenRifles)/2);
                rifle2.translation.add(0f,-0f,(rifleSize.z+spaceBetweenRifles)/2);
                node.addChild(rifle1);
                node.addChild(rifle2);

            }
        };
    }

    @Override
    public int getDamage() {
        return 10;
    }

    @Override
    public int getWaitingTimeInMillis() {
        return 400;
    }

}

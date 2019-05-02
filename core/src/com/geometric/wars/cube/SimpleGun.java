package com.geometric.wars.cube;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.geometric.wars.models.DynamicCubeModel;

public class SimpleGun implements Mountable {
    private MountableView view = new MountableView() {
        @Override
        public TextureRegion getTexture() {
            return DynamicCubeModel.getTextureRegion("gun1");
        }
    };
    public MountableView getView() {
        return view;
    }

}

package com.geometric.wars.cube;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.geometric.wars.Direction2D;
import com.geometric.wars.Values;
import com.geometric.wars.models.DynamicCubeModel;


public class DynamicCubeView extends ModelInstance {

    private static final float size = Values.unit;

    public DynamicCubeView() {
        super(DynamicCubeModel.getModel());
    }

    public void setColor(Color color) {
        for(Material material : this.materials) {
            material.set(ColorAttribute.createDiffuse(color));
        }
    }

    public void setTexture(int face, TextureRegion region) {
        materials.get(face).set(TextureAttribute.createDiffuse(region));
    }

    public void rotate(Direction2D direction2D, float degrees, int px, int pz) {
       this.transform = RotationCalculator.transformAfterRoll(this.transform, direction2D, degrees, size, px,pz);
    }

}

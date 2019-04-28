package com.geometric.wars.debug.axises;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.geometric.wars.models.LineModel;

class CoordinateAxis extends ModelInstance {
    public CoordinateAxis() {
        super(LineModel.getModel());
        transform.setToScaling(10,10,10);
    }
    public void setColor(Color color){
        materials.get(0).set(ColorAttribute.createDiffuse(color));
    }
}

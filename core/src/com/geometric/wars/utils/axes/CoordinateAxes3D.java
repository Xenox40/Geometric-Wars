package com.geometric.wars.utils.axes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.math.Vector3;

public class CoordinateAxes3D {
    private CoordinateAxis axisX, axisY, axisZ;
    public CoordinateAxes3D() {
        axisX = new CoordinateAxis();
        axisY = new CoordinateAxis();
        axisZ = new CoordinateAxis();

        axisX.setColor(Color.RED);
        axisY.setColor(Color.GREEN);
        axisZ.setColor(Color.BLUE);

        axisY.transform.rotate(Vector3.Z,90);
        axisZ.transform.rotate(Vector3.Y,270);
    }


    /**
     * Renders axises on screen.
     *
     * @param batch ModelBatch using to render.
     * @param environment Environment
     */
    public void render(ModelBatch batch, Environment environment) {
        batch.render(axisX,environment);
        batch.render(axisY,environment);
        batch.render(axisZ,environment);
    }
}

package com.geometric.wars.models;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.geometric.wars.Direction3D;
import com.geometric.wars.cube.*;

import java.util.ArrayList;
import java.util.HashMap;

public class DynamicCubeModelBuilder {
    /**
     * treated as map via Direction3D.ordinal()
     */
    private Array<CubeFaceView> faces;
    private ModelBuilder builder;

    public DynamicCubeModelBuilder() {
        faces = new Array<>();
        for(int i=0;i<6;i++)
            faces.add(new CubeFaceView());
    }
    public DynamicCubeModelBuilder createCube() {
        builder = new ModelBuilder();
        return this;
    }

    public DynamicCubeModelBuilder addMountable(Direction3D direction, MountableView mountable) {
        faces.get(direction.ordinal()).setMountedObject(mountable);
        return this;
    }
    public String build(String name) {
        if(DynamicCubeModel.getModel(name) != null)
            return name;
        builder.begin();
        BoxBuilder.addColoredBoxNode(builder,"core", Color.WHITE,1,1,1);
        for(int i=0;i<faces.size;i++) {
            if(faces.get(i).getView() == null)
                continue;
            faces.get(i).buildMeshPart(builder);

            Matrix4 transform = new Matrix4();

           Direction3D faceDirection = Direction3D.values()[i];
            if(faceDirection == Direction3D.UP)
                transform.setToRotation(Vector3.Y,90);
            if(faceDirection == Direction3D.LEFT)
                transform.setToRotation(Vector3.Y,180);
            if(faceDirection == Direction3D.DOWN)
                transform.setToRotation(Vector3.Y,270);
            if(faceDirection == Direction3D.TOP)
                transform.setToRotation(Vector3.Z,90);
            if(faceDirection == Direction3D.BOTTOM)
                transform.setToRotation(Vector3.Z,270);

            transform.translate(0.7f,0,0);
            faces.get(i).getNode().translation.set(transform.getTranslation(new Vector3()));
            faces.get(i).getNode().rotation.set(transform.getRotation(new Quaternion()));
            //faces.get(i).getNode().translation.set(2f,0,0);
        }
        DynamicCubeModel.addModel(name,builder.end());
        return name;
    }
}

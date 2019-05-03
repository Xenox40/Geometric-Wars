package com.geometric.wars.cube;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.geometric.wars.Direction3D;
import com.geometric.wars.models.BoxBuilder;
import com.geometric.wars.models.DynamicCubeModelDisposer;


public class DynamicCubeBuilder {
    /**
     * treated as map via Direction3D.ordinal()
     */
    private DynamicCube cube;
    private ModelBuilder builder;

    public DynamicCubeBuilder() {
        cube = new DynamicCube();
    }


    public DynamicCubeBuilder createCube() {
        builder = new ModelBuilder();
        return this;
    }

    public DynamicCubeBuilder addMountable(Direction3D direction, Mountable mountable) {
        cube.faces.get(direction.ordinal()).setMountedObject(mountable);
        return this;
    }


    public DynamicCube build() {
        builder.begin();
        BoxBuilder.addColoredBoxNode(builder,"core", Color.WHITE,1,1,1);
        for(int i=0;i<cube.faces.size;i++) {

            CubeFace face = cube.faces.get(i);
            Direction3D faceDirection = Direction3D.values()[i];

            if(face.getMountedObject() == null)
                continue;

            face.getMountedObject().getView().buildMeshPart(builder);

            Matrix4 transform = new Matrix4();


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
            face.getMountedObject().getView().getNode().translation.set(transform.getTranslation(new Vector3()));
            face.getMountedObject().getView().getNode().rotation.set(transform.getRotation(new Quaternion()));
            //faces.get(i).getNode().translation.set(2f,0,0);
        }
        Model model = builder.end();
        DynamicCubeModelDisposer.addModel(model);
        cube.dynamicCubeView = new DynamicCubeView(model);
        return cube;
    }
}

package com.geometric.wars.cube;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.geometric.wars.cube.mountables.Mountable;
import com.geometric.wars.models.BoxBuilder;
import com.geometric.wars.utils.GameResourceDisposer;
import com.geometric.wars.utils.Direction3D;
import com.geometric.wars.utils.Values;


public class DynamicCubeBuilder {
    private DynamicCube cube;
    private Color coreColor;
    private ModelBuilder builder;
    private static final float cubeSize = Values.unit;

    public DynamicCubeBuilder(DynamicCube cube) {
        this.cube = cube;
    }


    public DynamicCubeBuilder createCube(Color color) {
        builder = new ModelBuilder();
        coreColor = color;
        return this;
    }


    public DynamicCubeBuilder addMountable(Direction3D direction, Mountable mountable) {
        cube.faces.get(direction.ordinal()).setMountedObject(mountable);
        return this;
    }


    public DynamicCube build() {
        builder.begin();
        BoxBuilder.addColoredBoxNode(builder,"core", coreColor,cubeSize,cubeSize,cubeSize);
        for(int i=0;i<cube.faces.size;i++) {
            CubeFace face = cube.faces.get(i);
            Direction3D faceDirection = Direction3D.values()[i];
            buildFace(face,faceDirection);
        }

        Model model = builder.end();
        GameResourceDisposer.addResource(model);
        cube.color = coreColor;
        cube.dynamicCubeView = new DynamicCubeView(model);
        return cube;
    }

    private void buildFace(CubeFace face, Direction3D faceDirection) {
        if(face.getMountedObject() == null)
            return;

        face.getMountedObjectView().buildMeshPart(builder);

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

        transform.translate((cubeSize+face.getMountedObjectView().getSize().x)/2f,0,0);
        face.getMountedObjectView().getNode().translation.set(transform.getTranslation(new Vector3()));
        face.getMountedObjectView().getNode().rotation.set(transform.getRotation(new Quaternion()));
    }
}

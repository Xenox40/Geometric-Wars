package com.geometric.wars;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;


public class CubeView {
    Model model;
    ModelInstance cubeModelInstance;
    private final float size = 1;
    private KeyInputListener arrowAction;
    CubeView(){
        ModelBuilder modelBuilder = new ModelBuilder();
        model = modelBuilder.createBox(size, size,size,
                new Material(ColorAttribute.createDiffuse(Color.RED)),
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
        cubeModelInstance = new ModelInstance(model);
    }

    public void rotate(Vector3 md, Vector3 mr, float degrees){

        Vector3 translationVector = new Vector3(-size / 2 + md.x, -size / 2 + md.y, -size / 2 + md.z);

        Quaternion curRotation = cubeModelInstance.transform.getRotation(new Quaternion());
        Quaternion invRotation = new Quaternion(-curRotation.x / curRotation.len2(), -curRotation.y / curRotation.len2(),
                -curRotation.z / curRotation.len2(),  curRotation.w / curRotation.len2());

        cubeModelInstance.transform
                .rotate(invRotation)
                .translate(translationVector.cpy().scl(1))
                .rotate(mr, degrees)
                .translate(translationVector.cpy().scl(-1))
                .rotate(curRotation);
    }

    void processKeyInput() {
        if(Gdx.input.isKeyPressed(Input.Keys.UP))
            arrowAction.doAction(Input.Keys.UP);
        else if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
            arrowAction.doAction(Input.Keys.RIGHT);
        }
        else if(Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            arrowAction.doAction(Input.Keys.DOWN);
        }
        else if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
            arrowAction.doAction(Input.Keys.LEFT);
        }
    }


    public void setArrowAction(KeyInputListener arrowAction) {
        this.arrowAction = arrowAction;
    }
}

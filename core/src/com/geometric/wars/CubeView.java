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
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;


public class CubeView {
    final Model model;
    final ModelInstance cubeModelInstance;
    private static final float size = Values.unit;
    private KeyInputListener arrowAction;
    CubeView(){
        ModelBuilder modelBuilder = new ModelBuilder();
        model = modelBuilder.createBox(size, size,size,
                new Material(ColorAttribute.createDiffuse(Color.RED)),
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
        cubeModelInstance = new ModelInstance(model);
    }


    private void rotate(Vector3 translation, Vector3 rotation, float degrees, Vector3 pp) {

        Vector3 curPosition  = cubeModelInstance.transform.getTranslation(new Vector3());

        Quaternion curRotation = cubeModelInstance.transform.getRotation(new Quaternion()).nor();


        Quaternion invRotation = curRotation.cpy().conjugate();

        cubeModelInstance.transform
                .rotate(invRotation)
                .translate(curPosition.cpy().sub(pp).scl(-1))
                .translate(translation.cpy().scl(1))
                .rotate(rotation, degrees)
                .translate(translation.cpy().scl(-1))
                .translate(curPosition.cpy().sub(pp).scl(1))
                .rotate(curRotation)
            ;
    }


    public void rotateRight(float degrees,Vector3 pp) {
        rotate(new Vector3(size/2,-size/2,0), new Vector3(0,0,-1),degrees,pp);
    }

    public void rotateLeft(float degrees,Vector3 pp) {
        rotate(new Vector3(-size/2,-size/2,0), new Vector3(0,0,1),degrees,pp);
    }

    public void rotateUp(float degrees,Vector3 pp) {
        rotate(new Vector3(0,-size/2,-size/2), new Vector3(-1,0,0),degrees,pp);
    }

    public void rotateDown(float degrees,Vector3 pp) {
        rotate(new Vector3(0,-size/2,size/2), new Vector3(1,0,0),degrees,pp);
    }

    public void rotate(int direction,float degrees,int px, int pz) {
        if(direction == 0)
            rotateUp(degrees,new Vector3(px,0,pz));
        else if(direction == 1)
            rotateRight(degrees,new Vector3(px,0,pz));
        else if(direction == 2)
            rotateDown(degrees,new Vector3(px,0,pz));
        else if(direction == 3)
            rotateLeft(degrees,new Vector3(px,0,pz));
    }


    void processKeyInput() {
        if(Gdx.input.isKeyPressed(Input.Keys.UP))
            arrowAction.doAction(Input.Keys.UP);
        else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            arrowAction.doAction(Input.Keys.RIGHT);
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            arrowAction.doAction(Input.Keys.DOWN);
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            arrowAction.doAction(Input.Keys.LEFT);
        }
    }


    public void setArrowAction(KeyInputListener arrowAction) {
        this.arrowAction = arrowAction;
    }
}

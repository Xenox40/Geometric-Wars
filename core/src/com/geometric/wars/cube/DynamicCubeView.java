package com.geometric.wars.cube;


import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.geometric.wars.Direction2D;
import com.geometric.wars.Values;
import com.geometric.wars.models.CubeModel;


public class DynamicCubeView extends ModelInstance {

    private static final float size = Values.unit;

    public DynamicCubeView() {
        super(CubeModel.getModel());
    }


    private void rotate(Vector3 translation, Vector3 rotation, float degrees, Vector3 pp) {

        Vector3 curPosition  = transform.getTranslation(new Vector3());
        Quaternion curRotation = transform.getRotation(new Quaternion()).nor();
        Quaternion invRotation = curRotation.cpy().conjugate();

        transform.rotate(invRotation)
                .translate(curPosition.cpy().sub(pp).scl(-1))
                .translate(translation.cpy().scl(1))
                .rotate(rotation, degrees)
                .translate(translation.cpy().scl(-1))
                .translate(curPosition.cpy().sub(pp).scl(1))
                .rotate(curRotation);
    }

    public void rotate(Direction2D direction2D, float degrees, int px, int pz) {
        if(direction2D == Direction2D.UP)
            rotateUp(degrees,new Vector3(px,0,pz));
        else if(direction2D == Direction2D.RIGHT)
            rotateRight(degrees,new Vector3(px,0,pz));
        else if(direction2D == Direction2D.DOWN)
            rotateDown(degrees,new Vector3(px,0,pz));
        else if(direction2D == Direction2D.LEFT)
            rotateLeft(degrees,new Vector3(px,0,pz));
    }


    private void rotateRight(float degrees,Vector3 pp) {
        rotate(new Vector3(size/2,-size/2,0), new Vector3(0,0,-1),degrees,pp);
    }

    private void rotateLeft(float degrees,Vector3 pp) {
        rotate(new Vector3(-size/2,-size/2,0), new Vector3(0,0,1),degrees,pp);
    }

    private void rotateUp(float degrees,Vector3 pp) {
        rotate(new Vector3(0,-size/2,-size/2), new Vector3(-1,0,0),degrees,pp);
    }

    private void rotateDown(float degrees,Vector3 pp) {
        rotate(new Vector3(0,-size/2,size/2), new Vector3(1,0,0),degrees,pp);
    }

}

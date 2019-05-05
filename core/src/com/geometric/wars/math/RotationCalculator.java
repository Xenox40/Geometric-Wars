package com.geometric.wars.math;

import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.geometric.wars.utils.Direction2D;
import com.geometric.wars.utils.Direction3D;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * only static methods
 */
public class RotationCalculator {
    private RotationCalculator() {}

    private static HashMap<ArrayList<Integer>, Direction3D> quaternionToRotation = new HashMap<>();
    static {
        fillQuaternionToRotation();
    }


    public static Direction3D getFaceAt(Direction3D direction, Quaternion rotation) {
        Quaternion rotation2 = rotation.cpy();
        if(direction == Direction3D.RIGHT)
            rotation2 = orientationAfterRoll(rotation,Direction2D.LEFT,90);
        if(direction == Direction3D.LEFT)
            rotation2 = orientationAfterRoll(rotation,Direction2D.RIGHT,90);
        if(direction == Direction3D.UP)
            rotation2 = orientationAfterRoll(rotation,Direction2D.DOWN,90);
        if(direction == Direction3D.DOWN)
            rotation2 = orientationAfterRoll(rotation,Direction2D.UP,90);

        if(direction == Direction3D.BOTTOM)
            rotation2 = orientationAfterRoll(rotation,Direction2D.DOWN,180);
        if(!quaternionToRotation.containsKey(quaternionToArrayInt(rotation2))) {
            throw new ArithmeticException("quaternion precision exception for Rotation Calculator");
        }
        return quaternionToRotation.get(quaternionToArrayInt(rotation2));
    }

    public static Quaternion orientationAfterRoll(Quaternion rotation, Direction2D direction2D, float degrees) {
        return transformAfterRoll(new Matrix4(rotation),direction2D,degrees,0,0,0).getRotation(new Quaternion());
    }

    /**
     *one can also use size=px=pz=0 to calculate orientation only
     *
     * @param transform object transform
     * @param direction2D direction of roll in world's coordinate system
     * @param degrees rotation's degrees
     * @param size length of cube's size
     * @param px x position before started moving
     * @param pz z position before started moving
     * @return calculated object's transform
     *
     */

    public static Matrix4 transformAfterRoll(Matrix4 transform, Direction2D direction2D, float degrees, float size, int px, int pz) {
        if(direction2D == Direction2D.UP)
            return transformAfterRollUp(transform, degrees,size, new Vector3(px,0,pz));
        else if(direction2D == Direction2D.RIGHT)
            return transformAfterRollRight(transform, degrees, size, new Vector3(px,0,pz));
        else if(direction2D == Direction2D.DOWN)
            return transformAfterRollDown(transform, degrees, size, new Vector3(px,0,pz));
        else if(direction2D == Direction2D.LEFT)
            return transformAfterRollLeft(transform, degrees, size, new Vector3(px,0,pz));
        throw new NullPointerException("Bad direction2d inside RotationCalculator.rotate()");
    }



    public static ArrayList<Integer> quaternionToArrayInt(Quaternion q) {
        return new ArrayList<>(Arrays.asList(Math.round(10*q.x),Math.round(10*q.y),Math.round(10*q.z),Math.round(10*q.w)));
    }

    private static void addRotation(Quaternion q,Direction3D dir) {
        quaternionToRotation.put(quaternionToArrayInt(q),dir);
        quaternionToRotation.put(quaternionToArrayInt(q.cpy().mul(-1)),dir);
    }

    private static void fillQuaternionToRotation() {
        Matrix4 matrix = new Matrix4();
        matrix.setToRotation(0, 0, 0, 0);
        for (int i = 0; i < 4; i++) {
            addRotation(matrix.getRotation(new Quaternion()), Direction3D.TOP);
            matrix.rotate(0, 1, 0, 90f);

        }
        matrix.rotate(1, 0, 0, 90);
        for (int i = 0; i < 4; i++) {
            addRotation(matrix.getRotation(new Quaternion()), Direction3D.UP);
            matrix.rotate(0, 0, 1, 90f);
        }
        matrix.rotate(1, 0, 0, 90);
        for (int i = 0; i < 4; i++) {
            addRotation(matrix.getRotation(new Quaternion()), Direction3D.BOTTOM);
            matrix.rotate(0, 1, 0, 90f);
        }
        matrix.rotate(1, 0, 0, 90);
        for (int i = 0; i < 4; i++) {
            addRotation(matrix.getRotation(new Quaternion()), Direction3D.DOWN);
            matrix.rotate(0, 0, 1, 90f);
        }


        matrix.setToRotation(0, 0, 0, 0);
        matrix.rotate(0,0,1,90);

        for (int i = 0; i < 4; i++) {
            addRotation(matrix.getRotation(new Quaternion()), Direction3D.RIGHT);
            matrix.rotate(1,0 , 0, 90f);
        }

        matrix.setToRotation(0, 0, 0, 0);
        matrix.rotate(0,0,1,-90);

        for (int i = 0; i < 4; i++) {
            addRotation(matrix.getRotation(new Quaternion()), Direction3D.LEFT);
            matrix.rotate(1, 0, 0, 90f);
        }
    }

    private static Matrix4 transformAfterRollRight(Matrix4 transform, float degrees, float size, Vector3 pp) {
        return transformAfterRoll(transform, new Vector3(size/2,-size/2,0), new Vector3(0,0,-1),degrees,pp);
    }

    private static Matrix4 transformAfterRollLeft(Matrix4 transform, float degrees, float size, Vector3 pp) {
        return transformAfterRoll(transform, new Vector3(-size/2,-size/2,0), new Vector3(0,0,1),degrees,pp);
    }

    private static Matrix4 transformAfterRollUp(Matrix4 transform, float degrees,float size, Vector3 pp) {
        return transformAfterRoll(transform, new Vector3(0,-size/2,-size/2), new Vector3(-1,0,0),degrees,pp);
    }

    private static Matrix4 transformAfterRollDown(Matrix4 transform, float degrees, float size, Vector3 pp) {
        return transformAfterRoll(transform, new Vector3(0,-size/2,size/2), new Vector3(1,0,0),degrees,pp);
    }

    private static Matrix4 transformAfterRoll(Matrix4 transform, Vector3 translation, Vector3 rotation, float degrees, Vector3 pp) {

        Vector3 curPosition  = transform.getTranslation(new Vector3());
        Quaternion curRotation = transform.getRotation(new Quaternion()).nor();
        Quaternion invRotation = curRotation.cpy().conjugate();

        return transform.rotate(invRotation)
                .translate(curPosition.cpy().sub(pp).scl(-1))
                .translate(translation.cpy().scl(1))
                .rotate(rotation, degrees)
                .translate(translation.cpy().scl(-1))
                .translate(curPosition.cpy().sub(pp).scl(1))
                .rotate(curRotation);
    }

}

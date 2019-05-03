package com.geometric.wars.cube;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.geometric.wars.Direction2D;
import com.geometric.wars.Direction3D;
import com.geometric.wars.scene.SceneManager;
import com.geometric.wars.math.RotationCalculator;

/**
 * should be built only with DynamicCubeBuilder, manages its view
 */
public class DynamicCube {

    DynamicCube() {
        faces = new Array<>(6);
        for(int i=0;i<6;i++) {
            faces.add(new CubeFace());
        }
    }

    static final float movementTimeInSeconds = 0.25f;

    private float rotationAngleSumInDegrees;
    private boolean moving = false;
    private Direction2D rotationDirection2D;

    private int bx = 0, bz = 0;
    private int ax = 0, az = 0;

    DynamicCubeView dynamicCubeView;

    public DynamicCubeView getView() {
        return dynamicCubeView;
    }

    Array<CubeFace> faces;
    private Quaternion rotationBeforeMove = new Quaternion();
    private Quaternion rotationAfterMove = new Quaternion();



    public CubeFace getFaceAt(Direction3D direction) {
        return faces.get(RotationCalculator.getFaceAt(direction,rotationAfterMove).ordinal());
    }

    public Direction3D getFaceOrientation(CubeFace face) {
        for(int i=0;i<6;i++)
            if(getFaceAt(Direction3D.values()[i]) == face)
                return Direction3D.values()[i];
        throw new RuntimeException("face not found");
    }



    public boolean isMoving() {
        return moving;
    }


    /**
     * sets position (teleports) dynamicCube to (x,z)
     * warning: it cancells rotating and resets orientation to default
     */
    public void setPosition(int x, int z) {
        finishRotating();
        this.ax = this.bx = x;
        this.az = this.bz = z;
        dynamicCubeView.transform.setToTranslation(x,0,z);
    }

    /**
     * @return position where dynamicCube was before it started moving
     */
    public Vector2 getPosition() {
        return new Vector2(bx,bz);
    }

    /**
     * @return position where dynamicCube will be after it finishes moving
     */
    public Vector2 getApproachingPosition() {
        return new Vector2(ax,az);
    }


    public void updateRotationAndPosition() {
        if(isMoving()) {
            float deltaDegrees = (float)(Gdx.graphics.getDeltaTime()*90/(double)(movementTimeInSeconds));
            if(rotationAngleSumInDegrees+deltaDegrees >= 90){
                deltaDegrees = 90-rotationAngleSumInDegrees;
                moving = false;
            }

            rotationAngleSumInDegrees += deltaDegrees;

            dynamicCubeView.rotate(rotationDirection2D, deltaDegrees,bx,bz);
            if(!moving) {
                finishRotating();
            }
        }
    }

    public boolean moveIsAllowed(Direction2D direction2D) {
        Vector2 nextPosition = direction2D.toVector2();
        return !(SceneManager.getInstance().getCurrentMapService().checkIfIsOccupied(bx + (int)nextPosition.x, bz + (int)nextPosition.y));
    }

    public void move(Direction2D direction2D) {
        if(isMoving() || !moveIsAllowed(direction2D))
            return;

        startRotating();
        rotationDirection2D = direction2D;
        rotationAfterMove = RotationCalculator.transformAfterRoll(new Matrix4(rotationBeforeMove),direction2D,90,0,0,0).getRotation(new Quaternion()).nor();

        if(direction2D == Direction2D.UP) {
            az--;
        }
        if(direction2D == Direction2D.RIGHT)
            ax++;
        if(direction2D == Direction2D.DOWN)
            az++;
        if(direction2D == Direction2D.LEFT)
            ax--;
    }

    private void startRotating() {
        moving = true;
        rotationAngleSumInDegrees = 0;
    }

    private void finishRotating() {
        moving = false;
        rotationAngleSumInDegrees = 0;
        rotationDirection2D = null;
        bx = ax;
        bz = az;
        rotationBeforeMove = rotationAfterMove;
    }

}

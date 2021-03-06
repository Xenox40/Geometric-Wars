package com.geometric.wars.cube;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.geometric.wars.math.RotationCalculator;
import com.geometric.wars.utils.Direction2D;
import com.geometric.wars.utils.Direction3D;
import com.geometric.wars.utils.Position;


public class DynamicCube {

    public Color color;

    public DynamicCube() {
        faces = new Array<>(6);
        for(int i=0;i<6;i++) {
            faces.add(new CubeFace());
        }
    }

    private float movementTimeInSeconds = 0.25f;

    protected float rotationAngleSumInDegrees;
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
     * warning: it cancels rotating and resets orientation to default
     */
    public void setPosition(int x, int z) {
        if(isMoving())
            finishRotating();
        this.ax = this.bx = x;
        this.az = this.bz = z;
        rotationBeforeMove = rotationAfterMove = new Quaternion();
        dynamicCubeView.transform.setToTranslation(x,0,z);
    }

    /**
     * @return position where dynamicCube was before it started moving
     */
    public Position getPosition() {
        return new Position(bx,bz);
    }

    /**
     * @return position where dynamicCube will be after it finishes moving
     */
    public Position getApproachingPosition() {
        return new Position(ax,az);
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

    public void move(Direction2D direction2D) {
        if(isMoving())
            return;

        startRotating();
        rotationDirection2D = direction2D;
        rotationAfterMove = RotationCalculator.transformAfterRoll(new Matrix4(rotationBeforeMove),direction2D,90,0,0,0).getRotation(new Quaternion()).nor();

        if(direction2D == Direction2D.UP)
            az--;
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

    protected void finishRotating() {
        moving = false;
        rotationAngleSumInDegrees = 0;
        rotationDirection2D = null;
        bx = ax;
        bz = az;
        rotationBeforeMove = rotationAfterMove;
    }

    public void setMovementSpeed(float movesPerSecond) {
        this.movementTimeInSeconds = movesPerSecond;
    }

}

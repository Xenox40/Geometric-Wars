package com.geometric.wars.cube;
import com.badlogic.gdx.Gdx;
import com.geometric.wars.Direction;
import com.geometric.wars.Values;

public class Cube {
    static final float movementTimeInSeconds = 0.25f;

    private final CubeView cubeView;

    private float rotationAngleSumInDegrees;
    private boolean moving = false;
    private Direction rotationDirection;

    public boolean isMoving() {
        return moving;
    }
    private void startRotating() {
        moving = true;
        rotationAngleSumInDegrees = 0;
    }

    private int bx = 0, bz = 0;
    private int ax = 0, az = 0;


    public Cube(CubeView cubeView) {
        this.cubeView = cubeView;
    }


    public void updateRotationAndPosition() {
        if(isMoving()) {
            float deltaDegrees = (float)(Gdx.graphics.getDeltaTime()*90/(double)(movementTimeInSeconds));
            if(rotationAngleSumInDegrees+deltaDegrees >= 90){
                deltaDegrees = 90-rotationAngleSumInDegrees;
                moving = false;
            }

            rotationAngleSumInDegrees += deltaDegrees;

            cubeView.rotate(rotationDirection, deltaDegrees,bx,bz);
            if(!moving) {
                rotationAngleSumInDegrees = 0;
                bx = ax;
                bz = az;
            }
        }
    }


    public void move(Direction direction) {
        if(isMoving())
            return;

        startRotating();
        rotationDirection = direction;

        if(direction == Direction.UP)
            az--;
        if(direction == Direction.RIGHT)
            ax++;
        if(direction == Direction.DOWN)
            az++;
        if(direction == Direction.LEFT)
            ax--;
    }

}

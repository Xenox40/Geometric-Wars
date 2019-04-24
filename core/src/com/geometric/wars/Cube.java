package com.geometric.wars;
import com.badlogic.gdx.Gdx;

public class Cube {
    static final float movementTimeInSeconds = 0.25f;

    private final CubeView cubeView;


    private float rotationAngleSumInDegrees;
    private boolean moving = false;
    private int rotationDirection;

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


    /**
     *
     * @param direction
     *      0 -> up, 1->right, 2 ->down, 3->left
     */
    public void move(int direction) {
        //if(direction >= 4) throw...
        if(direction == 0)
            moveUp();
        if(direction == 1)
            moveLeft();
        if(direction == 2)
            moveDown();
        if(direction == 3)
            moveRight();
    }

    public void moveUp(){
        if(isMoving())
            return;
        startRotating();
        rotationDirection = 0;
        az--;
    }

    public void moveRight(){
        if(isMoving())
            return;
        startRotating();
        rotationDirection = 1;
        ax++;
    }

    public void moveDown(){
        if(isMoving())
            return;
        startRotating();

        rotationDirection = 2;
        az++;
    }

    public void moveLeft(){
        if(isMoving())
            return;
        startRotating();

        rotationDirection = 3;
        ax--;
    }
}

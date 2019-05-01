package com.geometric.wars.cube;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.geometric.wars.Direction;
import com.geometric.wars.maps.MapService;
import com.geometric.wars.scene.SceneManager;

public class DynamicCube {
    static final float movementTimeInSeconds = 0.25f;

    private float rotationAngleSumInDegrees;
    private boolean moving = false;
    private Direction rotationDirection;

    private int bx = 0, bz = 0;
    private int ax = 0, az = 0;

    private final DynamicCubeView dynamicCubeView;

    public DynamicCube(DynamicCubeView dynamicCubeView) {
        this.dynamicCubeView = dynamicCubeView;
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

            dynamicCubeView.rotate(rotationDirection, deltaDegrees,bx,bz);
            if(!moving) {
                finishRotating();
            }
        }
    }

    public boolean moveIsAllowed(Direction direction) {
        Vector2 nextPosition = direction.toVector2();
        return !(SceneManager.getInstance().getCurrentMapService().checkIfIsOccupied(bx + (int)nextPosition.x, bz + (int)nextPosition.y));
    }

    public void move(Direction direction) {
        if(isMoving() || !moveIsAllowed(direction))
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

    private void startRotating() {
        moving = true;
        rotationAngleSumInDegrees = 0;
    }

    private void finishRotating() {
        moving = false;
        rotationAngleSumInDegrees = 0;
        rotationDirection = null;
        bx = ax;
        bz = az;
    }

}

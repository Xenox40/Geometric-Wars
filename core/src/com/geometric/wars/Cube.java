package com.geometric.wars;
import com.badlogic.gdx.math.Vector3;

public class Cube {

    private CubeView cubeView;
    private Vector3 md = new Vector3(0,0,0);
    private Vector3 mr = new Vector3(0,0,0);
    int px = 0, pz = 0;
    public Cube(CubeView cubeView) {
        this.cubeView = cubeView;
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
        md.set(1,0,0);
        mr.set(-1,0,0);
        pz--;
        cubeView.move(md,mr);
    }

    public void moveRight(){
        md.set(1,0,0);
        mr.set(0,0,-1);
        px++;
        cubeView.move(md,mr);
    }

    public void moveDown(){
        md.set(0, 0, 1);
        mr.set(1,0,0);
        pz++;
        cubeView.move(md,mr);
    }


    public void moveLeft(){
        md.set(0, 0,1);
        mr.set(0,0,1);
        px--;
        cubeView.move(md,mr);
    }

}

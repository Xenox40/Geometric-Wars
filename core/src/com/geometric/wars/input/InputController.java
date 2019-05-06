package com.geometric.wars.input;

public interface InputController {
    boolean moveUp();
    boolean moveDown();
    boolean moveRight();
    boolean moveLeft();
    boolean shoot();

    /**
     * will be called after all controllable objects finishes their input  processing
     */
    void endOnProcessingInput();

    void dispose();
}

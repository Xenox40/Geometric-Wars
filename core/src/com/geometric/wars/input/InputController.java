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

    /**
     * will be called when switching to screen where InputController is needed
     * you can do here e.g. set gdx input processor
     */
    void activate();

    void dispose();
}

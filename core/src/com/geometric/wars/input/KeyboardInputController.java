package com.geometric.wars.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class KeyboardInputController implements InputController {
    private int upKey, downKey, rightKey, leftKey, shootKey;

    public KeyboardInputController(int upKey, int downKey, int rightKey, int leftKey, int shootKey) {
        this.upKey = upKey;
        this.downKey = downKey;
        this.rightKey = rightKey;
        this.leftKey = leftKey;
        this.shootKey = shootKey;
    }
    @Override
    public boolean moveUp() {
        return Gdx.input.isKeyPressed(upKey);
    }

    @Override
    public boolean moveDown() {
        return Gdx.input.isKeyPressed(downKey);
    }

    @Override
    public boolean moveRight() {
        return Gdx.input.isKeyPressed(rightKey);
    }

    @Override
    public boolean moveLeft() {
        return Gdx.input.isKeyPressed(leftKey);
    }

    @Override
    public boolean shoot() {
        return Gdx.input.isKeyPressed(shootKey);
    }


    @Override
    public void endOnProcessingInput() {}

    @Override
    public void activate() {}

    @Override
    public void dispose() {}
}

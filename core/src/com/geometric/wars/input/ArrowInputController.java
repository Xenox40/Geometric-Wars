package com.geometric.wars.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class ArrowInputController implements InputController {
    @Override
    public boolean moveUp() {
        return Gdx.input.isKeyPressed(Input.Keys.UP);
    }

    @Override
    public boolean moveDown() {
        return Gdx.input.isKeyPressed(Input.Keys.DOWN);
    }

    @Override
    public boolean moveRight() {
        return Gdx.input.isKeyPressed(Input.Keys.RIGHT);
    }

    @Override
    public boolean moveLeft() {
        return Gdx.input.isKeyPressed(Input.Keys.LEFT);
    }

    @Override
    public void endOnProcessingInput() {}
}
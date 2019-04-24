package com.geometric.wars.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.input.GestureDetector;

public class TouchInputController implements InputController {

    private boolean up;
    private boolean down;
    private boolean right;
    private boolean left;

    public TouchInputController() {
        Gdx.input.setInputProcessor(new SimpleDirectionGestureDetector(new SimpleDirectionGestureDetector.DirectionListener() {
            @Override
            public void onUp() {
                up = true;
            }

            @Override
            public void onRight() {
                right = true;

            }

            @Override
            public void onLeft() {
                left = true;

            }

            @Override
            public void onDown() {
                down = true;
            }
        }));
    }

    @Override
    public boolean moveUp() {
        if (up) {
            up = false;
            return true;
        }
        return false;
    }

    @Override
    public boolean moveDown() {
        if (down) {
            down = false;
            return true;
        }
        return false;
    }

    @Override
    public boolean moveRight() {
        if (right) {
            right = false;
            return true;
        }
        return false;
    }

    @Override
    public boolean moveLeft() {
        if (left) {
            left = false;
            return true;
        }
        return false;
    }
}


class SimpleDirectionGestureDetector extends GestureDetector {
    public interface DirectionListener {
        void onLeft();

        void onRight();

        void onUp();

        void onDown();
    }

    public SimpleDirectionGestureDetector(DirectionListener directionListener) {
        super(new DirectionGestureListener(directionListener));
    }

    private static class DirectionGestureListener extends GestureAdapter {
        DirectionListener directionListener;

        public DirectionGestureListener(DirectionListener directionListener) {
            this.directionListener = directionListener;
        }

        @Override
        public boolean fling(float velocityX, float velocityY, int button) {
            if (Math.abs(velocityX) > Math.abs(velocityY)) {
                if (velocityX > 0)
                    directionListener.onRight();
                else
                    directionListener.onLeft();
            } else {
                if (velocityY > 0)
                    directionListener.onDown();
                else
                    directionListener.onUp();
            }
            return super.fling(velocityX, velocityY, button);
        }
    }

}
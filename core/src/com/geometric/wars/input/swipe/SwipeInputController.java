package com.geometric.wars.input.swipe;

import com.badlogic.gdx.Gdx;
import com.geometric.wars.input.InputController;

/**
 * Singleton, see getInstance()
 */
public class SwipeInputController implements InputController {

    private static SwipeInputController instance = null;
    private boolean up;
    private boolean down;
    private boolean right;
    private boolean left;

    public static SwipeInputController getInstance() {
        if (instance == null)
            instance = new SwipeInputController();
        return instance;
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


    private SwipeInputController() {
        DirectionGestureDetector gestureDetector = new DirectionGestureDetector(createDirectionListener());
        Gdx.input.setInputProcessor(gestureDetector);
    }

    private DirectionListener createDirectionListener() {
        return new DirectionListener() {
            @Override
            public void onLeft() {
                left = true;
            }

            @Override
            public void onRight() {
                right = true;
            }

            @Override
            public void onUp() {
                up = true;
            }

            @Override
            public void onDown() {
                down = true;
            }
        };
    }
}


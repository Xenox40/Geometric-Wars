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
    private boolean tapped;
    private DirectionGestureDetector gestureDetector;


    public static SwipeInputController getInstance() {
        if (instance == null)
            instance = new SwipeInputController();
        return instance;
    }

    @Override
    public boolean moveUp() {
        return up;
    }

    @Override
    public boolean moveDown() {
        return down;
    }

    @Override
    public boolean moveRight() {
        return right;
    }

    @Override
    public boolean moveLeft() {
        return left;
    }

    @Override
    public boolean shoot() { return tapped; }

    @Override
    public void endOnProcessingInput() {
        up = right = down = left = tapped =false;
    }

    @Override
    public void activate() {
        Gdx.input.setInputProcessor(gestureDetector);
    }

    private SwipeInputController() {
        gestureDetector = new DirectionGestureDetector(createDirectionListener());

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

            @Override
            public void onTap() {
                tapped = true;
            }
        };
    }


    public void dispose() {
        instance = null;
    }
}


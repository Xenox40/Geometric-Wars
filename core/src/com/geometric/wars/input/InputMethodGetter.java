package com.geometric.wars.input;

import com.badlogic.gdx.utils.Array;


public class InputMethodGetter {
    private InputMethodGetter(){}
    private static InputMethodGetter instance;
    private Array<InputController> inputs = new Array<>();

    public static InputMethodGetter getInstance() {
        if(instance == null){
            instance = new InputMethodGetter();
        }
        return instance;
    }

    public void activateAll() {
        for (InputController controller : getInstance().inputs)
            controller.activate();
    }

    public void addInputMethod(InputController inputController) {
        inputs.add(inputController);
    }

    public InputController getInputMethod(int i) {
        return inputs.get(i);
    }

    public static void dispose() {
        if(instance != null) {
            for (InputController controller : getInstance().inputs)
                controller.dispose();
            getInstance().inputs.clear();
            instance = null;
        }
    }

}

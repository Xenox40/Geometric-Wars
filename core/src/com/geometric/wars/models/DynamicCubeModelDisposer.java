package com.geometric.wars.models;

import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.utils.Array;


public class DynamicCubeModelDisposer {
    private DynamicCubeModelDisposer() { }

    private static Array<Model> models = new Array<>();


    public static void addModel(Model model) {
        models.add(model);
    }


    public static void dispose() {
        for(Model model : models) {
            model.dispose();
        }
        models.clear();
    }
}

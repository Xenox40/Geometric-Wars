package com.geometric.wars.models;

import com.badlogic.gdx.graphics.g3d.Model;


import java.util.HashMap;


public class DynamicCubeModel {
    private DynamicCubeModel() { }

    private static HashMap<String,Model> modelMap = new HashMap<>();

    public static Model getModel(String name) {
        return modelMap.get(name);
    }

    public static void addModel(String name, Model model) {
        modelMap.put(name,model);
    }

    public static void disposeModel(String name) {
        getModel(name).dispose();
    }

    public static void dispose() {
        for(Model model : modelMap.values()) {
            model.dispose();
        }
        modelMap.clear();
    }
}

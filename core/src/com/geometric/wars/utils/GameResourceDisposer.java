package com.geometric.wars.utils;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;

import java.util.HashMap;

/**
 * disposes objects created before start on game that should be deleted after end of game,
 * like cube disposables, textures
 * object can be accessed by name if provided
 */
public class GameResourceDisposer {
    private GameResourceDisposer() { }

    private static Array<Disposable> disposables = new Array<>();

    /**
     * map is not responsible for disposing, it only contains some duplicates with Array
     */
    private static HashMap<String,Disposable> map = new HashMap<>();

    public static void addResource(String name, Disposable disposable) {
        if(map.containsKey(name)) {
            if(!map.get(name).equals(disposable))
                throw new RuntimeException("resource with given name already exists and wasn't disposed");
        }
        else {
            disposables.add(disposable);
            map.put(name, disposable);
        }
    }

    public static void addResource(Disposable disposable) {
        disposables.add(disposable);
    }


    public static Disposable getResource(String name) {
       return map.get(name);
    }
    public static boolean contains(String name) {
        return map.containsKey(name);
    }





    public static void dispose() {
        for(Disposable disposable : disposables) {
            disposable.dispose();
        }
        disposables.clear();
        map.clear();
    }
}

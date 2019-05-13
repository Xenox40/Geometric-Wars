package com.geometric.wars.maps;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.geometric.mapgenerators.GameMap;
import com.geometric.wars.collisions.Collidable;
import com.geometric.wars.scene.SceneManager;

import java.io.IOException;
import java.util.Scanner;

public class GeneratedMapLoader extends MapLoader {

    private GameMap map;

    public GeneratedMapLoader(GameMap map) {
        this.map = map;
    }

    @Override
    public void load() {
        MapService service  = SceneManager
                .getInstance()
                .getCurrentMapService();

        Array<Array<Collidable>> collidables;
        collidables = new Array<>();

        int height = 0;
        int width = -1;
        try {
            int row = 0;
            for (int i = 0; i < map.getHeight(); i++) {
                String line = map.getRow(i);
                Array<Collidable> objects = new Array<>();
                if (width == -1)
                    width = line.length();

                addObjectsToMap(line, row, objects);

                height++;
                row ++;
                collidables.add(objects);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        addFloor(height, width);

        service.setHeight(height);
        service.setWidth(width);
        service.addCollidables(collidables);

    }
}

package com.geometric.wars.maps;

import com.badlogic.gdx.utils.Array;
import com.geometric.wars.collisions.Collidable;
import com.geometric.wars.collisions.DynamicBody;
import com.geometric.wars.player.person.PersonsCube;

import java.util.ArrayList;
import java.util.List;

public class MapService {

    public MapService() {
        mapObjects = new Array<>();
    }

    private Array<Array<List<Collidable>> > mapObjects;

    private int width;
    private int height;


    void addCollidables(Array<Array<Collidable> > collidables) {
        for(int i=0;i<collidables.size;i++){
            for(int j=0;j<collidables.get(i).size;j++) {
                if(collidables.get(i).get(j) != null)
                    mapObjects.get(i).get(j).add(collidables.get(i).get(j));
            }
        }
    }
    void setWidth(int width) {
        this.width = width;
        updateMapSize();
    }

    void setHeight(int height) {
        this.height = height;
        updateMapSize();
    }

    private void updateMapSize() {
        if(mapObjects == null)
            mapObjects = new Array<>();

        mapObjects.setSize(height);

        for(int i=0;i<height;i++) {
            if (mapObjects.get(i) == null)
                mapObjects.set(i, new Array<List<Collidable>>());
            mapObjects.get(i).setSize(width);
            for(int j=0;j<width;j++) {
                if(mapObjects.get(i).get(j) == null)
                    mapObjects.get(i).set(j,new ArrayList<Collidable>());
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }


    public boolean isMoveAllowed(DynamicBody dynamicBody, int newX, int newY) {
        if(newX < 0 || newY < 0 ||  newX >= getWidth() || newY >= getHeight())
            return false;

        for(Collidable c : mapObjects.get(newY).get(newX)) {
            if(!c.canCollideWith(dynamicBody)) {
                return false;
            }
        }
        return true;
    }

    public void updatePosition(DynamicBody dynamicBody, int oldX, int oldY, int newX, int newY) {

        if(!mapObjects.get(oldY).get(oldX).contains(dynamicBody)) {
            for (int i = 0; i < getHeight(); i++){
                for (int j = 0; j < getWidth(); j++) {
                    System.out.print((mapObjects.get(i).get(j).size() == 0 ? "{}" : mapObjects.get(i).get(j).get(0).toString().substring(35)) + " ");
                }
                System.out.println();
            }
            throw new RuntimeException(oldX+" "+oldY+" "+newX+" "+newY);

        }
        mapObjects.get(oldY).get(oldX).remove(dynamicBody);

        for (Collidable c : mapObjects.get(newY).get(newX))
            c.onCollisionWith(dynamicBody);

        mapObjects.get(newY).get(newX).add(dynamicBody);


    }

}

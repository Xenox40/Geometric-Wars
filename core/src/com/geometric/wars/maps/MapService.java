package com.geometric.wars.maps;

import com.badlogic.gdx.utils.Array;
import com.geometric.wars.collisions.Collidable;
import com.geometric.wars.collisions.DynamicBody;
import com.geometric.wars.player.PlayersCube;
import com.geometric.wars.utils.Position;


public class MapService {

    public MapService() {
        mapObjects = new Array<>();
        mapGraph = new MapGraph(this);
    }
    public MapGraph mapGraph;
    private Array<Array<Array<Collidable>> > mapObjects;
    public Array<PlayersCube> cubes = new Array<>();
    private int width;
    private int height;


    public void addCollidable(Collidable collidable, int i, int j) {
        if(collidable != null) {
            mapObjects.get(i).get(j).add(collidable);
            if(collidable instanceof PlayersCube)
                cubes.add((PlayersCube)collidable);
        }
    }
    public void removeCollidable(Collidable collidable, int x, int y) {
        if(x < 0 || y < 0 ||  x >= getWidth() || y >= getHeight())
            return;
        mapObjects.get(y).get(x).removeValue(collidable,true);
    }

    public void addCollidables(Array<Array<Collidable> > collidables) {
        for(int i=0;i<collidables.size;i++){
            for(int j=0;j<collidables.get(i).size;j++) {
                addCollidable(collidables.get(i).get(j),i,j);
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
                mapObjects.set(i, new Array<Array<Collidable>>());
            mapObjects.get(i).setSize(width);
            for(int j=0;j<width;j++) {
                if(mapObjects.get(i).get(j) == null)
                    mapObjects.get(i).set(j,new Array<Collidable>());
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Array<Position> getEmptyCells() {
        Array<Position> emptyCells = new Array<>();
        for(int i=0;i<mapObjects.size;i++) {
            for(int j=0;j<mapObjects.get(i).size;j++) {
                if(mapObjects.get(i).get(j).size == 0)
                    emptyCells.add(new Position(i,j));
            }
        }
        return emptyCells;
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


    public void extendCollisionArea(DynamicBody dynamicBody, int x, int y) {
        if(x < 0 || y < 0 ||  x >= getWidth() || y >= getHeight())
            return;
        if(!mapObjects.get(y).get(x).contains(dynamicBody,true)) {
            for (Collidable c : mapObjects.get(y).get(x)) {
                c.onCollisionWith(dynamicBody);
            }
            if(dynamicBody.exists())
                mapObjects.get(y).get(x).add(dynamicBody);
        }
    }

    public void decreaseCollisionArea(DynamicBody dynamicBody, int x, int y) {
        removeCollidable(dynamicBody, x,y);
    }


    public Collidable getCollidable(int x, int y) {
        return mapObjects.get(y).get(x).get(0);
    }
}

package com.geometric.mapgenerators;

import com.badlogic.gdx.math.MathUtils;
import com.geometric.wars.maps.GameMap;
import com.geometric.wars.utils.algorithms.FindAndUnion;

import java.util.ArrayList;
import java.util.Collections;

public class DefaultMapConnector implements MapConnector{
    private int [][] myComponent;
    private GameMap map;
    private final int iterations;
    private int componentCount = 1;
    private char fakeEmpty = ',';

    /**
    used for time optimization
     */
    private int maxConnectionLength1D = 15;

    public DefaultMapConnector(int iterations){
        this.iterations = iterations;
    }

    @Override
    public void connectAllComponents(GameMap map) {
        this.map = map;

        for(int i=0;i<iterations;i++){
            connect();
        }
        end();
    }

    private void connect() {
        findComponents();
        ArrayList<VerticesPair> verticesPairs = new ArrayList<>();
        for(int i=0;i<map.getHeight();i++){
            for(int j=0;j<map.getWidth();j++) {
                for(int p=i;p<i+maxConnectionLength1D;p++) {
                    for (int q = j-maxConnectionLength1D; q < j+maxConnectionLength1D; q++) {
                        if(map.isIn(p,q) && (i != p || j != q) && !map.isWall(i,j)
                                && !map.isWall(p,q) && myComponent[i][j] != myComponent[p][q]) {
                            verticesPairs.add(new VerticesPair(i, j, myComponent[i][j], p, q, myComponent[p][q], -map.getWidth() / 5, map.getWidth() / 5));
                        }
                        }
                }
            }
        }
        randomShuffle(verticesPairs);
        Collections.sort(verticesPairs);
        com.geometric.wars.utils.algorithms.FindAndUnion fau = new FindAndUnion(componentCount+1);
        for (int i=0;i<verticesPairs.size() ;i++){
            if(fau.find(verticesPairs.get(i).component1) != fau.find(verticesPairs.get(i).component2)){
                fau.union(verticesPairs.get(i).component1,verticesPairs.get(i).component2);
                drillPath(verticesPairs.get(i));
            }
        }
    }

    private void end(){
        for(int i=0;i<map.getHeight();i++)
            for(int j=0;j<map.getWidth();j++)
                if(map.get(i,j) == fakeEmpty)
                    map.putEmpty(i,j);

        slowlyConnectIfNotConnected();

        for(int i=0;i<map.getHeight();i++)
            for(int j=0;j<map.getWidth();j++)
                if(map.get(i,j) == fakeEmpty)
                    map.putEmpty(i,j);
    }

    private void slowlyConnectIfNotConnected() {
        connect();
        if(componentCount > 1) {
            int maxConnectionLength1DCp = maxConnectionLength1D;
            maxConnectionLength1D = map.getHeight() + map.getWidth();
            connect();
            maxConnectionLength1D = maxConnectionLength1DCp;
        }
    }

    private void findComponents() {
        componentCount = 0;
        myComponent = new int[map.getHeight()][map.getWidth()];
        for(int i=0;i<map.getHeight();i++)
            for(int j=0;j<map.getWidth();j++)
                myComponent[i][j] = 0;

        for(int i=0;i<map.getHeight();i++) {
            for(int j=0;j<map.getWidth();j++){
                if(myComponent[i][j] == 0 && !map.isWall(i,j)) {
                    componentCount++;
                    dfs(i, j, componentCount);
                }
            }
        }
    }

    private void dfs(int i,int j, int currentComponent) {
         if(i < 0 || j < 0 || i >= map.getHeight() || j >= map.getWidth() || map.isWall(i,j) || myComponent[i][j] != 0)
             return;
         myComponent[i][j] = currentComponent;
         dfs(i+1,j,currentComponent);
         dfs(i-1,j,currentComponent);
         dfs(i,j+1,currentComponent);
         dfs(i,j-1,currentComponent);
     }

     private void drillPath(VerticesPair pair) {
        if(pair.i1 < pair.i2) {
            for (int i = pair.i1; i != pair.i2; i++)
                map.put(i, pair.j1, fakeEmpty);
        }
        else{
            for (int i = pair.i1; i != pair.i2; i--)
                map.put(i, pair.j1, fakeEmpty);
        }

        if(pair.j1 < pair.j2) {
             for(int j=pair.j1; j != pair.j2; j++)
                 map.put(pair.i2, j, fakeEmpty);
         }
         else{
            for(int j=pair.j1; j != pair.j2; j--)
                map.put(pair.i2, j, fakeEmpty);
         }
     }

    private static class VerticesPair implements Comparable<VerticesPair>{
        int i1,j1;
        int i2,j2;
        int component1;
        int component2;
        private int distance;
        VerticesPair(int i1, int j1, int component1, int i2, int j2, int component2) {
            this.i1 = i1;
            this.j1 = j1;
            this.component1 = component1;
            this.i2 = i2;
            this.j2 = j2;
            this.component2 = component2;
            distance = Math.abs(i1-j1)+Math.abs(i2-j2);
        }
        VerticesPair(int i1, int j1, int component1, int i2, int j2, int component2, int miniDistanceAdd, int maxiDistanceAdd) {
            this(i1,j1,component1,i2,j2,component2);
            addRandomToDistance(miniDistanceAdd,maxiDistanceAdd);
        }


        void addRandomToDistance(int miniAdd, int maxiAdd){
            distance += MathUtils.random(miniAdd,maxiAdd);
        }


        @Override
        public int compareTo(VerticesPair verticesPair) {
            return Integer.compare(this.distance, verticesPair.distance);
        }
    }

    private <T> void randomShuffle(ArrayList<T> list){
        for (int i=list.size()-1; i>0; i--) {
            int index = MathUtils.random(i);

            T a = list.get(index);
            list.set(index,list.get(i));
            list.set(i,a);
        }
    }

}

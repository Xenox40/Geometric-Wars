package com.geometric.mapgenerators;

import com.badlogic.gdx.math.MathUtils;

import java.util.ArrayList;
import java.util.Collections;

public class DefaultMapConnector implements MapConnector{
    private int [][] myComponent;
    private GameMap map;
    private int iterations;
    private int componentCount = 1;
    private char fakeEmpty = ',';


    public DefaultMapConnector(int iterations){
        this.iterations = iterations;
    }

    @Override
    public void connectAllComponents(GameMap map) {
        this.map = map;

        while(iterations --> 0){
            connect();
        }
        end();
    }

    private void connect() {
        findComponents();
        ArrayList<VerticesPair> verticesPairs = new ArrayList<>();
        for(int i=0;i<map.getHeight();i++){
            for(int j=0;j<map.getWidth();j++) {
                for(int p=i-15;p<i+15;p++) {
                    for (int q = j-15; q < j+15; q++) {
                        if(map.isIn(p,q) && i <= p && j <= q && (i != p || j != q) && map.isEmpty(i,j)
                                && map.isEmpty(p,q) && myComponent[i][j] != myComponent[p][q])
                            verticesPairs.add(new VerticesPair(i,j,myComponent[i][j],p,q,myComponent[p][q],-map.getWidth()/5,map.getWidth()/5));
                    }
                }
            }
        }
        randomShuffle(verticesPairs);
        Collections.sort(verticesPairs);
        FindAndUnion fau = new FindAndUnion(componentCount+1);
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
    }

    private void findComponents() {
        componentCount = 1;
        myComponent = new int[map.getHeight()][map.getWidth()];
        for(int i=0;i<map.getHeight();i++)
            for(int j=0;j<map.getWidth();j++)
                myComponent[i][j] = 0;

        for(int i=0;i<map.getHeight();i++) {
            for(int j=0;j<map.getWidth();j++){
                if(myComponent[i][j] == 0)
                    dfs(i,j,componentCount++);
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
        pair.normalize();
        for(int i=pair.i1; i != pair.i2;i++) {
            map.put(i,pair.j1,fakeEmpty);
        }
        for(int j=pair.j1; j != pair.j2; j++) {
            map.put(pair.i2,j,fakeEmpty);
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

        void normalize() {
            if (i1 > i2) {
                int tmp = i1;
                i1 = i2;
                i2 = tmp;
            }
            if(j1 > j2) {
                int tmp = j1;
                j1 = j2;
                j2 = tmp;
            }
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

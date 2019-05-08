package com.geometric.mapgenerators;

import java.util.ArrayList;
import java.util.Collections;

class MapConnector {
    private final GameMap map;
    private int [][] myComponent;
    private int componentCount = 1;

    public MapConnector(GameMap map){
        this.map = map;
        myComponent = new int[map.getHeight()][map.getWidth()];
    }

    private static class VerticesPair implements Comparable<VerticesPair>{
        public int i1,j1;
        public int i2,j2;
        public int component1;
        public int component2;
        private int distance;
        public VerticesPair(int i1, int j1, int component1, int i2, int j2, int component2) {
            this.i1 = i1;
            this.j1 = j1;
            this.component1 = component1;
            this.i2 = i2;
            this.j2 = j2;
            this.component2 = component2;
            distance = Math.abs(i1-j1)+Math.abs(i2-j2);
        }

        public void normalize() {
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
            if(this.distance < verticesPair.distance)
                return 1;
            if(this.distance > verticesPair.distance)
                return -1;
            int hash1 = this.i1+this.i2*this.j1+this.j2;
            int hash2 = verticesPair.i1+ verticesPair.i2* verticesPair.j1+ verticesPair.j2;
            return Integer.compare(hash1,hash2);
        }
    }

    public void connectAllComponents() {
        findComponents();
        ArrayList<VerticesPair> verticesPairs = new ArrayList<>();
        for(int i=0;i<map.getHeight();i++){
            for(int j=0;j<map.getWidth();j++) {
                for(int p=i-15;p<i+15;p++) {
                    for (int q = j-15; q < j+15; q++) {
                        if(map.isIn(p,q) && i <= p && j <= q && (i != p || j != q) && map.isEmpty(i,j)
                                && map.isEmpty(p,q) && myComponent[i][j] != myComponent[p][q])
                            verticesPairs.add(new VerticesPair(i,j,myComponent[i][j],p,q,myComponent[p][q]));
                    }
                }
            }
        }
        Collections.sort(verticesPairs);
        FindAndUnion fau = new FindAndUnion(componentCount+1);
        int joinedComponents = 0;
        for (int i=0;i<verticesPairs.size() && joinedComponents < componentCount;i++){
            if(fau.find(verticesPairs.get(i).component1) != fau.find(verticesPairs.get(i).component2)){
                fau.union(verticesPairs.get(i).component1,verticesPairs.get(i).component2);
                drillPath(verticesPairs.get(i));
                joinedComponents++;
            }
        }
    }

    private void findComponents() {
        componentCount = 1;
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
            map.putEmpty(i,pair.j1);
        }
        for(int j=pair.j1; j != pair.j2; j++) {
            map.putEmpty(pair.i2,j);
        }
     }
}

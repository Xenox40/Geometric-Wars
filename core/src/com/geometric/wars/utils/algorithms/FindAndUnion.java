package com.geometric.wars.utils.algorithms;

public class FindAndUnion {
    private int[] fau;
    private int[] size;

    public FindAndUnion(int n){
        fau = new int[n];
        size = new int[n];
        for(int i=0;i<n;i++){
            fau[i] =i;
            size[i] = 1;
        }
    }

    public int find(int i) {
        if(fau[i] != i)
            fau[i] = find(fau[i]);
        return fau[i];
    }

    public void union(int a, int b) {
        a = find(a);
        b = find(b);
        if(a == b)
            return;

        if(size[a] > size[b]) {
            size[a] += size[b];
            fau[b] = fau[a];
        }
        else{
            size[b] += size[a];
            fau[a] = fau[b];
        }
    }

}

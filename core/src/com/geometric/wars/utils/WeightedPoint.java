package com.geometric.wars.utils;

public class WeightedPoint extends Position {
    public int weight;
    public WeightedPoint(int x,int y, int weight){
        super(x,y);
        this.weight = weight;
    }
}
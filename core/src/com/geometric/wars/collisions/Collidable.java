package com.geometric.wars.collisions;

public interface Collidable {
    boolean canCollideWith(DynamicBody object);
    void onCollisionWith(DynamicBody object);
}

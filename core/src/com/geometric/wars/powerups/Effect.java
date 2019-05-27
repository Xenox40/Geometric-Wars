package com.geometric.wars.powerups;

import com.geometric.wars.player.ShootingPlayersCube;

public interface Effect {
    void apply(ShootingPlayersCube cube);
    void revert(ShootingPlayersCube cube);
}

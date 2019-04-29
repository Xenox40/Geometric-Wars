package com.geometric.wars.player.bots.randomactingbot;

import com.geometric.wars.player.PlayersCube;
import com.geometric.wars.player.PlayersCubeFactory;

public class RandomBotFactory extends PlayersCubeFactory {

    @Override
    public PlayersCube createCube(int x, int y) {
        return new RandomBotCube(x,y);
    }
}

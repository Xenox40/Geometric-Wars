package com.geometric.wars.player.bots.randomactingbot;

import com.geometric.wars.maps.MapObjectCheckerService;
import com.geometric.wars.player.PlayersCube;
import com.geometric.wars.player.PlayersCubeFactory;

public class RandomBotFactory extends PlayersCubeFactory {
    public RandomBotFactory(MapObjectCheckerService mapObjectCheckerService) {
        super(mapObjectCheckerService);
    }

    @Override
    public PlayersCube createCube(int x, int y) {
        return new RandomBotCube(mapObjectCheckerService, x,y);
    }
}

package com.geometric.wars.scene;

import com.badlogic.gdx.utils.Array;
import com.geometric.wars.player.ShooterPlayersController;
import com.geometric.wars.player.ShootingPlayersCube;


public class Scoreboard {
    private Array<ShooterPlayersController> cubes = new Array<>();
    private Array<Integer> scores  = new Array<>();

    public void addPoints(ShootingPlayersCube killer, int points) {
        for(int i=0;i<cubes.size;i++){
            if(cubes.get(i).getCube().equals(killer))
                scores.set(i,scores.get(i)+points);
        }
    }

    public void addPlayer(ShooterPlayersController player) {
        cubes.add(player);
        scores.add(0);
    }
}

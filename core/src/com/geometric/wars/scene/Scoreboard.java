package com.geometric.wars.scene;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.utils.Array;
import com.geometric.wars.gameobjects.DynamicGameObject;
import com.geometric.wars.player.ShooterPlayersController;
import com.geometric.wars.player.ShootingPlayersCube;
import com.geometric.wars.utils.Action;


public class Scoreboard implements DynamicGameObject {
    public static class PlayerScore implements Comparable<PlayerScore>{
        public PlayerScore(ShooterPlayersController controller, int score) {
            this.controller = controller;
            this.score = score;
        }
        public ShooterPlayersController controller;
        public int score;

        @Override
        public int compareTo(PlayerScore other) {
            return  -Integer.compare(score,other.score);
        }
    }

    private static final int scoreToWin = 10;
    private Array<PlayerScore> scores = new Array<>();
    private Action endOfGameAction;

    void addPoints(ShootingPlayersCube killer, int points) {
        for(int i=0;i<scores.size;i++){
            if(scores.get(i).controller.getCube().equals(killer))
                scores.get(i).score += points;
        }
        scores.sort();
    }

    void addPlayer(ShooterPlayersController player) {
        scores.add(new PlayerScore(player,0));
        scores.sort();
    }

    public boolean isEndOfGame() {
        return scores.get(0).score >= scoreToWin;
    }

    public ShooterPlayersController getWinner() {
        if(!isEndOfGame())
            return null;
        return scores.get(0).controller;


    }

    public Array<PlayerScore> getScores() {
        return scores;
    }

    @Override
    public void update() {
        if(isEndOfGame())
            endOfGameAction.doAction();

    }

    @Override
    public void render(ModelBatch modelBatch, Environment environment) { }

    public void addActionOnEndOfGame(Action action) {
        this.endOfGameAction = action;
    }
}

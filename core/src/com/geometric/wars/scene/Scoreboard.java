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
    private static final int scoreToWin = 3;
    private Array<ShooterPlayersController> cubes = new Array<>();
    private Array<Integer> scores  = new Array<>();
    private Action endOfGameAction;

    void addPoints(ShootingPlayersCube killer, int points) {
        for(int i=0;i<cubes.size;i++){
            if(cubes.get(i).getCube().equals(killer))
                scores.set(i,scores.get(i)+points);
        }
    }

    void addPlayer(ShooterPlayersController player) {
        cubes.add(player);
        scores.add(0);
    }

    private int getIndexWithMaximumScore(){
        int maxIndex = 0;
        for(int i=0;i<cubes.size;i++) {
            if (scores.get(i) > scores.get(maxIndex))
                maxIndex = i;
        }
        return maxIndex;
    }

    public boolean isEndOfGame() {
        return scores.get(getIndexWithMaximumScore()) >= scoreToWin;
    }

    public ShooterPlayersController getWinner() {
        if(!isEndOfGame())
            return null;
        return cubes.get(getIndexWithMaximumScore());

    }

    @Override
    public void update() {
        if(isEndOfGame())
            endOfGameAction.doAction();

    }

    @Override
    public void render(ModelBatch modelBatch, Environment environment) { }

    @Override
    public void renderGUI(SpriteBatch batch) {
        //TODO render scoreboard
    }

    public void addActionOnEndOfGame(Action action) {
        this.endOfGameAction = action;
    }
}

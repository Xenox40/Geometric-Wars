package com.geometric.wars.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.geometric.wars.GeometricWars;
import com.geometric.wars.scene.SceneManager;
import com.geometric.wars.scene.Scoreboard;

public class GameResultScreen extends AbstractMenuScreen {
    public GameResultScreen(GeometricWars game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();
        Scoreboard scoreboard = SceneManager.getInstance().getCurrentScene().getScoreboard();

        Table scoreboardTable = new Table();
        scoreboardTable.row();
        for(Scoreboard.PlayerScore score : scoreboard.getScores()) {
            final Label nameLabel = new Label(score.controller.getCube().getName(),skin);
            nameLabel.setFontScale(2);
            scoreboardTable.add(nameLabel).pad(20,50,20,50).expand();

            final Label scoreLabel = new Label(String.valueOf(score.score),skin);
            scoreLabel.setFontScale(2);
            scoreboardTable.add(scoreLabel).pad(20,50,20,50).expand();
            scoreboardTable.row();
        }

        Table table = new Table();
        table.setFillParent(true);
        table.top();
        Label winnerLabelMessage = new Label(scoreboard.getWinner().getCube().getName()+" has won the game",skin);
        winnerLabelMessage.setFontScale(2);
        table.add(winnerLabelMessage).colspan(2).expand();
        table.row();
        table.add(scoreboardTable).expand();
        table.row();
        TextButton backButton = new TextButton("Back to main menu",skin);
        backButton.getLabel().setFontScale(2);

        backButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
               setScreenToMainMenu();
            }
        });

        table.add(backButton).colspan(2).minSize(600,100).expand();

        stage.addActor(table);
    }

    private void setScreenToMainMenu() {
        if(game.isAndroidPlatform()) {
            Gdx.app.exit();
            System.exit(0);
        }
        else
            game.setScreen(game.mainMenuScreen);
    }

}

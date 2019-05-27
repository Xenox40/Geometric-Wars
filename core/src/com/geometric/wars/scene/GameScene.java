package com.geometric.wars.scene;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.geometric.wars.gameobjects.DynamicGameObject;
import com.geometric.wars.maps.MapService;
import com.geometric.wars.models.BarModel;
import com.geometric.wars.player.ShooterPlayersController;
import com.geometric.wars.player.ShootingPlayersCube;
import com.geometric.wars.utils.Action;


public class GameScene extends Scene {

    private MapService mapService = new MapService();
    private ShootingService shootingService = new ShootingService();
    private RespawningService respawningService = new RespawningService();
    private Scoreboard scoreboard = new Scoreboard();
    private PowerUpService powerUpService = new PowerUpService();

    NinePatch healthBarModel = BarModel.getInstance().newBar(Color.GREEN);
    NinePatch overheatBarModel = BarModel.getInstance().newBar(Color.RED);
    NinePatch colorIndicators = BarModel.getInstance().newBar(Color.WHITE);
    BitmapFont font = new BitmapFont();
    static final float barHeight = 10f;

    GameScene() {
        super();
        scoreboard.addActionOnEndOfGame(new Action(){
            @Override
            public void doAction() {
                isEndOfScene = true;
            }
        });;
        addDynamicGameObject(shootingService);
        addDynamicGameObject(respawningService);
        addDynamicGameObject(powerUpService);
        addDynamicGameObject(scoreboard);
    }

    @Override
    public void renderGUI(SpriteBatch batch) {
        float posX = 40, posY = 40;
        Array<Scoreboard.PlayerScore> scores = new Array<>(scoreboard.getScores());
        scores.reverse();
        for (Scoreboard.PlayerScore playerScore : scores) {
            ShootingPlayersCube cube = playerScore.controller.getCube();
            int hp = cube.getHealthPoints();
            float overheat = cube.getGunHeatLevel();
            colorIndicators.setColor(playerScore.controller.getCube().getColor());
            colorIndicators.draw(batch,posX-3*barHeight/2,posY,barHeight,barHeight);
            font.draw(batch,playerScore.score+" " + cube.getName(), posX, posY+barHeight);
            if(hp > 0) {
                healthBarModel.draw(batch, posX+120f, posY, 5f*hp, barHeight);
                overheatBarModel.draw(batch, posX+120,posY+barHeight*1.2f,5*overheat*35,barHeight);
            }
            posY += 2*barHeight+20;
        }
    }

    @Override
    public void addDynamicGameObject(DynamicGameObject object) {
        super.addDynamicGameObject(object);
        if(object instanceof ShooterPlayersController)
            scoreboard.addPlayer((ShooterPlayersController)object);
    }

    public MapService getMapService() {
        return mapService;
    }
    public ShootingService getShootingService() {
        return shootingService;
    }
    public RespawningService getRespawningService() {
        return respawningService;
    }
    public Scoreboard getScoreboard() {return scoreboard;}
    public PowerUpService getPowerUpService() {return powerUpService;}

    @Override
    public void dispose() {
        super.dispose();
        font.dispose();
    }
}

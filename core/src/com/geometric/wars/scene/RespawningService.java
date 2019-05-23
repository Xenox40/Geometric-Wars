package com.geometric.wars.scene;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.utils.Queue;
import com.badlogic.gdx.utils.TimeUtils;
import com.geometric.wars.gameobjects.DynamicGameObject;
import com.geometric.wars.player.ShootingPlayersCube;


public class RespawningService implements DynamicGameObject {
    private static final long respawnTimeInMillis = 2000;
    private Queue<ShootingPlayersCube> deadCubes = new Queue<>();
    private Queue<Long> lastDeathTimeInMillis = new Queue<>();

    public void moveToKilledQueue(ShootingPlayersCube cube) {
        deadCubes.addLast(cube);
        lastDeathTimeInMillis.addLast(TimeUtils.millis());
    }

    @Override
    public void render(ModelBatch modelBatch, Environment environment) {}

    @Override
    public void renderGUI(SpriteBatch batch) {

    }

    @Override
    public void update() {
        while (!deadCubes.isEmpty() && TimeUtils.timeSinceMillis(lastDeathTimeInMillis.first()) >= respawnTimeInMillis) {
            ShootingPlayersCube cube = deadCubes.removeFirst();
            lastDeathTimeInMillis.removeFirst();
            cube.setHealthPoints(ShootingPlayersCube.startingHp);
            cube.setGunHeatLevel(0);
        }
    }
}

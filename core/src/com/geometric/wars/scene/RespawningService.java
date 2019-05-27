package com.geometric.wars.scene;

import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.utils.Queue;
import com.badlogic.gdx.utils.TimeUtils;
import com.geometric.wars.gameobjects.DynamicGameObject;
import com.geometric.wars.player.ShootingPlayersCube;
import com.geometric.wars.powerups.EffectApplicator;
import com.geometric.wars.powerups.InvincibilityPowerUp;
import com.geometric.wars.utils.Position;


public class RespawningService implements DynamicGameObject {
    private static final long respawnTimeInMillis = 2000;
    private Queue<ShootingPlayersCube> deadCubes = new Queue<>();
    private Queue<Long> lastDeathTimeInMillis = new Queue<>();

    public void moveToKilledQueue(ShootingPlayersCube cube) {
        SceneManager.getInstance().getCurrentMapService().decreaseCollisionArea(cube, cube.getPosition().x, cube.getPosition().y);
        SceneManager.getInstance().getCurrentMapService().decreaseCollisionArea(cube, cube.getApproachingPosition().x, cube.getApproachingPosition().y);
        SceneManager.getInstance().getCurrentScene().getPowerUpService().getEffectApplicator().clearEffectsOn(cube);
        deadCubes.addLast(cube);
        lastDeathTimeInMillis.addLast(TimeUtils.millis());
    }

    @Override
    public void render(ModelBatch modelBatch, Environment environment) {}

    @Override
    public void update() {
        while (!deadCubes.isEmpty() && TimeUtils.timeSinceMillis(lastDeathTimeInMillis.first()) >= respawnTimeInMillis) {
            ShootingPlayersCube cube = deadCubes.removeFirst();
            lastDeathTimeInMillis.removeFirst();
            Position position =  SceneManager.getInstance().getCurrentMapService().getEmptyCells().random();
            cube.setPosition(position.y,position.x);
            SceneManager.getInstance().getCurrentScene().getPowerUpService().applyPowerUpTo(cube,new InvincibilityPowerUp(2000));
            cube.setHealthPoints(ShootingPlayersCube.startingHp);
            cube.setGunHeatLevel(0);
            SceneManager.getInstance().getCurrentMapService().extendCollisionArea(cube, cube.getPosition().x, cube.getPosition().y);
        }
    }
}

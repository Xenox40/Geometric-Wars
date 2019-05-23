package com.geometric.wars.scene;

import com.badlogic.gdx.utils.TimeUtils;
import com.geometric.wars.cube.mountables.MountableGun;

public class OverheatCalculator {
    private static final double coolingPerSecond = 0.08;

    public boolean canShoot(MountableGun gun, long lastShootTimeInMillis) {
        long deltaTime = TimeUtils.timeSinceMillis(lastShootTimeInMillis);
        long requiredTime = (long)(gun.getWaitingTimeInMillis()*(1+gun.getHeatLevel()*3)*(1+gun.getHeatLevel()*3));
        return deltaTime >= requiredTime;
    }

    public float getHeatLevelAfterShoot(MountableGun gun, float currentHeatLevel) {
        return (currentHeatLevel+gun.getOverheatGrowth() >= 1 ? 1 : currentHeatLevel+gun.getOverheatGrowth());
    }

    /**
     * decreases gun's heat level
     * @param gun gun
     * @param lastCoolingTimeInMillis last cooling time
     * @return heat level after cooling
     */
    public float getHeatLevelAfterCooling(MountableGun gun, long lastCoolingTimeInMillis) {
        long deltaTime = TimeUtils.timeSinceMillis(lastCoolingTimeInMillis);
        return (float)(gun.getHeatLevel()-(coolingPerSecond*deltaTime/1000));
    }
}

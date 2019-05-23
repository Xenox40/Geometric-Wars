package com.geometric.wars.scene;

import com.badlogic.gdx.utils.TimeUtils;
import com.geometric.wars.cube.mountables.MountableGun;

/**
 * singleton
 */
public class OverheatCalculator {
    private OverheatCalculator(){}

    /**
     * calculates if given gun can shoot including heat level and waiting time
     */
    public static boolean canShoot(MountableGun gun, long lastShootTimeInMillis) {
        long deltaTime = TimeUtils.timeSinceMillis(lastShootTimeInMillis);
        long requiredTime = (long)(gun.getWaitingTimeInMillis()*(1+gun.getHeatLevel()*1.2)*(1+gun.getHeatLevel()*1.2));
        return deltaTime >= requiredTime;
    }

    public static float getHeatLevelAfterShoot(MountableGun gun) {
        float newHeatLevel = (gun.getHeatLevel()+gun.getOverheatAbsoluteGrowth())*(1+gun.getOverheatRelativeGrowth());
        return (newHeatLevel >= 1 ? 1 :newHeatLevel);
    }

    /**
     * decreases gun's heat level
     * @param gun gun
     * @param deltaTime delta time in seconds between last frame
     * @return heat level after cooling
     */
    public static float getHeatLevelAfterCooling(MountableGun gun, double deltaTime) {
        double newHeatLevel =  gun.getHeatLevel() - (0.1*deltaTime);
        return newHeatLevel <= 0 ? 0 : (float)newHeatLevel;
    }
}

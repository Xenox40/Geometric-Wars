package com.geometric.wars.cube.mountables;

public abstract class MountableGun extends Mountable {
    protected float heatLevel;

    public abstract int getDamage();
    public abstract int getWaitingTimeInMillis();

    /**
     * @return bullet speed in blocks per seconds
     */
    public abstract float getBulletSpeed();

    /**
     * an overheat growth causes longer waiting time, see OverheatCalculator
     * @return absolute overheat growth per shoot bullet, value should be in range [0,1.0]
     */
    public abstract float getOverheatGrowth();


    public float getHeatLevel() {
        return heatLevel;
    }
    public void setHeatLevel(float heatLevel) {
        this.heatLevel = heatLevel;
    }
}

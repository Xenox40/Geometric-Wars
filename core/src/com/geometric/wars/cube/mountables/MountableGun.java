package com.geometric.wars.cube.mountables;

public abstract class MountableGun extends Mountable {
    private float heatLevel = 0;
    private int damage = 5;
    private float overheatRelativeGrowth = 0.06f;
    private float overheatAbsoluteGrowth = 0.08f;

    public abstract int getWaitingTimeInMillis();

    /**
     * @return bullet speed in blocks per seconds
     */
    public float getBulletSpeed() {return 20f;}

    /**
     * an overheat growth causes longer waiting time, see OverheatCalculator
     * @return absolute overheat growth per shoot bullet, value should be in range [0,1.0]
     */


    public float getOverheatRelativeGrowth() {return overheatRelativeGrowth; }
    public float getOverheatAbsoluteGrowth() {return overheatAbsoluteGrowth; }

    public float getHeatLevel() {
        return heatLevel;
    }
    public void setHeatLevel(float heatLevel) {
        this.heatLevel = heatLevel;
    }

    public int getDamage() {return damage;}
    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setOverheatGrowth(float relative, float absolute) {
        this.overheatRelativeGrowth = relative;
        this.overheatAbsoluteGrowth = absolute;
    }
}

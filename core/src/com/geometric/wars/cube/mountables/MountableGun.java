package com.geometric.wars.cube.mountables;

public abstract class MountableGun extends Mountable {
    public abstract int getDamage();
    public abstract int getWaitingTime();

    /**
     * @return bullet speed in blocks per seconds
     */
    public abstract float getBulletSpeed();
}

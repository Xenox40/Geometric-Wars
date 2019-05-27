package com.geometric.wars.powerups;

import com.badlogic.gdx.graphics.Color;
import com.geometric.wars.player.ShootingPlayersCube;

public class DamagePowerUp extends PowerUp {
    private float multiplier;

    /**
     * new damage = damage*multiplier
     * @param multiplier
     */
    public DamagePowerUp(float multiplier) {
        super();
        this.multiplier = multiplier;
        setColor(new Color(0.4f,0.6f,0.3f,1));
    }


    @Override
    protected void applyEffectTo(ShootingPlayersCube cube,EffectApplicator applicator) {
        applicator.addEffect(cube,10000, new Effect(){
            int prevDamage;

            @Override
            public void apply(ShootingPlayersCube cube) {
                prevDamage = cube.getGun().getDamage();
                cube.getGun().setDamage((int)(cube.getGun().getDamage()*multiplier));
            }

            @Override
            public void revert(ShootingPlayersCube cube) {
                cube.getGun().setDamage(prevDamage);
            }
        });
    }


}

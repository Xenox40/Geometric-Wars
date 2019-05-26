package com.geometric.wars.powerups;

import com.badlogic.gdx.graphics.Color;
import com.geometric.wars.player.ShootingPlayersCube;

public class DoubleDamagePowerUp extends PowerUp {
    public DoubleDamagePowerUp() {
        super();
        setColor(new Color(0.4f,0.6f,0.3f,0.4f));
    }


    @Override
    protected void applyEffectTo(ShootingPlayersCube cube) {
        EffectApplicator.getInstance().addEffect(cube,10000, new Effect(){
            @Override
            public void apply(ShootingPlayersCube cube) {
                cube.getGun().setDamage(cube.getGun().getDamage()*2);
            }

            @Override
            public void revert(ShootingPlayersCube cube) {
                cube.getGun().setDamage(cube.getGun().getDamage()/2);
            }
        });
    }


}

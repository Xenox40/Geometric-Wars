package com.geometric.wars.powerups;

import com.badlogic.gdx.graphics.Color;
import com.geometric.wars.player.ShootingPlayersCube;

public class DoubleDamagePowerUp extends PowerUp {
    public DoubleDamagePowerUp() {
        super();
        setColor(new Color(0.9f,0.1f,0.1f,1));
    }

    @Override
    public int getPower() {
        return 4;
    }

    @Override
    protected void applyEffectTo(ShootingPlayersCube cube) {
        EffectApplicator.getInstance().addEffect(cube,10000, new Effect(){
            @Override
            public void apply(ShootingPlayersCube cube) {
                cube.getGun().setDamage(cube.getGun().getDamage()*2);
                System.out.println("applied");
            }

            @Override
            public void revert(ShootingPlayersCube cube) {
                cube.getGun().setDamage(cube.getGun().getDamage()/2);
                System.out.println("deapplied");
            }
        });
    }


}

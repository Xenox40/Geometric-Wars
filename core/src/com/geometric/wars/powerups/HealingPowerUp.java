package com.geometric.wars.powerups;

import com.badlogic.gdx.graphics.Color;
import com.geometric.wars.player.ShootingPlayersCube;

public class HealingPowerUp extends PowerUp {
    public HealingPowerUp() {
        super();
        setColor(new Color(255/255f,214/255f,240/255f,1));
    }

    @Override
    protected void applyEffectTo(ShootingPlayersCube cube, EffectApplicator applicator) {
        applicator.addEffect(cube,1, new Effect(){
            @Override
            public void apply(ShootingPlayersCube cube) {
                cube.setHealthPoints(ShootingPlayersCube.startingHp);
            }

            @Override
            public void revert(ShootingPlayersCube cube) {}
        });
    }
}

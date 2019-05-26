package com.geometric.wars.powerups;

import com.badlogic.gdx.graphics.Color;
import com.geometric.wars.player.ShootingPlayersCube;

public class InvincibilityPowerUp extends PowerUp {
    private int duration;
    public InvincibilityPowerUp(int duration) {
        super();
        this.duration = duration;
        setColor(new Color(240/255f,240/255f,60/255f,1));
    }

    @Override
    protected void applyEffectTo(ShootingPlayersCube cube, EffectApplicator applicator) {
        applicator.addEffect(cube,duration, new Effect(){
            @Override
            public void apply(ShootingPlayersCube cube) {
                cube.isInvincible = true;
            }

            @Override
            public void revert(ShootingPlayersCube cube) {
                cube.isInvincible = false;
            }
        });
    }
}

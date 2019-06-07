package com.geometric.wars.powerups;

import com.badlogic.gdx.graphics.Color;
import com.geometric.wars.player.ShootingPlayersCube;

public class MovementSpeedPowerUp extends PowerUp {
    public MovementSpeedPowerUp() {
        super();
        setColor(new Color(67/255f,0/255f,237/255f,1));
    }

    @Override
    protected void applyEffectTo(ShootingPlayersCube cube, EffectApplicator applicator) {
        applicator.addEffect(cube,5000, new Effect(){
            @Override
            public void apply(ShootingPlayersCube cube) {
                cube.setMovementSpeed(0.15f);
            }

            @Override
            public void revert(ShootingPlayersCube cube) {cube.setMovementSpeed(0.25f);}
        });
    }
}

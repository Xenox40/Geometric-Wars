package com.geometric.wars.powerups;

import com.badlogic.gdx.graphics.Color;
import com.geometric.wars.player.ShootingPlayersCube;

public class NoOverHeatPowerUp extends PowerUp{
    public NoOverHeatPowerUp() {
        super();
        setColor(new Color(0.9f,0.1f,0.1f,1));
    }


    @Override
    protected void applyEffectTo(ShootingPlayersCube cube) {
        EffectApplicator.getInstance().addEffect(cube,2000, new Effect(){
            float absGrowth, relGrowth;
            @Override
            public void apply(ShootingPlayersCube cube) {
                cube.setGunHeatLevel(0);
                this.relGrowth = cube.getGun().getOverheatRelativeGrowth();
                this.absGrowth = cube.getGun().getOverheatAbsoluteGrowth();
                cube.getGun().setOverheatGrowth(0,0);
            }

            @Override
            public void revert(ShootingPlayersCube cube) {
                cube.getGun().setOverheatGrowth(relGrowth,absGrowth);
            }
        });
    }
}

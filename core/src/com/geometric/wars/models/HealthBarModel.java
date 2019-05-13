package com.geometric.wars.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;

public class HealthBarModel {
    private Texture texture;
    private NinePatch bar;

    public HealthBarModel() {
        texture = new Texture(Gdx.files.internal("healthBar.jpg"));
        bar = new NinePatch(texture, 0, 0, 0, 0);
    }

    public NinePatch getBar() { return bar; }

    public void dispose() {
        texture.dispose();
    }

}

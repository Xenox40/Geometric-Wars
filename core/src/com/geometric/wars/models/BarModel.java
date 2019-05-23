package com.geometric.wars.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;

public class BarModel {
    private Texture texture;
    private BarModel(){
        texture = new Texture(Gdx.files.internal("pixel.png"));
    }

    public NinePatch newBar(Color color) { return new NinePatch(texture,color); }


    private static BarModel instance;

    public static BarModel getInstance(){
        if(instance == null)
            instance = new BarModel();
        return instance;
    }



    public static void dispose() {
        if(instance != null) {
            instance.texture.dispose();
            instance = null;
        }

    }

}

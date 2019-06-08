package com.geometric.wars.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.geometric.wars.GeometricWars;

public class HowToPlayScreen extends AbstractMenuScreen {
    private int page = 0;
    private final Texture textures[] = {new Texture(Gdx.files.internal("howToPlay1.png")),new Texture(Gdx.files.internal("howToPlay2.png")), new Texture(Gdx.files.internal("howToPlay3.png"))};
    public static final String strings[] = {
            "Kill as many enemies as you can! Control the cube with the gun at one of its sides. Every move causes rotating of cube - your gun also rotates. " +
                    "Be careful - every time you shoot the bullet, your gun heats- overheated gun shots slower. " +
                    "Watch out your health and collect power ups to crush your enemies.",
            "You can play with bots (easy or medium) or with friends on the same device. Bots are randomly given a better gun - play safe with them. " +
            "Choose one of maps created by us or try random map generators (cellular or tunnels).",
            "You can check and change default controls in options. Good luck and have fun!"
    };
    public HowToPlayScreen(GeometricWars game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();

        Label label = new Label(strings[page],skin);
        label.setWrap(true);
        Button backButton = new TextButton("Back",skin);
        Button nextButton = new TextButton("Next",skin);
        backButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                page = 0;
                game.setScreen(game.mainMenuScreen);
            }
        });
        nextButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                page ++;
                if(page == textures.length)
                    page = 0;
                game.setScreen(game.howToPlayScreen);
            }
        });

        Table table = new Table();
        table.setFillParent(true);
        table.top();
        if(page == 1) {
            table.add(label).minSize(500, 300).colspan(2).expand();
            table.row();
            table.add(new Image(textures[page])).colspan(2).expand();
        }
        else{
            table.add(label).minSize(500, 300).expand();
            table.add(new Image(textures[page])).expand();
        }
        table.row();
        table.add(backButton).minSize(350,60).align(Align.center).expand();
        table.add(nextButton).minSize(350,60).align(Align.center).expand();
        table.row();

        stage.addActor(table);
    }

    @Override
    public void dispose() {
        super.dispose();
        for(Texture t : textures)
            t.dispose();
    }
}

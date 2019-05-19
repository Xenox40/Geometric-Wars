package com.geometric.wars.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.geometric.mapgenerators.*;
import com.geometric.wars.GeometricWars;
import com.geometric.wars.maps.GameMap;


public class CustomGameScreen extends AbstractMenuScreen{
    private static final float mapPreviewWidth = 600, mapPreviewHeight = 600;

    private int width = 13;
    private int height = 13;
    private float wallThreshold = .5f;

    MapBuilder builder = new MapBuilder();
    private GameMap map;

    private Array<Array<Image> > images;
    private Texture texture;

    public CustomGameScreen(GeometricWars game) {
        super(game);
        texture = new Texture(Gdx.files.internal("mapPreviewTile.png"));
    }

    @Override
    public void show() {
        super.show();
        generateMap();


        final Table table = new Table();
        table.setFillParent(true);
        table.top();
        TextButton playButton = new TextButton("Play!", skin);
        final Slider widthSlider = new Slider(5,25,1,false,skin);
        widthSlider.setValue(width);
        final Slider heightSlider = new Slider(5,25,1,false,skin);
        heightSlider.setValue(height);
        TextButton backButton = new TextButton("Back", skin);


        widthSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                width = (int)widthSlider.getValue();
                //show();
            }
        });

        heightSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                height = (int)heightSlider.getValue();
               // show();
            }
        });

        playButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(game.splashScreen);
                game.gameScreen.setMap(map);
                game.splashScreen.setNextScreen(game.gameScreen);
            }
        });
        backButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(game.mainMenuScreen);
            }
        });

        Table buttonsTable = new Table();
        buttonsTable.top();
        buttonsTable.add(playButton).minSize(300,60).expand();
        buttonsTable.row();
        buttonsTable.add(widthSlider).minSize(300,60);
        buttonsTable.row();
        buttonsTable.add(heightSlider).minSize(300,60);
        buttonsTable.row();
        buttonsTable.add(backButton).minSize(300,60).expand();
        buttonsTable.row();


        final Table imageTable = new Table();

        imageTable.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(game.customGameScreen);
            }
        });

        imageTable.top();

        int cellSize = (int)(mapPreviewWidth/map.getWidth() < mapPreviewHeight/map.getHeight() ? mapPreviewWidth/map.getWidth() : mapPreviewHeight/map.getHeight());

        for(int i=0;i<map.getHeight();i++) {
            for(int j=0;j<map.getWidth();j++)
                imageTable.add(images.get(i).get(j)).minSize(cellSize,cellSize);
            imageTable.row();
        }

        table.add(buttonsTable).minSize(200,600).expand();
        table.add(imageTable).minSize(mapPreviewWidth,mapPreviewHeight).expand();
        stage.addActor(table);
    }

    private void generateMap() {
        builder.setGenerator(new DefaultMapGenerator(15,wallThreshold)).setCompressor(new CuttingMapSizeCompressor(),3,3).setConnector(new DefaultMapConnector(5)).setPlayerPlacer(new CornerMapPlayerPlacer(4,3));
        map = builder.build(width,height);
        updatePreview();
    }

    private void updatePreview() {
        if(images == null)
            images = new Array<>();
        images.clear();
        for(int i=0;i<map.getHeight();i++){
            images.add(new Array<Image>());
            for(int j=0;j<map.getWidth();j++){
                images.get(i).add(new Image(texture));
                if (map.get(i, j) == '#')
                    images.get(i).get(j).setColor(Color.LIME);
                if (map.get(i, j) == 'P')
                    images.get(i).get(j).setColor(Color.RED);
                if (map.get(i, j) == 'B')
                    images.get(i).get(j).setColor(Color.BLUE);
                if (map.get(i, j) == '.')
                    images.get(i).get(j).setColor(Color.BLACK);
            }
        }

    }


    @Override
    public void dispose() {
        super.dispose();
        texture.dispose();
    }


}
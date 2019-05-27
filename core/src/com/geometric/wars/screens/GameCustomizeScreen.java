package com.geometric.wars.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.geometric.mapgenerators.*;
import com.geometric.wars.GeometricWars;
import com.geometric.wars.maps.GameMap;


public class GameCustomizeScreen extends AbstractMenuScreen{
    private static final float mapPreviewWidth = 600, mapPreviewHeight = 600;

    int width = 13;
    int height = 13;
    float wallThreshold = .5f;
    private int persons;
    private int bots;


    private boolean customizeMap = false;

    private PlayersSettingsTable playersSettingsTable  = new PlayersSettingsTable(this);
    private RandomMapSettingsTable randomMapSettingsTable =  new RandomMapSettingsTable(this);

    MapBuilder builder = new MapBuilder();
    private String template;
    String getMapTemplate() {return template;}
    void setBuilderTemplate(String template) {
        this.template = template;
        builder.clear();
        if(template.equals("Default")){
            builder.setGenerator(new StockMapPicker());
        }
        if(template.equals("Cellular")){
            builder
                    .setGenerator(new CellularMapGenerator(15,wallThreshold))
                    .setCompressor(new CuttingMapSizeCompressor(),3,3)
                    .setConnector(new DefaultMapConnector(5))
                    .setPlayerPlacer(new CornerMapPlayerPlacer(persons+bots,bots));
        }
        if(template.equals("Tunnels")) {
            builder
                    .setGenerator(new TunnelingMapGenerator(wallThreshold, (map.getWidth() < map.getHeight() ? map.getWidth()*3/4 : map.getHeight()*3/4 )))
                    .setPlayerPlacer(new CornerMapPlayerPlacer(persons+bots,bots));
        }
    }

    GameMap map;

    private Array<Array<Image> > images;
    Array<String> selectedPlayers;
    private Texture texture;

    public GameCustomizeScreen(GeometricWars game) {
        super(game);
        setBuilderTemplate("Default");
        texture = new Texture(Gdx.files.internal("pixel.png"));
    }


    @Override
    public void show() {
        super.show();

        if(selectedPlayers == null) {
            selectedPlayers = new Array<>();
            selectedPlayers.clear();
            selectedPlayers.add("Player", "Bot", "Bot", "Bot");
            persons = 1;
            bots = 3;
        }

        if(map == null)
            generateMap();
        else
            updatePreview();

        final Table table = new Table();
        table.setFillParent(true);
        table.top();



        TextButton playButton = new TextButton("Play!", skin);
        TextButton randomMapOptionsButton = new TextButton("Map options", skin);
        TextButton backButton = new TextButton("Back", skin);


        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.gameScreen.setMap(map);
                game.setScreen(game.gameScreen);
            }
        });

        randomMapOptionsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                customizeMap = true;
                game.setScreen(game.gameCustomizeScreen);
            }
        });

        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(customizeMap) {
                    customizeMap = false;
                    game.setScreen(game.gameCustomizeScreen);
                }
                else
                    game.setScreen(game.mainMenuScreen);
            }
        });

        Table buttonsTable = new Table();
        buttonsTable.top();

        if(customizeMap){
            randomMapSettingsTable.addTo(buttonsTable).minSize(300,400).expand();

        }
        else {

            buttonsTable.add(playButton).minSize(300,60).expand();
            buttonsTable.row();

            playersSettingsTable.addTo(buttonsTable).minSize(300,250).expand();
            buttonsTable.row();

            buttonsTable.add(randomMapOptionsButton).minSize(300,60).expand();
            buttonsTable.row();

        }
        buttonsTable.row();
        buttonsTable.add(backButton).minSize(300,60).expand();
        buttonsTable.row();


        final Table imageAndArrowTable = new Table();
        final Table imageTable = new Table();

        imageTable.top();
        int cellSize = (int)(mapPreviewWidth/map.getWidth() < mapPreviewHeight/map.getHeight() ? mapPreviewWidth/map.getWidth() : mapPreviewHeight/map.getHeight());

        for(int i=0;i<map.getHeight();i++) {
            for(int j=0;j<map.getWidth();j++)
                imageTable.add(images.get(i).get(j)).minSize(cellSize,cellSize);
            imageTable.row();
        }

        imageAndArrowTable.add(imageTable);
        imageAndArrowTable.row();
        TextButton nextMap = new TextButton("Next",skin);
        imageAndArrowTable.add(nextMap).minSize(mapPreviewHeight/2,40).padTop(20).align(Align.bottomRight).colspan(map.getWidth()).expand();

        nextMap.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                generateMap();
                game.setScreen(game.gameCustomizeScreen);
            }
        });

        table.add(buttonsTable).minSize(200,600).expand();
        table.add(imageAndArrowTable).minSize(mapPreviewWidth,mapPreviewHeight).expand();
        stage.addActor(table);
    }

    private void generateMap() {
        setBuilderTemplate(getMapTemplate());
        map = builder.build(width,height);
        updatePlayersOnMap();
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
                else if (map.get(i, j) == 'P')
                    images.get(i).get(j).setColor(Color.RED);
                else if (map.get(i, j) == 'B')
                    images.get(i).get(j).setColor(Color.BLUE);
                else
                    images.get(i).get(j).setColor(Color.BLACK);
            }
        }

    }

    public void updatePlayersOnMap() {
        bots = persons = 0;
        for(int i=0;i<selectedPlayers.size;i++) {
            if(selectedPlayers.get(i).equals("Player"))
                persons++;
            else if(selectedPlayers.get(i).equals("Bot"))
                bots++;
        }

        int ct = 0;
        for(int i=0; i < map.getHeight(); i++) {
            for (int j = 0; j < map.getWidth(); j++) {
                if (map.get(i, j) == 'P' || map.get(i,j) == 'B' || map.get(i,j) == 'N') {
                    if(selectedPlayers.get(ct).equals("Player"))
                         map.put(i, j, 'P');
                    else if(selectedPlayers.get(ct).equals("Bot"))
                        map.put(i, j, 'B');
                    else
                        map.put(i,j,'N'); //none player
                    ct++;
                }
            }
        }

        game.setScreen(this);
    }


    @Override
    public void dispose() {
        super.dispose();
        texture.dispose();
    }


}
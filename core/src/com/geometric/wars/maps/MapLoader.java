package com.geometric.wars.maps;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.geometric.wars.Values;
import com.geometric.wars.enviromentparts.Floor;
import com.geometric.wars.enviromentparts.Wall;
import com.geometric.wars.input.InputController;
import com.geometric.wars.player.ShooterPlayersController;
import com.geometric.wars.player.bots.randomactingbot.RandomBotFactory;
import com.geometric.wars.player.person.PersonsCubeFactory;
import com.geometric.wars.scene.Scene;
import com.geometric.wars.scene.SceneManager;

import java.io.BufferedReader;
import java.io.IOException;

public class MapLoader {
    private String fileName;
    private int width;
    private int height ;
    private InputController inputController;
    private Scene scene;

    public void load() {
        FileHandle handle;
        BufferedReader reader;

        MapService service = SceneManager
                .getInstance()
                .getCurrentMapService();

        Array<Array<MapObjectType>> mapObjects;
        mapObjects = new Array<>();

        int height = 0;
        int width = 0;
        try {
            handle = Gdx.files.internal(fileName);
            reader = handle.reader(15);
            String line = reader.readLine();
            width = line.length();
            int x = 0, y = 0;
            while(line != null) {
                mapObjects.add(new Array<MapObjectType>());

                ++height;

                if(line.length() != width)
                    throw new IOException("Wrong scene width in: "+fileName);

                for (char item: line.toCharArray()) {
                    switch (item) {
                        case '#':
                            scene.addStaticGameOject(new Wall(x, y));
                            mapObjects.get(y).add(MapObjectType.WALL);
                            break;
                        case 'P':
                            if(inputController == null)
                                throw new IOException("No inputController provided");
                            scene.addDynamicGameObject(new ShooterPlayersController(x, y, new PersonsCubeFactory(inputController)));
                            mapObjects.get(y).add(MapObjectType.PLAYER);
                            break;
                        case 'B':
                            scene.addDynamicGameObject(new ShooterPlayersController(x, y, new RandomBotFactory()));
                            mapObjects.get(y).add(MapObjectType.PLAYER);
                            break;
                        default:
                            mapObjects.get(y).add(MapObjectType.EMPTY);
                            break;
                    }
                    x += Values.unit;
                }

                line = reader.readLine();
                x = 0; y += Values.unit;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Floor floor = new Floor(0,0);
        floor.transform.translate((width-Values.unit)/2f,0,(height-Values.unit)/2f);
        floor.transform.scale((float)width,1f,(float)height);
        scene.addStaticGameOject(floor);



//        scene.staticModelsCache = new ModelCache();
//        scene.staticModelsCache.begin();
//        for(StaticGameObject o : scene.staticMapObjects)
//            o.cache(scene.staticModelsCache);
//        scene.staticModelsCache.end();

        service.setHeight(height);
        service.setWidth(width);
        service.setMapObjects(mapObjects);
    }

    public MapLoader setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public MapLoader setInputController(InputController inputController) {
        this.inputController = inputController;
        return this;
    }

    public MapLoader setScene(Scene scene) {
        this.scene = scene;
        return this;
    }
}

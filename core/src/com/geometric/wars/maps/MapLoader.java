package com.geometric.wars.maps;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g3d.ModelCache;
import com.badlogic.gdx.utils.Array;
import com.geometric.wars.Values;
import com.geometric.wars.enviromentparts.Floor;
import com.geometric.wars.enviromentparts.Wall;
import com.geometric.wars.input.InputController;
import com.geometric.wars.player.ShooterPlayersController;
import com.geometric.wars.player.bots.randomactingbot.RandomBotFactory;
import com.geometric.wars.player.person.PersonsCubeFactory;

import java.io.BufferedReader;
import java.io.IOException;

public class MapLoader {
    private String fileName;
    private int width;
    private int height ;
    private InputController inputController;

    public Map load() {
        FileHandle handle;
        BufferedReader reader;

        Map map = new Map();
        MapService service = MapService.getInstance();
        service.setMap(map);

        int height = 0;
        int width = 0;
        try {
            handle = Gdx.files.internal(fileName);
            reader = handle.reader(15);
            String line = reader.readLine();
            width = line.length();
            int x = 0, y = 0;
            while(line != null) {
                service.mapObjects.add(new Array<MapObjectType>());

                ++height;

                if(line.length() != width)
                    throw new IOException("Wrong map width in: "+fileName);

                for (char item: line.toCharArray()) {
                    switch (item) {
                        case '#':
                            map.staticMapObjects.add(new Wall(x, y));
                            service.mapObjects.get(y).add(MapObjectType.WALL);
                            break;
                        case 'P':
                            if(inputController == null)
                                throw new IOException("No inputController provided");
                            map.dynamicMapObjects.add(new ShooterPlayersController(x, y, new PersonsCubeFactory(inputController)));
                            service.mapObjects.get(y).add(MapObjectType.PLAYER);
                            break;
                        case 'B':
                            map.dynamicMapObjects.add(new ShooterPlayersController(x, y, new RandomBotFactory()));
                            service.mapObjects.get(y).add(MapObjectType.PLAYER);
                            break;
                        default:
                            service.mapObjects.get(y).add(MapObjectType.EMPTY);
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
        map.staticMapObjects.add(floor);


        map.staticModelsCache = new ModelCache();
        map.staticModelsCache.begin();
        for(StaticMapObject o : map.staticMapObjects)
            o.cache(map.staticModelsCache);
        map.staticModelsCache.end();

        map.width = width;
        map.height = height;

        return map;
    }

    public MapLoader setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }


    public MapLoader setInputController(InputController inputController) {
        this.inputController = inputController;
        return this;
    }


}

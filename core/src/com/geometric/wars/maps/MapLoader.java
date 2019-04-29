package com.geometric.wars.maps;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
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
    private int width = 13;
    private int height = 13;
    private InputController inputController;

    public Map load() {
        FileHandle handle;
        BufferedReader reader;
        Map map = new Map();
        map.width = width;
        map.height = height;
        map.occupied = new boolean[width][height];
        MapObjectCheckerService service = new MapObjectCheckerService(map);
        try {
            handle = Gdx.files.internal(fileName);
            reader = handle.reader(15);
            String line = reader.readLine();
            int lineCounter = 0;
            int x = 0, y = 0;
            while(line != null) {
                ++lineCounter;
                if(line.length() != width)
                    throw new IOException("Wrong map width in: "+fileName);

                for (char item: line.toCharArray()) {
                    map.staticMapObjects.add(new Floor(x, y));
                    switch (item) {
                        case '#':
                            map.staticMapObjects.add(new Wall(x, y));
                            map.occupied[x][y] = true;
                            break;
                        case 'P':
                            if(inputController == null)
                                throw new IOException("No inputController provided");
                            map.dynamicMapObjects.add(new ShooterPlayersController(x, y, new PersonsCubeFactory(service, inputController)));
                            break;
                        case 'B':
                            map.dynamicMapObjects.add(new ShooterPlayersController(x, y, new RandomBotFactory(service)));
                            break;
                    }
                    x += Values.unit;
                }

                line = reader.readLine();
                x = 0; y += Values.unit;
            }
            if(lineCounter != height)
                throw new IOException("Wrong map height in: "+fileName);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    public MapLoader setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public MapLoader setWidth(int width) {
        this.width = width;
        return this;
    }

    public MapLoader setHeight(int height) {
        this.height = height;
        return this;
    }

    public MapLoader setInputController(InputController inputController) {
        this.inputController = inputController;
        return this;
    }


}

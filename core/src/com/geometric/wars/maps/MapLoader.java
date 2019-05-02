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

import java.io.IOException;
import java.util.Scanner;

public class MapLoader {
    private String fileName;
    private InputController inputController;
    private Scene scene;

    public void load() {
        FileHandle handle;
        Scanner scanner;

        MapService service = SceneManager
                .getInstance()
                .getCurrentMapService();

        Array<Array<MapObjectType>> mapObjects;
        mapObjects = new Array<>();

        int height = 0;
        int width = -1;
        try {
            handle = Gdx.files.internal(fileName);
            scanner = new Scanner(handle.reader(15));
            int row = 0;

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Array<MapObjectType> objects = new Array<>();
                if (width == -1)
                    width = line.length();
                if(line.length() != width)
                    throw new IOException("Wrong scene width in: "+fileName);

                addObjectsToMap(line, row, objects);

                height++;
                row ++;
                mapObjects.add(objects);
            }
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        addFloor(height, width);


        service.setHeight(height);
        service.setWidth(width);
        service.setMapObjects(mapObjects);
    }

    private void addObjectsToMap(String line, int row, Array<MapObjectType> objects) throws IOException {
        int col = 0;
        for (char item: line.toCharArray()) {
            switch (item) {
                case '#':
                    addWall(objects, col, row);
                    break;
                case 'P':
                    addPlayersController(objects, col, row);
                    break;
                case 'B':
                    addRandomBot(objects, col, row);
                    break;
                default:
                    addFloorObject(objects, col, row);
                    break;
            }
            col ++;
        }
    }

    private void addFloor(int height, int width) {
        Floor floor = new Floor(0,0);
        floor.transform.translate((width- Values.unit)/2f,0,(height-Values.unit)/2f);
        floor.transform.scale((float)width,1f,(float)height);
        scene.addStaticGameOject(floor);
    }

    private void addWall(Array<MapObjectType> objects, int x, int y) {
        x *= Values.unit;
        y *= Values.unit;
        scene.addStaticGameOject(new Wall(x, y));
        objects.add(MapObjectType.WALL);
    }

    private void addPlayersController(Array<MapObjectType> objects, int x, int y) throws IOException {
        x *= Values.unit;
        y *= Values.unit;
        if(inputController == null)
            throw new IOException("No inputController provided");
        scene.addDynamicGameObject(new ShooterPlayersController(x, y, new PersonsCubeFactory(inputController)));
        objects.add(MapObjectType.PLAYER);
    }

    private void addRandomBot(Array<MapObjectType> objects, int x, int y) {
        x *= Values.unit;
        y *= Values.unit;
        scene.addDynamicGameObject(new ShooterPlayersController(x, y, new RandomBotFactory()));
        objects.add(MapObjectType.PLAYER);
    }

    private void addFloorObject(Array<MapObjectType> objects, int x, int y) {
        x *= Values.unit;
        y *= Values.unit;
        objects.add(MapObjectType.EMPTY);
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

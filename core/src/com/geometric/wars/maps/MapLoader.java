package com.geometric.wars.maps;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.geometric.wars.collisions.Collidable;
import com.geometric.wars.gameobjects.enviromentparts.Floor;
import com.geometric.wars.gameobjects.enviromentparts.Wall;
import com.geometric.wars.input.InputController;
import com.geometric.wars.input.InputMethodGetter;
import com.geometric.wars.player.PlayersController;
import com.geometric.wars.player.ShooterPlayersController;
import com.geometric.wars.player.bots.mediumbot.MediumBotFactory;
import com.geometric.wars.player.bots.randomactingbot.RandomBotFactory;
import com.geometric.wars.player.persons.shooting.ShootingPersonsCubeFactory;
import com.geometric.wars.scene.Scene;
import com.geometric.wars.scene.SceneManager;
import com.geometric.wars.utils.Values;

import java.io.IOException;
import java.util.Scanner;

public class MapLoader {
    private String fileName;
    private Scene scene;
    private int personsCount = 0;
    public void load(GameMap map) {
        MapService service  = SceneManager
                .getInstance()
                .getCurrentMapService();

        Array<Array<Collidable>> collidables;
        collidables = new Array<>();

        personsCount = 0;
        int height = 0;
        int width = -1;
        try {
            int row = 0;
            for (int i = 0; i < map.getHeight(); i++) {
                String line = map.getRow(i);
                Array<Collidable> objects = new Array<>();
                if (width == -1)
                    width = line.length();

                addObjectsToMap(line, row, objects);

                height++;
                row ++;
                collidables.add(objects);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        addFloor(height, width);

        service.setHeight(height);
        service.setWidth(width);
        service.addCollidables(collidables);

    }

    void addObjectsToMap(String line, int row, Array<Collidable> objects) throws IOException {
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

    void addFloor(int height, int width) {
        Floor floor = new Floor(0,0);
        floor.transform.translate((width- Values.unit)/2f,0,(height-Values.unit)/2f);
        floor.transform.scale((float)width,1f,(float)height);
        scene.addStaticGameObject(floor);
    }

    private void addWall(Array<Collidable> objects, int x, int y) {
        x *= Values.unit;
        y *= Values.unit;
        Wall wall = new Wall(x,y);
        scene.addStaticGameObject(wall);
        objects.add(wall);
    }

    private void addPlayersController(Array<Collidable> objects, int x, int y) throws IOException {
        x *= Values.unit;
        y *= Values.unit;
        //TODO throw exception if not enough distinct controllers
        PlayersController controller = new ShooterPlayersController(x, y, new ShootingPersonsCubeFactory(InputMethodGetter.getInstance().getInputMethod(personsCount++)));
        scene.addDynamicGameObject(controller);
        objects.add(controller.getCube(0));
    }

    private void addRandomBot(Array<Collidable> objects, int x, int y) {
        x *= Values.unit;
        y *= Values.unit;
        PlayersController controller = new ShooterPlayersController(x, y, new MediumBotFactory());
        scene.addDynamicGameObject(controller);
        SceneManager.getInstance().getCurrentMapService();
        objects.add(controller.getCube(0));
    }

    private void addFloorObject(Array<Collidable> objects, int x, int y) {
        x *= Values.unit;
        y *= Values.unit;
        objects.add(null);
    }

    public MapLoader setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }


    public MapLoader setScene(Scene scene) {
        this.scene = scene;
        return this;
    }
}

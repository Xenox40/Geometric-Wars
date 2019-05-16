package com.geometric.wars.maps;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class GameMap implements Serializable {

    /**
     * @param width map width
     * @param height map height
     */
    public GameMap(int width, int height) {
        this.width = width;
        this.height = height;
        map = new char[height][width];
    }
    public GameMap(String simpleNameWithoutExt) {
        loadFromFile(simpleNameWithoutExt);
    }


    private final char wall = '#', empty = '.', person = 'P', bot = 'B';
    private int width;
    private int height;
    private char[][] map;

    public void setTo(GameMap other) {
        this.width = other.width;
        this.height = other.height;
        map = new char[height][width];
        for(int i=0;i<height;i++) {
            for(int j=0;j<width;j++)
                map[i][j] = other.map[i][j];
        }
    }

    public static class WeightedPoint{
        public int x,y;
        public int weight;
        public WeightedPoint(int x,int y, int weight){
            this.x = x;
            this.y = y;
            this.weight = weight;
        }
    }



    public char get(int i, int j) {
        return map[i][j];
    }
    public String getRow(int i) {
        return new String(map[i]);
    }
    public void put(int i,int j, char c) {
        map[i][j] = c;
    }

    public boolean isWall(int i, int j) {
        return get(i,j) == wall;
    }
    public boolean isEmpty(int i,int j) {
        return get(i,j) == empty;
    }



    public void putWall(int i,int j) {
        put(i,j,wall);
    }
    public void putEmpty(int i,int j) {
        put(i,j,empty);
    }
    public void putBot(int i, int j) {
        put(i,j,bot);
    }
    public void putPerson(int i, int j) {
        put(i,j,person);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isIn(int i, int j){
        return i >= 0 && j >= 0 && i < getHeight() && j < getWidth();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                builder.append(map[i][j]);
            }
            builder.append('\n');
        }
        return builder.toString();
    }

    public void loadFromFile(String simpleNameWithoutExt) {
        FileHandle handle;
        Scanner scanner;

        ArrayList<String> lines = new ArrayList<>();
        int lineWidth = -1;
        try {
            handle = Gdx.files.internal("maps/"+simpleNameWithoutExt+".txt");
            scanner = new Scanner(handle.reader(15));

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (lineWidth == -1)
                    lineWidth = line.length();
                if(line.length() != lineWidth)
                    throw new IOException("Wrong scene width in: "+simpleNameWithoutExt);

                lines.add(line);
            }
            scanner.close();

            this.width = lineWidth;
            this.height = lines.size();
            this.map = new char[height][width];
            for(int i=0;i<lines.size();i++)
                for(int j=0;j<lines.get(i).length();j++)
                    map[i][j] = lines.get(i).charAt(j);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveAs(String simpleNameWithoutExt, boolean overwrite) {
        try {
            File file = new File("android/assets/maps/" + simpleNameWithoutExt + ".txt");
            if(file.createNewFile() || overwrite) {
                FileOutputStream outputStream = new FileOutputStream(file, false);
                outputStream.write(this.toString().getBytes());
                outputStream.close();
            }
            else {
                System.out.println("map with given name already exists, cancelling saveToFile\n");
                return;
            }

        }
        catch (IOException e){
            System.out.println(e);
        }
    }
    public void saveAs(String simpleNameWithoutExt){
        saveAs(simpleNameWithoutExt,false);
    }


}

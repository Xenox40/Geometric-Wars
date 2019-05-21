package com.geometric.wars.screens;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.geometric.wars.GeometricWars;
import com.geometric.wars.input.InputMethodGetter;
import com.geometric.wars.input.KeyboardInputController;


public class ControlPickScreen extends AbstractMenuScreen {
    public ControlPickScreen(GeometricWars game) {
        super(game);
    }

    private static final String[] keyNamesInPrefs = {".input.up",".input.down",".input.left",".input.right",".input.shoot"};
    private String createKeyName(int playerId, int keyId){
        return "player"+playerId+keyNamesInPrefs[keyId];
    }
    private static final int[][] defaultKeys = {
            {Input.Keys.UP,Input.Keys.DOWN,Input.Keys.LEFT, Input.Keys.RIGHT, Input.Keys.SPACE},
            {Input.Keys.W,Input.Keys.S,Input.Keys.A, Input.Keys.D, Input.Keys.F},
            {Input.Keys.I,Input.Keys.K,Input.Keys.J, Input.Keys.L, Input.Keys.SEMICOLON},
            {Input.Keys.NUM_8,Input.Keys.NUM_5,Input.Keys.NUM_4, Input.Keys.NUM_6, Input.Keys.PLUS},
    };


    private Picker currentlyPicked;
    private class Picker{
        final TextButton button;
        final Label label;
        String keyNameInPrefs;
        int key;
        Picker(String labelName, String keyNameInPrefs) {
            this.keyNameInPrefs = keyNameInPrefs;

            label = new Label(labelName,skin);
            label.setAlignment(Align.center,Align.center);

            button = new TextButton("",skin);
            reset();
            button.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    if(currentlyPicked != null)
                        currentlyPicked.reset();
                    button.setText("?");
                    currentlyPicked  = Picker.this;
                }
            });
        }

        void reset() {
            setKey(game.prefs.getInteger(keyNameInPrefs));
        }

        void setKey(int key){
            this.key = key;
            button.setText(Input.Keys.toString(key));
        }

        void save() {
            game.prefs.putInteger(keyNameInPrefs,key);
        }
    }


    private Cell<TextButton> addPicker(final Table table,final String name, String nameInPrefs) {
        Picker picker = new Picker(name,nameInPrefs);
        table.add(picker.label).minSize(200,100).expand();
        return table.add(picker.button);
    }

    private void resetPrefsToDefault(boolean overwrite) {
        for(int playerId=0; playerId < defaultKeys.length; playerId++) {
            for(int i=0;i<defaultKeys[playerId].length;i++){
                if (!game.prefs.contains(createKeyName(playerId,i)) || overwrite)
                    game.prefs.putInteger(createKeyName(playerId,i), defaultKeys[playerId][i]);
            }
        }
    }

    public void setSettingsToDefaultIfNotPresent() {
        resetPrefsToDefault(false);
        saveSettings();
    }

    private void saveSettings() {
        game.prefs.flush();
        InputMethodGetter.dispose();
        for(int playerId=0; playerId < defaultKeys.length; playerId++) {
            int[] keys = new int[defaultKeys[0].length];
            for (int keyId = 0; keyId < defaultKeys[playerId].length; keyId++)
                keys[keyId] = game.prefs.getInteger(createKeyName(playerId, keyId));
            InputMethodGetter.getInstance().addInputMethod(new KeyboardInputController(keys[0],keys[1],keys[2],keys[3],keys[4]));
        }

    }


    @Override
    public void show() {
        super.show();
        resetPrefsToDefault(false);
        Table table = new Table();
        table.setFillParent(true);
        table.top();

        Table keysTable = new Table();
        Table[] playersKeysTable = new Table[defaultKeys.length];
        for(int i=0;i<defaultKeys.length;i++)
            playersKeysTable[i] = new Table();

        final String[] keyNames = {"Up","Down","Left","Right","Shoot"};

        for(int playerId=0;playerId<defaultKeys.length;playerId++) {
            Label label = new Label("Player "+(playerId+1),skin);
            label.setFontScale(1.75f);
            label.setAlignment(Align.center,Align.center);
            playersKeysTable[playerId].add(label).minSize(200,80).colspan(2).expand();
            playersKeysTable[playerId].row();
            for (int i = 0; i < defaultKeys[playerId].length; i++) {
                addPicker(playersKeysTable[playerId], keyNames[i], createKeyName(playerId,i)).minSize(100, 100).expand();
                playersKeysTable[playerId].row();
            }
            if(playerId % 2 == 0)
                keysTable.add(playersKeysTable[playerId]).pad(50,50,50,300);
            else{
                keysTable.add(playersKeysTable[playerId]).pad(50,50,50,200);
                keysTable.row();
            }
        }



        ScrollPane scrollPane = new ScrollPane(keysTable,skin);

        table.add(scrollPane).fill();
        table.row();

        Button backButton = new TextButton("Back",skin);
        backButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                saveSettings();
                game.setScreen(game.optionsScreen);
            }
        });
        table.add(backButton).minSize(500,60).pad(50).expand();


        table.addListener(new InputListener(){
            @Override
            public boolean keyDown(InputEvent event, int keycode)
            {
               if(currentlyPicked != null) {
                   currentlyPicked.setKey(keycode);
                   currentlyPicked.save();
                   currentlyPicked = null;
                   return true;
               }
               return false;
            }
        });

        stage.addActor(table);
        stage.setKeyboardFocus(table);
    }
}

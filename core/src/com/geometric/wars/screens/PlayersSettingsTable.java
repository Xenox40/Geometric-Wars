package com.geometric.wars.screens;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class PlayersSettingsTable {
    private CustomGameScreen screen;

    PlayersSettingsTable(CustomGameScreen screen){
        this.screen = screen;
    }

    public Cell<Table> addTo(Table outerTable) {
        final Table playersTable = new Table();
        playersTable.top();
        for (int i = 0; i < 4; i++) {
            final SelectBox<String> box = new SelectBox<>(screen.skin);
            box.setItems("Player", "Bot");
            box.setSelected(screen.selectedPlayers.get(i));

            final int j = i;
            box.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    screen.selectedPlayers.set(j, box.getSelected());
                    screen.bots = screen.persons = 0;
                    for(int i=0;i<screen.selectedPlayers.size;i++) {
                        if(screen.selectedPlayers.get(i).equals("Player"))
                            screen.persons++;
                        else
                            screen.bots++;
                    }
                }
            });
            playersTable.add(box).minSize(200,40).expand();
            playersTable.row();
        }
        return outerTable.add(playersTable);
    }

}

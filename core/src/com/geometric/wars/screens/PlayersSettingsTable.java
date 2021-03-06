package com.geometric.wars.screens;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Array;

public class PlayersSettingsTable {
    private GameCustomizeScreen screen;

    PlayersSettingsTable(GameCustomizeScreen screen){
        this.screen = screen;
    }

    Array<SelectBox<String>> boxes = new Array<>();

    public Cell<Table> addTo(Table outerTable) {
        final Table playersTable = new Table();
        playersTable.top();
        for (int i = 0; i < 4; i++) {
            final SelectBox<String> box = new SelectBox<>(screen.skin);
            box.setItems("Player", "Easy Bot","Medium Bot","None");
            box.setSelected(screen.selectedPlayers.get(i));
            boxes.add(box);

            final int j = i;
            box.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    screen.selectedPlayers.set(j, box.getSelected());
                    screen.updatePlayersOnMap();
                }

            });
            playersTable.add(box).minSize(200,40).expand();
            playersTable.row();
        }
        return outerTable.add(playersTable);
    }

}

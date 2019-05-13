package com.geometric.wars;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.badlogic.gdx.backends.android.AndroidFragmentApplication;
import com.geometric.mapgenerators.CornerMapPlayerPlacer;
import com.geometric.mapgenerators.DefaultMapGenerator;
import com.geometric.mapgenerators.GameMap;

public class GameFragment extends AndroidFragmentApplication {

    private GeometricWars game;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (getArguments() != null && getArguments().containsKey("MAP"))
            game = new GeometricWars((GameMap) getArguments().getSerializable("MAP"));
        else
            game = new GeometricWars();

        return initializeForView(game);
    }

    GeometricWars getGame() {
        return game;
    }
}

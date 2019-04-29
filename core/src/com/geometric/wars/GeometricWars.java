package com.geometric.wars;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.geometric.wars.input.ArrowInputController;
import com.geometric.wars.input.InputController;
import com.geometric.wars.input.swipe.SwipeInputController;
import com.geometric.wars.models.CubeModel;
import com.geometric.wars.models.FloorModel;
import com.geometric.wars.models.WallModel;
import com.geometric.wars.screens.GameScreen;


public class GeometricWars extends ApplicationAdapter {
	private GameScreen gameScreen;

    private InputController inputController;

	@Override
	public void create () {
        if (Gdx.app.getType() == Application.ApplicationType.Android)
            inputController = SwipeInputController.getInstance();
        else
            inputController = ArrowInputController.getInstance();

		gameScreen = new GameScreen(this, "map1.txt");
	}

	@Override
	public void render () {
		gameScreen.render(1/30);
	}
	
	@Override
	public void dispose () {
		CubeModel.getModel().dispose();
		FloorModel.getModel().dispose();
		WallModel.getModel().dispose();
	}

    public InputController getInputController() {
        return inputController;
    }
}

package com.geometric.wars;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.geometric.mapgenerators.GameMap;
import com.geometric.wars.input.ArrowInputController;
import com.geometric.wars.input.InputController;
import com.geometric.wars.input.swipe.SwipeInputController;
import com.geometric.wars.models.*;
import com.geometric.wars.screens.GameScreen;
import com.geometric.wars.screens.SplashScreen;

public class GeometricWars extends ApplicationAdapter {
	private SplashScreen splashScreen;
	private GameScreen gameScreen;
    private InputController inputController;
    private String mapName;
    private GameMap map;
    private float gameTime;

    public GeometricWars() {
		mapName = "map2.txt";
		gameTime = 0f;
	}

	public GeometricWars(String mapName) {
		this.mapName = mapName;
		this.gameTime = 0f;
	}

	public GeometricWars(GameMap map) {
    	this.map = map;
        this.gameTime = 0f;
	}

	@Override
	public void create() {
		if (Gdx.app.getType() == Application.ApplicationType.Android)
			inputController = SwipeInputController.getInstance();
		else
			inputController = ArrowInputController.getInstance();

		splashScreen = new SplashScreen(this);
        if (map != null) {
        	gameScreen = new GameScreen(this, map);
		}
        else {
			gameScreen = new GameScreen(this, mapName);
		}
	}

	@Override
	public void render() {
		if (gameTime < 2f) {
			gameTime += Gdx.graphics.getDeltaTime();
			splashScreen.render(1 / 30f);
		} else
			gameScreen.render(1 / 30f);
	}

	@Override
	public void resize(int x, int y) {
		gameScreen.resize(x, y);
	}

	@Override
	public void dispose() {
		DynamicCubeModelDisposer.dispose();
		FloorModel.dispose();
		WallModel.dispose();
		LineModel.dispose();
		BulletModel.dispose();
		inputController.dispose();
	}

	public InputController getInputController() {
		return inputController;
	}
}

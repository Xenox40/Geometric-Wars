package com.geometric.wars;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.geometric.wars.maps.GameMap;
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
    private GameMap map;
    private String defaultMap = "map2";
    private float gameTime = 0f;


    public GeometricWars() {}

	public GeometricWars(GameMap map) {
		this.map = map;
	}

	@Override
	public void create() {
    	if(map == null)
    		map = new GameMap(defaultMap);
		if (isAndroidPlatform())
			inputController = SwipeInputController.getInstance();
		else
			inputController = ArrowInputController.getInstance();

		splashScreen = new SplashScreen(this);
        if (map != null) {
        	gameScreen = new GameScreen(this, map);
		}
        else{
        	throw new RuntimeException("map not set");
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
    	gameScreen.dispose();
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

	public boolean isAndroidPlatform() {
    	return Gdx.app.getType() == Application.ApplicationType.Android;
	}

}

package com.geometric.wars;

import com.badlogic.gdx.*;
import com.geometric.wars.input.InputMethodGetter;
import com.geometric.wars.maps.GameMap;
import com.geometric.wars.input.KeyboardInputController;
import com.geometric.wars.input.InputController;
import com.geometric.wars.input.swipe.SwipeInputController;
import com.geometric.wars.models.*;
import com.geometric.wars.screens.CustomGameScreen;
import com.geometric.wars.screens.GameScreen;
import com.geometric.wars.screens.MainMenuScreen;
import com.geometric.wars.screens.SplashScreen;


public class GeometricWars extends Game{

	public MainMenuScreen mainMenuScreen;
	public SplashScreen splashScreen;
	public GameScreen gameScreen;
	public CustomGameScreen customGameScreen;


    private GameMap map;
    private String defaultMap = "map2";


    public GeometricWars() {}

	public GeometricWars(GameMap map) {
		this.map = map;
	}

	@Override
	public void create() {
    	if(map == null)
    		map = new GameMap(defaultMap);
		if (isAndroidPlatform())
			addInputController(SwipeInputController.getInstance());
		else {
			addInputController(new KeyboardInputController(Input.Keys.UP, Input.Keys.DOWN, Input.Keys.RIGHT, Input.Keys.LEFT, Input.Keys.SPACE));
			addInputController(new KeyboardInputController(Input.Keys.W, Input.Keys.S, Input.Keys.D, Input.Keys.A, Input.Keys.G));
		}

        if (map != null) {
        	gameScreen = new GameScreen(this);
        	gameScreen.setMap(map);
		}
        else{
        	throw new RuntimeException("map not set");
		}
		if(!isAndroidPlatform()) {
			mainMenuScreen = new MainMenuScreen(this);
			splashScreen = new SplashScreen(this);
			customGameScreen = new CustomGameScreen(this);
			splashScreen.setNextScreen(mainMenuScreen);
			setScreen(splashScreen);
		}
		else
			setScreen(gameScreen);
	}

	private void addInputController(InputController inputController) {
    	InputMethodGetter.getInstance().addInputMethod(inputController);
	}

	@Override
	public void render() {
		getScreen().render(1/30f);
    }

	@Override
	public void resize(int x, int y) {
		getScreen().resize(x, y);
	}

	@Override
	public void dispose() {
    	if(gameScreen != null)
    		gameScreen.dispose();
    	if(mainMenuScreen != null)
    		mainMenuScreen.dispose();
    	if(splashScreen != null)
    		splashScreen.dispose();
    	if(customGameScreen != null)
    		customGameScreen.dispose();

		DynamicCubeModelDisposer.dispose();
		FloorModel.dispose();
		WallModel.dispose();
		LineModel.dispose();
		BulletModel.dispose();
		InputMethodGetter.dispose();
	}

	public boolean isAndroidPlatform() {
    	return Gdx.app.getType() == Application.ApplicationType.Android;
	}

}

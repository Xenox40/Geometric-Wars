package com.geometric.wars;

import com.badlogic.gdx.*;
import com.geometric.wars.input.InputMethodGetter;
import com.geometric.wars.maps.GameMap;
import com.geometric.wars.input.InputController;
import com.geometric.wars.input.swipe.SwipeInputController;
import com.geometric.wars.models.*;
import com.geometric.wars.utils.GameResourceDisposer;
import com.geometric.wars.screens.*;


public class GeometricWars extends Game{

	public MainMenuScreen mainMenuScreen;
	public SplashScreen splashScreen;
	public GameScreen gameScreen;
	public GameCustomizeScreen gameCustomizeScreen;
	public OptionsScreen optionsScreen;
	public ControlPickScreen controlPickScreen;

	public Preferences prefs;

    private GameMap map;


    public GeometricWars() {}

	public GeometricWars(GameMap map) {
		this.map = map;
	}

	@Override
	public void create() {
		prefs = Gdx.app.getPreferences("com.geometric.wars.config");

		if (isAndroidPlatform()) {
			addInputController(SwipeInputController.getInstance());
			if (map == null)
				map = new GameMap("map2");
		}

		gameScreen = new GameScreen(this);

		if(isAndroidPlatform()){
			gameScreen.setMap(map);
			setScreen(gameScreen);
		}


		if(!isAndroidPlatform()) {
			mainMenuScreen = new MainMenuScreen(this);
			splashScreen = new SplashScreen(this);
			splashScreen.setNextScreen(mainMenuScreen);

			gameCustomizeScreen = new GameCustomizeScreen(this);
			optionsScreen = new OptionsScreen(this);
			controlPickScreen = new ControlPickScreen(this);
			controlPickScreen.setSettingsToDefaultIfNotPresent();
			setScreen(splashScreen);
		}
		else {

		}
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
    	if(gameCustomizeScreen != null)
    		gameCustomizeScreen.dispose();

		GameResourceDisposer.dispose();
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

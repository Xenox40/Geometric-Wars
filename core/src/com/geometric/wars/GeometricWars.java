package com.geometric.wars;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.geometric.wars.input.ArrowInputController;
import com.geometric.wars.input.InputController;
import com.geometric.wars.input.swipe.SwipeInputController;
import com.geometric.wars.models.CubeModel;
import com.geometric.wars.models.FloorModel;
import com.geometric.wars.models.LineModel;
import com.geometric.wars.models.WallModel;
import com.geometric.wars.screens.GameScreen;


public class GeometricWars extends ApplicationAdapter {
	private GameScreen gameScreen;
    private InputController inputController;
    private TypeOfGame typeOfGame;
    private String mapName;

    GeometricWars() {
    	typeOfGame = TypeOfGame.MOCK_GAME;
		mapName = "map1.txt";
	}

	public GeometricWars(TypeOfGame typeOfGame, String mapName) {
		this.typeOfGame = typeOfGame;
		this.mapName = mapName;
	}

	@Override
	public void create () {
        if (Gdx.app.getType() == Application.ApplicationType.Android)
            inputController = SwipeInputController.getInstance();
        else
			inputController = ArrowInputController.getInstance();

		gameScreen = new GameScreen(this, mapName, typeOfGame);
	}

	@Override
	public void render () {
		gameScreen.render(1/30f);
	}

	@Override
	public void resize(int x,int y){
		gameScreen.resize(x,y);
	}

	@Override
	public void dispose () {
		CubeModel.dispose();
		FloorModel.dispose();
		WallModel.dispose();
		LineModel.dispose();
		inputController.dispose();
	}

    public InputController getInputController() {
        return inputController;
    }
}

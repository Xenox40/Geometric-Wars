package com.geometric.wars;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.geometric.wars.enviromentparts.Floor;
import com.geometric.wars.input.ArrowInputController;
import com.geometric.wars.input.InputController;
import com.geometric.wars.input.swipe.SwipeInputController;
import com.geometric.wars.models.CubeModel;
import com.geometric.wars.models.FloorModel;
import com.geometric.wars.models.WallModel;
import com.geometric.wars.player.ShooterPlayersController;
import com.geometric.wars.player.person.PersonsCubeFactory;
import com.geometric.wars.screens.GameScreen;


public class GeometricWars extends ApplicationAdapter {
	//TODO getters
	public ShooterPlayersController player;
	public Floor floor1;
	public Floor floor2;
	private GameScreen gameScreen;

	private InputController inputController;

	@Override
	public void create () {
		gameScreen = new GameScreen(this);

		if (Gdx.app.getType() == Application.ApplicationType.Android)
			inputController = SwipeInputController.getInstance();
		else
			inputController = ArrowInputController.getInstance();

		player = new ShooterPlayersController(0, 0, new PersonsCubeFactory(inputController));
		floor1 = new Floor(0, 0);
		floor2 = new Floor(1, 0);
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
}

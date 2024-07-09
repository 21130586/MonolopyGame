package controller;

import model.GameModel;
import model.Player;

public class GameController {
	GameModel gameModel = new GameModel();

	public void addPlayer(Player player) {
		gameModel.addPlayer(player);
	}

	public void removePlayer(Player player) {
		gameModel.removePlayer(player);
	}

	public GameModel getGameModel() {
		return gameModel;
	}

	public void notifyGameOb() {
		gameModel.notifyGameObs();
	}

	public void rollDice() {
		int step = gameModel.rollDice();
		gameModel.move(step);
	}

	public void endTurn() {
		// TODO Auto-generated method stub
		gameModel.nextPlayer();
	}
}

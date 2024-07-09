package model;

import java.util.List;

public interface Board {

	public Player getCurrPlayer();

	public List<Square> getSquareList();

	public void setCurrPlayer(Player player);

	public int rollingDice();

	public void goSquareFuntion();

	public void goToJailSquareFunction();
	
	public void payToGetOutOfJail();

	public void useFreeGetOutOfJailCard();

	public void move(int steps);

	public void notifyBoardOB();

	public void notifyPlayerOb();

	public void notifySquareOb();

	public void registerBoardObserver(BoardObserver ob);

	public void removerBoardObserver(BoardObserver ob);

	public void registerDiceObserver(DiceObserver ob);

	public void removerDiceObserver(DiceObserver ob);

	public void registerSquareObserver(SquareObserver ob);

	public void removerSquareObserver(SquareObserver ob);

	public void registerPlayerObserver(PlayerObserver ob);

	public void removerPlayerObserver(PlayerObserver ob);

	public void createSquareList();

	public void updatePlayerLocation();
}

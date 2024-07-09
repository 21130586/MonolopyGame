package model;

import java.util.ArrayList;
import java.util.List;

public class BoardModel implements Board {
	List<BoardObserver> boardObserver;
	List<PlayerObserver> playerObList;
	List<SquareObserver> squareObList;
	List<DiceObserver> diceObList;
	List<Square> squareList;
	Player currentPlayer;
	Dice dice;

	public BoardModel() {
		squareList = new ArrayList<Square>();
		boardObserver = new ArrayList<BoardObserver>();
		diceObList = new ArrayList<DiceObserver>();
		squareObList = new ArrayList<SquareObserver>();
		playerObList = new ArrayList<PlayerObserver>();
		dice = new DoubleDice();
		createSquareList();
	}

	@Override
	public int rollingDice() {
		// TODO Auto-generated method stub
		int step = dice.roll();
		if (dice.isDicePairValue()) {
			if (currentPlayer.isJailed()) {
				currentPlayer.setJailed(false);
				System.out.println("your free");
				step = 0;
			}
			notifyDiceOB(dice.getDiceValue()[0], dice.getDiceValue()[1], true);
		}else if(dice.isDicePairValue() == false) {
			if(currentPlayer.isJailed()) {
				payToGetOutOfJail();
				currentPlayer.setJailed(false);
			}
			notifyDiceOB(dice.getDiceValue()[0], dice.getDiceValue()[1], false); 
		}
		else {
			notifyDiceOB(dice.getDiceValue()[0], dice.getDiceValue()[1], false);
		}
		return step;
	}

	public void notifyBoardOB() {
		for (BoardObserver ob : boardObserver) {
			ob.update();
		}
	}

	public void notifyDiceOB(int diceA, int diceB, boolean isPair) {
		for (DiceObserver ob : diceObList) {
			ob.update(diceA, diceB, isPair);
		}
	}

	public void notifyPlayerOb() {
		for (PlayerObserver ob : playerObList) {
			ob.update(currentPlayer.getPropertyList(), currentPlayer.getMoney(), currentPlayer.getX(),
					currentPlayer.getY());
		}
	}

	@Override
	public void updatePlayerLocation() {
		for (PlayerObserver ob : playerObList) {
			ob.update(currentPlayer.getX(), currentPlayer.getY());
		}
	}

	public void updatePlayerProperty() {
		for (PlayerObserver ob : playerObList) {
			ob.update(currentPlayer.getPropertyList());
		}
	}

	public void updatePlayerMoney() {
		for (PlayerObserver ob : playerObList) {
			ob.update(currentPlayer.getMoney());
		}
	}

	public void notifySquareOb() {
		for (SquareObserver ob : squareObList) {
			ob.update(0, null);
		}
	}

	@Override
	public void registerBoardObserver(BoardObserver ob) {
		// TODO Auto-generated method stub
		boardObserver.add(ob);
	}

	@Override
	public void removerBoardObserver(BoardObserver ob) {
		// TODO Auto-generated method stub
		boardObserver.remove(ob);
	}

	@Override
	public void move(int steps) {
		// TODO Auto-generated method stub
		if (!currentPlayer.isJailed()) {
			for (int i = 0; i < steps; i++) {
				if (currentPlayer.getIndex() == 0 && steps - i > 0) {
					goSquareFuntion();
				}
				currentPlayer.setIndex(currentPlayer.getIndex() + 1);
				if (currentPlayer.getIndex() > 31) {
					currentPlayer.setIndex(0);
				}
			}
			currentPlayer.setX(squareList.get(currentPlayer.getIndex()).getX());
			currentPlayer.setY(squareList.get(currentPlayer.getIndex()).getY());
			updatePlayerLocation();

		}
	}

	@Override
	public void goSquareFuntion() {
		// TODO Auto-generated method stub
		currentPlayer.setIndex(0);
		currentPlayer.setMoney(currentPlayer.getMoney() + 200);
		updatePlayerLocation();
	}

	@Override
	public void goToJailSquareFunction() {
		// TODO Auto-generated method stub
		currentPlayer.setJailed(true);
		currentPlayer.setIndex(8);
		updatePlayerLocation();
	}

	@Override
	public void useFreeGetOutOfJailCard() {
		// TODO Auto-generated method stub
		if (currentPlayer.getNumOfFreeGetOutOfJailCard() > 0) {
			currentPlayer.setJailed(false);
			currentPlayer.setNumOfFreeGetOutOfJailCard(currentPlayer.getNumOfFreeGetOutOfJailCard() - 1);
		}
	}

	@Override
	public void setCurrPlayer(Player player) {
		// TODO Auto-generated method stub
		currentPlayer = player;
	}

	@Override
	public Player getCurrPlayer() {
		// TODO Auto-generated method stub
		return currentPlayer;
	}

	@Override
	public void registerSquareObserver(SquareObserver ob) {
		// TODO Auto-generated method stub
		squareObList.add(ob);
	}

	@Override
	public void removerSquareObserver(SquareObserver ob) {
		// TODO Auto-generated method stub
		squareObList.remove(ob);
	}

	@Override
	public void registerPlayerObserver(PlayerObserver ob) {
		// TODO Auto-generated method stub
		playerObList.add(ob);
	}

	@Override
	public void removerPlayerObserver(PlayerObserver ob) {
		// TODO Auto-generated method stub
		playerObList.remove(ob);
	}

	@Override
	public void createSquareList() {
		// TODO Auto-generated method stub

		double[] electicComapyPrice = { 150 };
		double[] electicComapyTax = { 1 };
		double[] railRoadPrice = { 200 };
		double[] railRoadTax = { 25, 50, 100, 200 };

		double[] ConnecticutAvenuePrices = { 120, 50, 50, 50, 50, 250 };
		double[] VermontAvenuePrices = { 100, 50, 50, 50, 50, 250 };
		double[] OrientalAvenuePrices = { 100, 50, 50, 50, 50, 250 };
		double[] BalticAvenuePrices = { 60, 50, 50, 50, 50, 250 };
		double[] NewYorkAvenuePrices = { 200, 100, 100, 100, 100, 500 };
		double[] TennesseeAvenuePrices = { 180, 100, 100, 100, 100, 500 };
		double[] STJamesPlacePrices = { 180, 100, 100, 100, 100, 500 };
		double[] StatesAvenuePrices = { 140, 100, 100, 100, 100, 500 };
		double[] KentuckyAvenuePrices = { 220, 150, 150, 150, 150, 750 };
		double[] IllinoisAvenuePrices = { 240, 150, 150, 150, 150, 750 };
		double[] IndianaAvenuePrices = { 220, 150, 150, 150, 150, 750 };
		double[] AtlanticAvenuePrices = { 260, 150, 150, 150, 150, 750 };
		double[] VentnorAvenuePrices = { 260, 150, 150, 150, 150, 750 };
		double[] PacificAvenuePrices = { 300, 200, 200, 200, 200, 1000 };
		double[] NorthCarolinaAvenuePrices = { 300, 200, 200, 200, 200, 1000 };
		double[] PennsylvaniaAvenuePrices = { 320, 200, 200, 200, 200, 1000 };
		double[] ParkPlacePrices = { 350, 200, 200, 200, 200, 1000 };

		double[] ConnecticutAvenueTaxes = { 8, 40, 100, 300, 450, 600 };
		double[] VermontAvenueTaxes = { 6, 30, 90, 270, 400, 550 };
		double[] OrientalAvenueTaxes = { 6, 30, 90, 270, 400, 550 };
		double[] BalticAvenueTaxes = { 4, 20, 80, 180, 320, 450 };
		double[] NewYorkAvenueTaxes = { 16, 80, 220, 600, 800, 1000 };
		double[] TennesseeAvenueTaxes = { 14, 70, 200, 550, 750, 950 };
		double[] STJamesPlaceTaxes = { 50, 70, 200, 550, 750, 950 };
		double[] StatesAvenueTaxes = { 10, 50, 150, 450, 625, 750 };
		double[] KentuckyAvenueTaxes = { 18, 90, 250, 700, 875, 1050 };
		double[] IllinoisAvenueTaxes = { 18, 90, 250, 700, 875, 1050 };
		double[] IndianaAvenueTaxes = { 20, 100, 300, 750, 925, 1100 };
		double[] AtlanticAvenueTaxes = { 22, 110, 330, 800, 975, 1150 };
		double[] VentnorAvenueTaxes = { 22, 110, 330, 800, 975, 1150 };
		double[] PacificAvenueTaxes = { 26, 130, 390, 900, 1100, 1275 };
		double[] NorthCarolinaAvenueTaxes = { 26, 130, 390, 900, 1100, 1275 };
		double[] PennsylvaniaAvenueTaxes = { 28, 150, 450, 1000, 1200, 1400 };
		double[] ParkPlaceTaxes = { 50, 200, 600, 1400, 1700, 2000 };

		Square Go = new SquareModel(520, 505);
		Square ConnecticutAvenue = new SquareModel(ConnecticutAvenuePrices, ConnecticutAvenueTaxes, "Blue", "Connecticut Avenue", 464, 505);
		Square VermontAvenue = new SquareModel(VermontAvenuePrices, VermontAvenueTaxes, "Blue", "Vermont Avenue", 408, 505);
		Square CommunityChest1 = new SquareModel(352, 505);
		Square OrientalAvenue = new SquareModel(OrientalAvenuePrices, OrientalAvenueTaxes, "Blue", "Oriental Avenue", 296, 505);
		Square ReadingRailRoad = new SquareModel(railRoadPrice, railRoadTax, false, "Reading RailRoad", 240, 505);
		Square Chance1 = new SquareModel(184, 505);
		Square BalticAvenue = new SquareModel(BalticAvenuePrices, BalticAvenueTaxes, "Blue", "Baltic Avenue", 128, 505);
		Square Jail = new SquareModel(52, 505);
		Square NewYorkAvenue = new SquareModel(NewYorkAvenuePrices, NewYorkAvenueTaxes, "Blue", "NewYork Avenue", 82, 462);
		Square TennesseeAvenue = new SquareModel(TennesseeAvenuePrices, TennesseeAvenueTaxes, "Blue", "Tennessee Avenue", 82, 412);
		Square CommunityChest2 = new SquareModel(82, 352);
		Square STJamesPlace = new SquareModel(STJamesPlacePrices, STJamesPlaceTaxes, "Blue", "ST. James Place", 82, 296);
		Square PennsylvaniaRailRoad = new SquareModel(railRoadPrice, railRoadTax, false, "Pennsylvania RailRoad", 82, 240);
		Square ElectricCompany = new SquareModel(electicComapyPrice, electicComapyTax, false, "Elctric Company", 82,
				184);
		Square StatesAvenue = new SquareModel(StatesAvenuePrices, StatesAvenueTaxes, "Blue", "States Avenue", 82, 128);
		Square FreeParking = new SquareModel(52, 82);
		Square KentuckyAvenue = new SquareModel(KentuckyAvenuePrices, KentuckyAvenueTaxes, "Blue", "Kentucky Avenue", 128, 82);
		Square Chance2 = new SquareModel(184, 82);
		Square IllinoisAvenue = new SquareModel(IllinoisAvenuePrices, IllinoisAvenueTaxes, "Blue", "Illinois Avenue", 240, 82);
		Square IndianaAvenue = new SquareModel(IndianaAvenuePrices, IndianaAvenueTaxes, "Blue", "Indiana Avenue", 296, 82);
		Square BORailRoad = new SquareModel(railRoadPrice, railRoadTax, false, "B. & O. RailRoad", 352, 82);
		Square AtlanticAvenue = new SquareModel(AtlanticAvenuePrices, AtlanticAvenueTaxes, "Blue", "Atlantic Avenue", 408, 82);
		Square VentnorAvenue = new SquareModel(VentnorAvenuePrices, VentnorAvenueTaxes, "Blue", "Ventnor Avenue", 464, 82);
		Square GoToJail = new SquareModel(550, 82);
		Square PacificAvenue = new SquareModel(PacificAvenuePrices, PacificAvenueTaxes, "Blue", "Pacific Avenue", 505, 128);
		Square NorthCarolinaAvenue = new SquareModel(NorthCarolinaAvenuePrices, NorthCarolinaAvenueTaxes, "Blue", "North Carolina Avenue", 505, 184);
		Square CommunityChest3 = new SquareModel(505, 240);
		Square PennsylvaniaAvenue = new SquareModel(PennsylvaniaAvenuePrices, PennsylvaniaAvenueTaxes, "Blue", "Pennsylvania Avenue", 505, 296);
		Square ShortLineRailRoad = new SquareModel(railRoadPrice, railRoadTax, false, "Short Line RailRoad", 505, 352);
		Square Chance3 = new SquareModel(505, 408);
		Square ParkPlace = new SquareModel(ParkPlacePrices, ParkPlaceTaxes, "Blue", "Park Place", 505, 464);

		squareList.add(Go);
		squareList.add(ConnecticutAvenue);
		squareList.add(VermontAvenue);
		squareList.add(CommunityChest1);
		squareList.add(OrientalAvenue);
		squareList.add(ReadingRailRoad);
		squareList.add(Chance1);
		squareList.add(BalticAvenue);
		squareList.add(Jail);
		squareList.add(NewYorkAvenue);
		squareList.add(TennesseeAvenue);
		squareList.add(CommunityChest2);
		squareList.add(STJamesPlace);
		squareList.add(PennsylvaniaRailRoad);
		squareList.add(ElectricCompany);
		squareList.add(StatesAvenue);
		squareList.add(FreeParking);
		squareList.add(KentuckyAvenue);
		squareList.add(Chance2);
		squareList.add(IllinoisAvenue);
		squareList.add(IndianaAvenue);
		squareList.add(BORailRoad);
		squareList.add(AtlanticAvenue);
		squareList.add(VentnorAvenue);
		squareList.add(GoToJail);
		squareList.add(PacificAvenue);
		squareList.add(NorthCarolinaAvenue);
		squareList.add(CommunityChest3);
		squareList.add(PennsylvaniaAvenue);
		squareList.add(ShortLineRailRoad);
		squareList.add(Chance3);
		squareList.add(ParkPlace);
	}

	@Override
	public List<Square> getSquareList() {
		// TODO Auto-generated method stub
		return squareList;
	}

	@Override
	public void registerDiceObserver(DiceObserver ob) {
		// TODO Auto-generated method stub
		diceObList.add(ob);
	}

	@Override
	public void removerDiceObserver(DiceObserver ob) {
		// TODO Auto-generated method stub
		diceObList.remove(ob);
	}

	@Override
	public void payToGetOutOfJail() {
		// TODO Auto-generated method stub
		currentPlayer.setMoney(currentPlayer.getMoney() - 50);
	}

//	public static void main(String[] args) {
//		Board board = new BoardModel();
//		System.out.println(board.getSquareList().get(2).getX());
//	}

}

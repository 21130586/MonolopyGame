package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameModel {
	Board boardmodel;
	List<Player> playerList;
	List<GameModelObserver> observerList;

	public GameModel() {
		playerList = new ArrayList<Player>();
		boardmodel = new BoardModel();
		observerList = new ArrayList<GameModelObserver>();
	}

	public int rollDice() {
		return boardmodel.rollingDice();
	}

	public void addPlayer(Player player) {
		playerList.add(player);
	}

	public void removePlayer(Player player) {
		playerList.remove(player);
	}

	public void gameStart() {
		boardmodel.setCurrPlayer(playerList.get(0));
		boardmodel.notifyPlayerOb();
	}

	public void nextPlayer() {
		int index = playerList.indexOf(boardmodel.getCurrPlayer());
		if (index == playerList.size() - 1) {
			boardmodel.setCurrPlayer(playerList.get(0));
		} else {
			boardmodel.setCurrPlayer(playerList.get(index + 1));
		}
		boardmodel.notifyPlayerOb();
	}

	public void chanceSquareFunction() {
		// TODO Auto-generated method stub
		String[] chanceCards = { "Go to Jail", "Go back 3 spaces", "Advanced to Go", "You won a competition collect 100$",
				"You have been elected chairman of the board pay each other 50$", "Take a trip to Reading RailRoad",
				"Get out of jail free card", "Pay poor tax 15$", "You building your loan mature collect 150$", 
				"Advanced to Illinois Avenue"};
		Random ranNum = new Random();
		String getCard = chanceCards[ranNum.nextInt(chanceCards.length - 1)];

		switch (getCard) {
		case "Advanced to Go":
			boardmodel.goSquareFuntion();
			System.out.println(getCard);
			updateNoticeCard("chance0");
			boardmodel.notifyPlayerOb();
			break;
		case "Go back 3 spaces":
			boardmodel.getCurrPlayer().setIndex(boardmodel.getCurrPlayer().getIndex() - 3);
			int n = boardmodel.getCurrPlayer().getIndex();
			updateNoticeCard("chance7");
			System.out.println(getCard);
			if(n == 4 || n == 11 || n == 27) comunitySquareFunction(); ;
			boardmodel.updatePlayerLocation();
			boardmodel.notifyPlayerOb();
			break;
		case "Go to Jail":
			boardmodel.goToJailSquareFunction();
			System.out.println(getCard);
			updateNoticeCard("chance8");
			boardmodel.notifyPlayerOb();
			break;
		case "Pay poor tax 15$":
			boardmodel.getCurrPlayer().setMoney(boardmodel.getCurrPlayer().getMoney() - 15);
			System.out.println(getCard);
			updateNoticeCard("chance10");
			boardmodel.notifyPlayerOb();
			break;
		case "You building your loan mature collect 150$":
			boardmodel.getCurrPlayer().setMoney(boardmodel.getCurrPlayer().getMoney() + 150);
			System.out.println(getCard);
			updateNoticeCard("chance14");
			boardmodel.notifyPlayerOb();
			break;
		case "You won a competition collect 100$":
			boardmodel.getCurrPlayer().setMoney(boardmodel.getCurrPlayer().getMoney() + 100);
			System.out.println(getCard);
			updateNoticeCard("chance15");
			boardmodel.notifyPlayerOb();
			break;
		case "You have been elected chairman of the board pay each other 50$":
			for(Player player : playerList) {
				player.setMoney(player.getMoney() + 50);
			};
			boardmodel.getCurrPlayer().setMoney(boardmodel.getCurrPlayer().getMoney() - (50 * playerList.size()));
			System.out.println(getCard);
			updateNoticeCard("chance13");
			boardmodel.notifyPlayerOb();
			break;
		case "Take a trip to Reading RailRoad":
			n = boardmodel.getCurrPlayer().getIndex();
			if(n > 5) boardmodel.getCurrPlayer().setMoney(boardmodel.getCurrPlayer().getMoney() + 200);
			boardmodel.getCurrPlayer().setIndex(5);
			System.out.println(getCard);
			updateNoticeCard("chance11");
			boardmodel.updatePlayerLocation();
			boardmodel.notifySquareOb();
			break;
		case "Get out of jail free card":
			boardmodel.getCurrPlayer().setNumOfFreeGetOutOfJailCard(boardmodel.getCurrPlayer().getNumOfFreeGetOutOfJailCard() + 1);
			System.out.println(getCard);
			updateNoticeCard("chance6");
			boardmodel.notifyPlayerOb();
			break;
		case "Advanced to Illinois Avenue":
			n = boardmodel.getCurrPlayer().getIndex();
			if(n > 19) boardmodel.getCurrPlayer().setMoney(boardmodel.getCurrPlayer().getMoney() + 200);
			boardmodel.getCurrPlayer().setIndex(19);
			System.out.println(getCard);
			updateNoticeCard("chance1");
			boardmodel.updatePlayerLocation();
			boardmodel.notifyPlayerOb();
			break;
		}
	}

	public void comunitySquareFunction() {
		// TODO Auto-generated method stub
		String[] communityChestCards = { "Get out of jail free card", "Advanced to Go", "Doctor fees pay 50$", "Go to jail",
				"Grand Opera Night", "Hopistal fees pay 100$", "School fees pay 50$", "You inherit 100$", 
				"Sale of stock collect 50$", "Holiday fund mature collect 100$"};
		Random ranNum = new Random();
		String getCard = communityChestCards[ranNum.nextInt(communityChestCards.length - 1)];

		switch (getCard) {
		case "Get out of Jail free card":
			boardmodel.getCurrPlayer().setNumOfFreeGetOutOfJailCard(boardmodel.getCurrPlayer().getNumOfFreeGetOutOfJailCard() + 1);
			updateNoticeCard("community3");
			boardmodel.notifyBoardOB();
			boardmodel.notifyPlayerOb();
			break;
		case "Advanced to Go":
			boardmodel.goSquareFuntion();
			System.out.println(getCard);
			updateNoticeCard("community0");
			boardmodel.notifyPlayerOb();
			break;
		case "Doctor fees pay 50$":
			boardmodel.getCurrPlayer().setMoney(boardmodel.getCurrPlayer().getMoney() + 50);
			System.out.println(getCard);
			updateNoticeCard("community2");
			boardmodel.notifyPlayerOb();
			break;
		case "Go to jail":
			boardmodel.goToJailSquareFunction();
			System.out.println(getCard);
			updateNoticeCard("community4");
			boardmodel.notifyPlayerOb();
			break;
		case "Grand Opera Night":
			for (Player player : playerList) {
				player.setMoney(player.getMoney() - 50);
			}
			boardmodel.getCurrPlayer().setMoney(boardmodel.getCurrPlayer().getMoney() + (50 * playerList.size()));
			updateNoticeCard("community6");
			boardmodel.notifyPlayerOb();
			break;
		case "Hopistal fees pay 100$":
			boardmodel.getCurrPlayer().setMoney(boardmodel.getCurrPlayer().getMoney() - 100);
			System.out.println(getCard);
			updateNoticeCard("community9");
			boardmodel.notifyPlayerOb();
			break;
		case "School fees pay 50$":
			boardmodel.getCurrPlayer().setMoney(boardmodel.getCurrPlayer().getMoney() - 50);
			System.out.println(getCard);
			updateNoticeCard("community10");
			boardmodel.notifyPlayerOb();
			break;
		case "You inherit 100$":
			boardmodel.getCurrPlayer().setMoney(boardmodel.getCurrPlayer().getMoney() + 100);
			System.out.println(getCard);
			updateNoticeCard("community14");
			boardmodel.notifyPlayerOb();
			break;
		case "Sale of stock collect 50$":
			boardmodel.getCurrPlayer().setMoney(boardmodel.getCurrPlayer().getMoney() + 50);
			System.out.println(getCard);
			updateNoticeCard("community15");
			boardmodel.notifyPlayerOb();
			break;
		case "Holiday fund mature collect 100$":
			boardmodel.getCurrPlayer().setMoney(boardmodel.getCurrPlayer().getMoney() + 100);
			System.out.println(getCard);
			updateNoticeCard("community16");
			boardmodel.notifyPlayerOb();
			break;
		}
	}

	public void notifyGameObs() {
		for (GameModelObserver ob : observerList) {
			ob.update(playerList.size());
		}
	}

	public void updateNoticeCard(String src) {
		for (GameModelObserver ob : observerList) {
			ob.cardNotice(src);
		}
	}

	public void registerObserver(GameModelObserver ob) {
		observerList.add(ob);
	}

	public void removerObserver(GameModelObserver ob) {
		observerList.remove(ob);
	}

	public List<Player> getPlayerList() {
		return playerList;
	}

	public Board getBoardModel() {
		return boardmodel;
	}

	public void move(int step) {
		// TODO Auto-generated method stub
		boardmodel.move(step);
		doSquareFunction();
	}

	public void doSquareFunction() {
		// TODO Auto-generated method stub
		int index = boardmodel.getCurrPlayer().getIndex();
		System.out.println(index);
		if (index == 3 || index == 11 || index == 27) {
			comunitySquareFunction();
			System.out.println("chest");
		} else if (index == 6 || index == 18 || index == 30) {
			chanceSquareFunction();
			System.out.println("Chance");
		} else if (index == 24) {
			boardmodel.goToJailSquareFunction();
			System.out.println("GotoJail");
		} else {
			System.out.println("Land");
		}
		//		switch (index) {
		//		case 3, 11, 27: {
		//			comunitySquareFunction();
		//		}
		//		case 6, 18, 30: {
		//			chanceSquareFunction();
		//		}
		//		case 24: {
		//			boardmodel.goToJailSquareFunction();
		//		}
		//		case 1, 2, 4, 5, 7, 9, 10, 12, 13, 15, 17, 19, 20, 21, 22, 23, 25, 26, 28, 29, 31: {
		//			Square property = boardmodel.getSquareList().get(index);
		//			if (property.isOwn() && !boardmodel.getCurrPlayer().getPropertyList().contains(property)) {
		//				boardmodel.getCurrPlayer().setMoney(boardmodel.getCurrPlayer().getMoney() - property.getTax());
		//				boardmodel.notifyPlayerOb();
		//			}
		//		}
		//		case 0, 8: {
		//
		//		}
		//		default:
		//		}
	}

}

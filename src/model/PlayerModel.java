package model;

import java.util.ArrayList;
import java.util.List;

public class PlayerModel implements Player {
	boolean jailed;
	double money;
	List<Square> propertyList;
	int X;
	int Y;
	int index;
	int numOfFreeGetOutOfJailCard = 0;

	public PlayerModel() {
		propertyList = new ArrayList<Square>();
		money = 2500;
		index = 0;
	}

	public PlayerModel(int X, int Y) {
		this.X = X;
		this.Y = Y;
		money = 2500;
		index = 0;
		propertyList = new ArrayList<Square>();
	}

	public void buy(Square square) {
		if (square.isOwn()) {

		} else if (money >= square.getPrice()) {
			if (square.getLevel() == 0) {
				money = money - square.getPrice();
				propertyList.add(square);
			} else if (square.getLevel() != -1 && square.getLevel() < 5) {
				money = money - square.getPrice();
				square.levelUp();
			}
		}
	}

	public void sell(Square square) {
		if (propertyList.contains(square)) {
			if (square.getLevel() == 0) {
				propertyList.remove(square);
				money = money + square.getPrice() / 2;
			} else if (square.getLevel() != -1) {
				money = money + square.getPrice() / 2;
				square.levelDown();
			}
		}
	}

	@Override
	public double setMoney(double money) {
		// TODO Auto-generated method stub
		return this.money = money;
	}

	@Override
	public void setNumOfFreeGetOutOfJailCard(int numOfCard) {
		// TODO Auto-generated method stub
		this.numOfFreeGetOutOfJailCard = numOfCard;
	}

	@Override
	public int getNumOfFreeGetOutOfJailCard() {
		// TODO Auto-generated method stub
		return numOfFreeGetOutOfJailCard;
	}

	@Override
	public int getX() {
		// TODO Auto-generated method stub
		return X;
	}

	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return Y;
	}

	@Override
	public boolean isJailed() {
		// TODO Auto-generated method stub
		return jailed;
	}

	@Override
	public void setJailed(boolean jail) {
		// TODO Auto-generated method stub
		this.jailed = jail;
	}

	@Override
	public int getIndex() {
		// TODO Auto-generated method stub
		return index;
	}

	@Override
	public void setIndex(int index) {
		// TODO Auto-generated method stub
		this.index = index;
	}

	@Override
	public void setX(int x) {
		// TODO Auto-generated method stub
		X = x;
	}

	@Override
	public void setY(int y) {
		// TODO Auto-generated method stub
		Y = y;
	}

	@Override
	public void pay(double moneyPayed) {
		// TODO Auto-generated method stub
		money = money - moneyPayed;
	}

	@Override
	public List<Square> getPropertyList() {
		// TODO Auto-generated method stub
		return propertyList;
	}

	@Override
	public double getMoney() {
		// TODO Auto-generated method stub
		return money;
	}
	
}

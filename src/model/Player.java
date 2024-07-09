package model;

import java.util.List;

public interface Player {
	public List<Square> getPropertyList();

	public double getMoney();

	public void setNumOfFreeGetOutOfJailCard(int numOfCard);

	public int getNumOfFreeGetOutOfJailCard();

	public double setMoney(double money);

	public int getX();

	public int getY();

	public void setX(int x);

	public void setY(int y);

	public int getIndex();

	public void setIndex(int index);

	public void pay(double moneyPayed);

	public boolean isJailed();

	public void setJailed(boolean jail);
	
}

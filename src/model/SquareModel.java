package model;

import java.util.List;

//level | 0 | 1 | 2 | 3 | 4 | 5 |
//price | . | . | . | . | . | . |
//tax   | . | . | . | . | . | . |

public class SquareModel implements Square {
	int X, Y;
	Player owner;
	boolean own;
	String title;
	int level; // level 0 = land; level 1 = 1 house,...level 4 = 4 house; level 5(max) = hotel
	double[] price; // each index has price corresponding to it level, level start at 1 with index 0
	double[] tax; // each index has tax corresponding to it level, level start at 1 with index 0
	String color; // has all square that same color, tax x2

	// land, buy able, hasPrice, hasTax, hasColor, own false defaultLv = 0
	public SquareModel(double[] price, double[] tax, String color, String title, int X, int Y) {
		level = 0;
		own = false;
		this.price = price;
		this.tax = tax;
		this.color = color;
		this.title = title;
		this.X = X;
		this.Y = Y;
	}

	// special square, unBuy able ,own false, has no price/tax/color, example: Go,
	// Free parking, Chance, Community,... defaultLv = -1
	public SquareModel(int X, int Y) {
		level = -1;
		own = true;
		this.X = X;
		this.Y = Y;
	}

	// special land, buy/unBuy able, has price/no price, has tax, example: station,
	// company, Tax square, defaultLv = 0
	public SquareModel(double[] price, double[] tax, boolean own, String title, int X, int Y) {
		level = 0;
		this.title = title;
		this.price = price;
		this.tax = tax;
		this.own = own;
		this.X = X;
		this.Y = Y;
	}

	@Override
	public double getTax() {
		// TODO Auto-generated method stub
		return tax[level];
	}

	@Override
	public double getPrice() {
		// TODO Auto-generated method stub
		return price[level];
	}

	@Override
	public boolean isOwn() {
		// TODO Auto-generated method stub
		return own;
	}

	@Override
	public void setOwn(boolean b) {
		// TODO Auto-generated method stub
		own = b;
	}

	@Override
	public int getLevel() {
		// TODO Auto-generated method stub
		return level;
	}

	@Override
	public int setLevel(int level) {
		// TODO Auto-generated method stub
		return level;
	}

	@Override
	public void levelUp() {
		// TODO Auto-generated method stub
		level++;
	}

	@Override
	public void levelDown() {
		// TODO Auto-generated method stub
		level--;
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
	public String getTitle() {
		// TODO Auto-generated method stub
		return title;
	}

	@Override
	public Player getOwner() {
		// TODO Auto-generated method stub
		return owner;
	}

	@Override
	public void setOwner(Player player) {
		// TODO Auto-generated method stub
		owner = player;
	}

}

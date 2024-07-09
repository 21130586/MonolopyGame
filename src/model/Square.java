package model;

public interface Square {
	public void setOwn(boolean b);

	public int getX();

	public int getY();

	public Player getOwner();

	public void setOwner(Player player);

	public boolean isOwn();

	public String getTitle();

	public int getLevel();

	public int setLevel(int level);

	public void levelUp();

	public void levelDown();

	public double getTax();

	public double getPrice();

}

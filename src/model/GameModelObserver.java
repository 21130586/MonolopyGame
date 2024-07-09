package model;

public interface GameModelObserver {
	public void update(int numOfPlayer);

	public void cardNotice(String src);
}

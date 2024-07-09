package model;

import java.util.List;

public interface PlayerObserver {
	public void update(List<Square> propertyList, double money, int X, int Y);

	public void update(double money);

	public void update(int X, int Y);

	public void update(List<Square> propertyList);
}

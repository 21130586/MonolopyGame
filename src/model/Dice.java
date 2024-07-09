package model;

public interface Dice {
	public int roll();

	public int[] getDiceValue();

	public void setDoubleDiceValue(int a, int b);

	public boolean isDicePairValue();
}

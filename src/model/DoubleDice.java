	package model;

import java.util.Random;

public class DoubleDice implements Dice {
	int diceA, diceB;
	boolean dicePairValue;

	@Override
	public int roll() {
		// TODO Auto-generated method stub
		Random ran = new Random();
		int dicea = ran.nextInt(6) + 1;
		int diceb = ran.nextInt(6) + 1;
		setDoubleDiceValue(dicea, diceb);
		if (dicea == diceb) {
			dicePairValue = true;
		} else {
			dicePairValue = false;
		}
		System.out.println(diceA + "|" + diceB);
		return dicea + diceb;
	}

	@Override
	public int[] getDiceValue() {
		// TODO Auto-generated method stub
		int[] a = { diceA, diceB };
		return a;
	}

	@Override
	public void setDoubleDiceValue(int a, int b) {
		// TODO Auto-generated method stub
		diceA = a;
		diceB = b;
	}

	public boolean isDicePairValue() {
		
		return dicePairValue;
	}

}

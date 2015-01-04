package com.me.mygdxgame.math;

public class MathPercentage {
	public static int getPercentage(float large, float small) {
		return (int)(small / large * 100);
	}
	public static int getNumber(float percent, float number) {
		return (int)(percent / 100 * number);
	}
}

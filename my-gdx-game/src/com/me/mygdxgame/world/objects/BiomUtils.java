package com.me.mygdxgame.world.objects;

import java.util.Random;

public class BiomUtils {	
	public static boolean chooseRare(int r) {
		return new Random().nextInt(r) + 1 == r;
	}
}

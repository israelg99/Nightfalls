package com.me.mygdxgame.world;

import java.util.List;

import com.me.mygdxgame.world.objects.Block;

public class SeaBiom {
	public static void makeSea(List<Block> map, int width, int x, int y, int h) {
		for(int i = 0; i <= h; i++) {
			for(int j = 0; j <= i; j++) {
				map.set((y - h + i) * width + x + j, new Block(9));
				map.set((y - h + i) * width + x - j, new Block(9));
			}
		}
	}
}

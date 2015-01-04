package com.me.mygdxgame.world;

import java.util.List;
import java.util.Random;

import com.me.mygdxgame.world.objects.BiomUtils;
import com.me.mygdxgame.world.objects.Block;

public class GreenBiom {
	public static void makeWaterStream(List<Block> map, int width, int x, int y) {
		int up = 0;
		while(map.get((y - up) * width + x).ID != 0) {
			if(map.get((y - up) * width + (x - 1)).ID != 7 && !map.get((y - up) * width + (x - 1)).isLeaf()) {	
				up++;
			} else {
				return;
			}
		}
		
		map.set((y - up) * width + (x - 1), new Block(9));
		
	}
	
	public static void makeHill(List<Block> map, int width, int x, int y, int h, int rHillStone, int rHillDirt) {
		for(int i = 0; i <= h; i++) {
			for(int j = 0; j <= i; j++) {
				map.set((y - h + i) * width + x + j, BiomUtils.chooseRare(rHillStone) && j < i ? new Block(2) : BiomUtils.chooseRare(rHillDirt) ? new Block(6) : new Block(1));
				map.set((y - h + i) * width + x - j, BiomUtils.chooseRare(rHillStone) && j < i ? new Block(2) : BiomUtils.chooseRare(rHillDirt) ? new Block(6) : new Block(1));
			}
		}
	}
	
	public static void makeTree(List<Block> map, int width, int x, int y, int h, int leefH, int leefMin) {
		int up = 0;
		while(map.get((y - up) * width + x).ID != 0) {
			if(map.get((y - up) * width + (x - 1)).ID == 7) { return; } else {
				up++;
			}
		}
		
		if(map.get((y - up + 1) * width + x).ID == 8) { return; }
		for(int i = 0; i <= h; i++) {
			if(map.get((y - up - i) * width + (x - 1)).ID == 7) {
				return;
			}
		}
		
		for(int i = 0; i <= h; i++) {
			map.set((y - up - i) * width + x, new Block(7));
		}
		makeLeaf(map, width, x, y - up - h, new Random().nextInt(leefH - leefMin) + leefMin);
	}
	
	public static void makeLeaf(List<Block> map, int width, int x, int y, int h) {
		for(int i = 0; i <= h; i++) {
			for(int j = 0; j <= i; j++) {
				map.set((y - h + i) * width + x + j, map.get((y - h + i) * width + x + j).ID != 7 ? new Block(8) : map.get((y - h + i) * width + x + j));
				map.set((y - h + i) * width + x - j, map.get((y - h + i) * width + x - j).ID != 7 ? new Block(8) : map.get((y - h + i) * width + x - j));
			}
		}
	}
}

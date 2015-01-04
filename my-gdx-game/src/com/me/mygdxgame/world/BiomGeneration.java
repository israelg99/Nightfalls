package com.me.mygdxgame.world;

import java.util.List;
import java.util.Random;

import com.me.mygdxgame.world.objects.Block;

public class BiomGeneration {
	public static final int BIOM_WIDTH = 100;
	public static final int BIOM_HEIGHT = 100;
	
	public static int applyBiomWidth(int num) {
		return num / BIOM_WIDTH * BIOM_WIDTH;
	}
	public static int applyBiomHeight(int num) {
		return num / BIOM_HEIGHT * BIOM_HEIGHT;
	}
	
	// Random
	public static List<Block> generateBioms(List<Block> map, int width, int level, int x, int amount) {
		for(int i = 0; i < amount; i++) {
			map = generateBiom(map, width, level, getX(x, i));
		}
		return map;
	}
	public static List<Block> generateBioms(List<Block> map, List<String> bioms, int width, int level, int x, int amount) {
		for(int i = 0; i < amount; i++) {
			map = generateBiom(map, bioms, width, level, getX(x, i));
		}
		return map;
	}
	
	public static List<Block> generateBiom(List<Block> map, int width, int level, int x) {
		return Biom.values()[new Random().nextInt(Biom.values().length - 1)].generateBiom(map, width, level, x);
	}
	public static List<Block> generateBiom(List<Block> map, List<String> bioms, int width, int level, int x) {
		int rnd = new Random().nextInt(Biom.values().length - 1);
		bioms.add(Biom.values()[rnd].toString());
		return Biom.values()[rnd].generateBiom(map, width, level, x);
	}
	
	// Number
	public static List<Block> generateBioms(int biom, List<Block> map, int width, int level, int x, int amount) {
		for(int i = 0; i < amount; i++) {
			map = Biom.values()[biom].generateBiom(map, width, level, getX(x,i));
		}
		return map;
	}
	public static List<Block> generateBiom(int biom, List<Block> map, int width, int level, int x) {
		return Biom.values()[biom].generateBiom(map, width, level, x);
	}
	
	// String
	public static List<Block> generateBioms(String biom, List<Block> map, int width, int level, int x, int amount) {
		for(int i = 0; i < amount; i++) {
			map = Biom.valueOf(biom).generateBiom(map, width, level, getX(x,i));
		}
		return map;
	}
	public static List<Block> generateBiom(String biom, List<Block> map, int width, int level, int x) {
		return Biom.valueOf(biom).generateBiom(map, width, level, x);
	}
	
	private static int getX(int x, int i) {
		return i * BIOM_WIDTH + x;
	}
	
}
package com.me.mygdxgame.world;

import java.io.Serializable;

import com.me.mygdxgame.world.objects.Block;

public class WorldFile implements Serializable {

	private static final long serialVersionUID = 1L;
	public int[] tempMap;
	public String[] tempBioms;
	public int width;
	public int height;
	private Time savedTime;
	
	public WorldFile(World world) {
		width = world.width;
		height = world.height;
		tempMap = new int[height * width];
		tempBioms = new String[world.bioms.size()];
		
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				int index = i * width + j;
				tempMap[index] = world.map.get(index).ID;
			}
		}
		
		for(int i = 0; i < tempBioms.length; i++) {
			tempBioms[i] = world.bioms.get(i);
		}
		
		savedTime = world.getTime();
	}
	
	public World getWorld() {
		World temp = new World(width, height);
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				int index = i * width + j;
				temp.map.add(new Block(tempMap[index]));
			}
		}
		
		for(int i = 0; i < tempBioms.length; i++) {
			temp.bioms.add(tempBioms[i]);
		}
		
		temp.setTime(savedTime);
		return temp;
	}
}

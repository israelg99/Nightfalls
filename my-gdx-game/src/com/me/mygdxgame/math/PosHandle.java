package com.me.mygdxgame.math;

import java.util.Random;

import com.me.mygdxgame.world.objects.Block;

public class PosHandle {
	public static int getBlockedPos(int x, int y, int width) {
		return getBlockY(y) * width + getBlockX(x);
	}
	public static int getPos(int x, int y, int width) {
		return y * width + x;
	}
	public static int getBlockX(int num) {
		return num / Block.WIDTH;
	}
	public static int getBlockY(int num) {
		return num / Block.HEIGHT;
	}
	public static int toRealX(int num) {
		return num * Block.WIDTH;
	}
	public static int toRealY(int num) {
		return num * Block.HEIGHT;
	}
	public static int getFixedBlockY(int num) {
		if(num % Block.HEIGHT == 0) { return num / Block.HEIGHT; }
		else { return num / Block.HEIGHT + 1; }
	}
	public static int getXPos(int num, int length) {
		return num % length;
	}
	public static int getYPos(int num, int length) {
		return num / length;
	}
	public static int getRestXPosBlocked(int num) {
		return getXPos(num, Block.WIDTH);
	}
	public static int getRestYPosBlocked(int num) {
		return getYPos(num, Block.HEIGHT);
	}
	public static int getRealXPos(int num, int length) {
		return num % getBlockX(length);
	}
	public static int getRealYPos(int num, int length) {
		return num / getBlockX(length);
	}
	public static int getInBlockedX(int num) {
		return num /Block.WIDTH *Block.WIDTH;
	}
	public static int getInBlockedY(int num) {
		return num /Block.HEIGHT *Block.HEIGHT;
	}
	public static int getFloorBlockedX(int num) {
		return num - (num % Block.WIDTH);
	}
	public static int getFloorBlockedY(int num) {
		return num - (num % Block.HEIGHT);
	}
	public static int getRandom(int min, int max) {
		return min + new Random().nextInt(max - min + 1);
	}
	public static int getYWorld(int y, int width) {
		return y * width;
	}
}
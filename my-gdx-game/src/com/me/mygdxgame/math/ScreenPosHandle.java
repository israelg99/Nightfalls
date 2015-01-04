package com.me.mygdxgame.math;

import com.me.mygdxgame.game.Render;


public class ScreenPosHandle {
	
	public static int getCenterScreenX(int w) {
		return Render.ResX / 2 - w / 2;
	}
	
	public static int getCenterScreenY(int h) {
		return Render.ResY / 2 - h / 2;
	}
	public static int getSizeCenter(int s, int is) {
		return  s/ 2 - is / 2;
	}	
	public static int getPosCenter(int sp, int s, int is) {
		return  sp + (s/ 2 - is / 2);
	}	

	public static int getViableSize(int s, int is) {
		return (s / is * is);
	}
	
	public static int SpaceFit(int space, int object) {
		return space / object;
	}
}

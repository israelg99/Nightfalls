package com.me.mygdxgame.HUD;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.me.mygdxgame.math.ScreenPosHandle;
import com.me.mygdxgame.resources.Resources;
import com.me.mygdxgame.utility.TextureHandle;

public class EscapeMenuHUD {
	private static final Sprite BG = TextureHandle.getSpriteFlippedY("ui/menugamebg1.png");
	
	public static final int WIDTH = 500;
	public static final int HEIGHT = 400;
	
	private static final int CENTER_X = ScreenPosHandle.getCenterScreenX(WIDTH);
	private static final int CENTER_Y = ScreenPosHandle.getCenterScreenY(HEIGHT);
	
	private static final int textW = 220;
	private static final int textH = 70;
	
	private static final int textYADD = 0;
	
	private static int idSelect = 1;
	private static final int idMax = 4;
	
	public static void draw(SpriteBatch batch) {
		batch.draw(BG, CENTER_X, CENTER_Y, WIDTH, HEIGHT);
		drawCheck(batch, 1, "Resume Game");
		drawCheck(batch, 2, "Settings Menu");
		drawCheck(batch, 3, "Main Menu");
		drawCheck(batch, 4, "Quit Game");
	}
	
	private static void drawCheck(SpriteBatch batch, int id, String txt) {
		if(id == idSelect) {
			Resources.font_buttonS.draw(batch, txt, ScreenPosHandle.getPosCenter(CENTER_X,WIDTH, textW), CENTER_Y + (textH * id + textYADD));
		} else {
			Resources.font_button.draw(batch, txt, ScreenPosHandle.getPosCenter(CENTER_X,WIDTH, textW), CENTER_Y + (textH * id + textYADD));
		}
	}
	
	public static void addID() {
		idSelect = idSelect == idMax ? idMax : idSelect + 1;
	}
	public static void subID() {
		idSelect = idSelect == 1 ? 1 : idSelect - 1;
	}
	
	public static void click() {
		EscapeOption.values()[idSelect - 1].trigger();
	}
	
	public static void disableEM() {
		Resources.player.ResetStates();
		HUD.showEscapeMenu(false);
		Resources.player.controlPlayer = true;
	}

	public static void dispose() {
		
	}
}

package com.me.mygdxgame.HUD;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.me.mygdxgame.game.Render;
import com.me.mygdxgame.math.MathPercentage;
import com.me.mygdxgame.math.ScreenPosHandle;
import com.me.mygdxgame.player.Player;
import com.me.mygdxgame.resources.Resources;
import com.me.mygdxgame.utility.TextureHandle;

public class HMBarsHUD {
	
	private static final Sprite LAYOUT = TextureHandle.getSpriteFlippedY("ui/bar.png");
	private static final Sprite HP = TextureHandle.getSpriteFlippedY("ui/hpBar.png");
	private static final Sprite MANA = TextureHandle.getSpriteFlippedY("ui/manaBar.png");
	
	private static final int WIDTH = 700;
	private static final int HEIGHT = 30;
	
	private static final int XPOS = ScreenPosHandle.getCenterScreenX(WIDTH);
	private static final int YPOS = Render.ResY;
	
	private static final int hpYPOS = YPOS - 200;
	private static final int manaYPOS = YPOS - 150;
	
	
	public static void draw(SpriteBatch batch, Player p) {
	
		// HP Bar
		batch.draw(LAYOUT, XPOS, hpYPOS, WIDTH, HEIGHT);
		batch.draw(HP, XPOS, hpYPOS, MathPercentage.getNumber(p.getHP_Percentage(), WIDTH), HEIGHT);
		Resources.font_hminfo.draw(batch, " " + p.getHP(), ScreenPosHandle.getCenterScreenX(50), ScreenPosHandle.getPosCenter(hpYPOS, HEIGHT, 16));
		
		// MANA Bar
		batch.draw(LAYOUT, XPOS, manaYPOS, WIDTH, HEIGHT);
		batch.draw(MANA, XPOS, manaYPOS, MathPercentage.getNumber(p.getMana_Percentage(), WIDTH), HEIGHT);
		Resources.font_hminfo.draw(batch, " " + p.getMana(), ScreenPosHandle.getCenterScreenX(50), ScreenPosHandle.getPosCenter(manaYPOS, HEIGHT, 16));
	}
	
	public static void dispose() {
		
	}
}

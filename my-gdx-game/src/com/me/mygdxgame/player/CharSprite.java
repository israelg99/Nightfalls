package com.me.mygdxgame.player;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.me.mygdxgame.utility.TextureHandle;

public class CharSprite {
	
	private TextureRegion[] walk_frames, run_frames;
	private TextureRegion stand, jump;
	
	public final int BLOCK_SIZE_WIDTH = 80;
	public final int BLOCK_SIZE_HEIGHT = 80;
	
	public int drawCenterX;
	public int drawCenterY;
	
	private final int COLS_LENGTH;
	private final int ROWS_LENGTH;
	
	private TextureRegion[][] sheet;
	
	public CharSprite(int x, int y) {
		TextureRegion[][] sheet = TextureHandle.TextureSplit("sprites/char_right.png", BLOCK_SIZE_WIDTH, BLOCK_SIZE_HEIGHT, true);
		COLS_LENGTH = sheet[0].length;
		ROWS_LENGTH = sheet.length;
		
		walk_frames = new TextureRegion[4];
		
		walk_frames = TextureHandle.ApplyFrames(4, 0, 7, 1, sheet);
		walk_frames = TextureHandle.Flip(false, true, walk_frames);
		
		run_frames = TextureHandle.ApplyFrames(0, 2, 3, 3, sheet);
		run_frames = TextureHandle.Flip(false, true, run_frames);
		
		stand = sheet[0][0];
		stand.flip(false, true);
		
		jump = sheet[6][2];
		jump.flip(false, true);
		
		drawCenterX = x;
		drawCenterY = y;
	}
	
	public Animation getWalkAnimation(String flip) {
		return returnAfterCheck(flip, walk_frames);
	}
	public Animation getRunAnimation(String flip) {
		return returnAfterCheck(flip, run_frames);
	}
	public TextureRegion getStand(String flip) {
		return returnAfterCheck(flip, stand);
	}
	public TextureRegion getJump(String flip) {
		return returnAfterCheck(flip, jump);
	}
	
	private TextureRegion returnAfterCheck(String flip, TextureRegion tr) {
		if(flip == "right") {
			if(tr.isFlipX()) {
				tr.flip(true, false);
			}
			return tr;
		} else if(flip == "left") {
			if(!tr.isFlipX()) {
				tr.flip(true, false);
			}
			return tr;
		} else {
			System.err.println(" ---- You are trying to get TextureRegion with invalid argument.. maybe you spelled flip wrong? hahaha :)");
			return null;
		}
	}
	
	private Animation returnAfterCheck(String flip, TextureRegion[] tr) {
		if(flip == "right") {
			if(tr[0].isFlipX()) {
				tr = TextureHandle.Flip(true, false, tr);
			}
			return new Animation(0.13f, tr);
		} else if(flip == "left") {
			if(!tr[0].isFlipX()) {
				tr = TextureHandle.Flip(true, false, tr);
			}
			return new Animation(0.13f, tr);
		} else {
			System.err.println(" ---- You are trying to get Animation with invalid argument.. maybe you spelled flip wrong? hahaha :)");
			System.err.println(flip == null);
			return null;
		}
	}
}
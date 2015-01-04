package com.me.mygdxgame.utility;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class TextureHandle {
	
	public static Texture getTexture(String path, boolean linear) {
		Texture sheet = new Texture(Gdx.files.internal(path));
		if(linear) {
			sheet.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		}
		return sheet;
	}
	
	public static TextureRegion[][] TextureSplit(String path, int w, int h, boolean linear) {
		Texture sheet = new Texture(Gdx.files.internal(path));
		if(linear) {
			sheet.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		}
		
		TextureRegion[][] temp = TextureRegion.split(sheet, w, h);
		
		return temp;
	}
	
	public static TextureRegion[] ApplyFrames(int sc, int sr, int fc, int fr, TextureRegion[][] frames) {
		TextureRegion[] temp = new TextureRegion[(fr - sr) * (fc - sc)];
		int index = 0;
		for (int i = sr; i < fr; i++) {
            for (int j = sc; j < fc; j++) {
            	temp[index++] = frames[i][j];
            }
		}
		
		return temp;
	}
	
	public static TextureRegion[] Flip(boolean x, boolean y, TextureRegion[] temp) {
		for(int i = 0; i < temp.length; i++) {
			temp[i].flip(x, y);
		}
		
		return temp;
	}
	
	public static Sprite getSprite(String path, boolean linear, boolean x, boolean y) {
		Texture temp = new Texture(Gdx.files.internal(path));
		if(linear) {
			temp.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		}
		Sprite tempS = new Sprite(temp);
		tempS.flip(x, y);
		return tempS;
	}
	
	public static Sprite getSprite(String path) {
		Sprite tempS = new Sprite(new Texture(Gdx.files.internal(path)));
		return tempS;
	}
	
	public static Sprite getSpriteLinear(String path) {
		Texture sheet = new Texture(Gdx.files.internal(path));
		sheet.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		Sprite sprite = new Sprite(sheet);
		return sprite;
	}
	
	public static Sprite getSprite(String path, boolean x, boolean y) {
		Sprite tempS = new Sprite(new Texture(Gdx.files.internal(path)));
		tempS.flip(x, y);
		return tempS;
	}
	
	public static Sprite getSpriteFlippedX(String path) {
		Sprite sprite = new Sprite(new Texture(Gdx.files.internal(path)));
		sprite.flip(true, false);
		return sprite;
	}
	
	public static Sprite getSpriteFlippedY(String path) {
		Sprite sprite = new Sprite(new Texture(Gdx.files.internal(path)));
		sprite.flip(false, true);
		return sprite;
	}
	
	public static BitmapFont getFont(String path, int size, Color color, boolean linear, int x, int y) {
		FreeTypeFontGenerator gen = new FreeTypeFontGenerator(Gdx.files.internal(path));
		BitmapFont font = gen.generateFont(size);
		
		if(linear) {
			font.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		}
		
		font.setColor(color);
		font.setScale(x, y); // 1 = false ||| -1 = true ||| .setScale is similar to the flip method.
		
		return font;
	}
}

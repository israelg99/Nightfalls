package com.me.mygdxgame.world;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.me.mygdxgame.game.Render;
import com.me.mygdxgame.graphics.Shaders;
import com.me.mygdxgame.math.PosHandle;
import com.me.mygdxgame.resources.Resources;
import com.me.mygdxgame.utility.TextureHandle;

public class Background {
	public static Sprite back_sprite;
	public static OrthographicCamera cam;
	private static Sprite pallax; // I know its parallax.. i just like pallax more :D
	private static Sprite tempPallax;
	private static String biom;
	private static float fade;
	private static int fadeWidth = 100;
	private static float scrollSpeed = 0;
	private static int divideSpeed = 55000;
	private static float imageNum = 0.5f;
	
	public static void Load() {
		
		back_sprite = TextureHandle.getSprite("backgrounds/back.jpg", true, false, true);
		
		cam = new OrthographicCamera();
		cam.setToOrtho(true, Render.ResX, Render.ResY);
	}
	
	public static void Init() {
		update();
		
		setU(pallax);
	}
	
	public static void render(SpriteBatch batch) {
		batch.setProjectionMatrix(cam.combined);
		
		batch.setShader(Shaders.motionBlur);
		Shaders.motionBlur.setUniformf("fade", 1.0f);
		batch.draw(pallax, 0, 0, Render.ResX, Render.ResY);
		
		if(fade > 0f) {
			batch.draw(back_sprite, 0, 0, Render.ResX, Render.ResY); // Go figure.
			Shaders.motionBlur.setUniformf("fade", fade);
			batch.draw(tempPallax, 0, 0, Render.ResX, Render.ResY);
		}
	}
	
	public static void update() {
		
		if(biom != Resources.map.getBiom(Resources.player.getX())) {
			loadBG(Resources.map.getBiom(Resources.player.getX()));
		}
		
		if(biom != Resources.map.getBiom(Resources.player.getX() + fadeWidth)) {
			fade = PosHandle.getRestXPosBlocked(Resources.player.getX()) / 100.0f;
			tempPallax = Biom.valueOf(Resources.map.getBiom(Resources.player.getX() + fadeWidth)).getBG();
			setU(tempPallax);
		} else {
			fade = 0f;
		}
	}
	
	public static void loadBG(String b) {
		pallax = Biom.valueOf(b).getBG();
		biom = b;
	}
	
	private static void setU(Sprite p) {
		p.setU(scrollSpeed);
		p.setU2(scrollSpeed + imageNum);
	}
	
	public static void applyMove(int ps) {
		
		update();
		
		float speed = (float)ps / divideSpeed;
		scrollSpeed += speed;
		
		setU(pallax);
	}
	
	public static Sprite getProPallax(String path) {
		Texture temp = TextureHandle.getTexture(path, true);
		temp.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);
		Sprite sTemp = new Sprite(temp);
		sTemp.setSize(Render.ResX, Render.ResY);
		sTemp.flip(false, true);
		
		return sTemp;
	}
}

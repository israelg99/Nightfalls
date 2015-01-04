package com.me.mygdxgame.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.me.mygdxgame.game.Render;
import com.me.mygdxgame.screen.GameScreen;

public class Shaders {
	public static ShaderProgram pass;
	public static ShaderProgram block;
	public static ShaderProgram motionBlur;
	public static ShaderProgram ppBlur;
	
	public static FrameBuffer blurTargetA, blurTargetB;
	public static final int FBO_SIZE = Render.ResX > Render.ResY ? Render.ResX : Render.ResY;
	public static float blurRadius = 8f;
	public final static float MAX_BLUR = 3f;

	public static void Load() {
		ShaderProgram.pedantic = false;

		motionBlur = loadShaders("shaders/pass.vsh", "shaders/motionBlur.fsh");
		block = loadShaders("shaders/pass.vsh", "shaders/block.fsh");
		pass = loadShaders("shaders/pass.vsh", "shaders/pass.fsh");
		ppBlur = loadShaders("shaders/pass.vsh", "shaders/ppBlur.fsh");

		block.begin();
		block.setUniformf("screenSize", GameScreen.SizeX, GameScreen.SizeY);
		block.end();
		
		ppBlur.begin();
		ppBlur.setUniformf("dir", 0f, 0f); //direction of blur; nil for now
		ppBlur.setUniformf("resolution", FBO_SIZE); //size of FBO texture
		ppBlur.setUniformf("radius", blurRadius); //radius of blur
		ppBlur.end();
		
		blurTargetA = new FrameBuffer(Pixmap.Format.RGBA8888, Render.ResX, Render.ResY, false);
		blurTargetB = new FrameBuffer(Pixmap.Format.RGBA8888, Render.ResX, Render.ResY, false);
	}

	private static ShaderProgram loadShaders(String p1, String p2) {
		return new ShaderProgram(Gdx.files.internal(p1), Gdx.files.internal(p2));
	}
}

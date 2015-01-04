package com.me.mygdxgame.game;

import static com.me.mygdxgame.resources.Resources.font_details;
import static com.me.mygdxgame.resources.Resources.map;
import static com.me.mygdxgame.resources.Resources.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.me.mygdxgame.HUD.HUD;
import com.me.mygdxgame.graphics.Particles;
import com.me.mygdxgame.graphics.Shaders;
import com.me.mygdxgame.math.PosHandle;
import com.me.mygdxgame.world.Background;

public class Render {
	
	public static SpriteBatch batch;
	
	public final static int ResX = 1920, ResY = 1080;
	
	public static OrthographicCamera fixedCamera;
	
	public static TextureRegion walkFrame;
	public static TextureRegion runFrame;
	
	public static boolean showDet;
	
	private static FPSLogger fps;
	
	public static void Load() {
		fixedCamera = new OrthographicCamera();
		fixedCamera.setToOrtho(true, ResX, ResY);
		
		fps = new FPSLogger();
		
		batch = new SpriteBatch();
		
		showDet = false;
	}
	
	public static void clear() {
		Gdx.gl.glClearColor(1F, 1F, 1F, 1F);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
	}
	
	public static void draw(float delta) {
		batch.begin();
		
		drawScene(delta);
		
		batch.end();
		
		drawHUD(delta);
	}
	
	public static void drawHUD(float delta) {
		batch.begin();
		
		batch.setShader(Shaders.pass); // Pass Shader
		
		// Draw HUD
		drawHUD();
		
		// Game Details
		drawDet();
		
		batch.end();
	}
	public static void drawScene(float delta) {
		
		// Background
		drawBackground();
		
		
		batch.setProjectionMatrix(player.camera.combined); // PLAYER CAM
		batch.setShader(Shaders.block); // Block Shader
		
		drawMap();
		
		// Particles
		drawParticles(delta);
		
		if(!HUD.isInvShown() && !HUD.isEscapeMenuShown()) {
			// Player
			drawPlayer();
		}
		
		batch.setProjectionMatrix(fixedCamera.combined); // FIXED CAM
		
	}
	
	public static void drawBackground() {
		// Background
		Background.render(batch);
	}
	public static void drawMap() {
		// Map Render
		map.renderMap(batch, player.getX(), player.getY());
	}
	public static void drawParticles(float delta) {
		// Particles
		Particles.render(batch, delta);
	}
	public static void drawPlayer() {
		// Player
		player.draw(batch, walkFrame, runFrame);
		player.drawName(batch);
	}
	public static void drawHUD() {
		// Draw HUD
		HUD.drawHUD(batch, player);
	}
	public static void drawDet() {
		fps.log();
		// Game Details
		if (showDet) {
			font_details.draw(batch, "Version : Not Alpha Yet", 50, 100);
			font_details.draw(batch, "Camera Pos X : " + player.camera.position.x + " Pos Y : " + player.camera.position.y, 50, 150);
			font_details.draw(batch, "Player Pos X : " + player.getX() + " Pos Y : " + player.getY(), 50, 200);
			font_details.draw(batch, "Look Pos X : " + player.getLookX() + " Pos Y : " + player.getLookY(), 50, 250);
			font_details.draw(batch, "Mouse Pos X : " + Gdx.input.getX() + " Pos Y : " + Gdx.input.getY(), 50, 300);
			font_details.draw(batch, "Time : " + map.getWorldTime(), 50, 350);
			font_details.draw(batch, "Days : " + map.getDays(), 50, 400);
			font_details.draw(batch, "Biom : " + map.getBiom(player.getX()), 50, 450);
			font_details.draw(batch, "Block : " + (PosHandle.getBlockY(player.getLookY()) * map.width + PosHandle.getBlockX(player.getLookX())) + " Light = " + map.map.get(PosHandle.getBlockY(player.getLookY()) * map.width + PosHandle.getBlockX(player.getLookX())).light, 50, 500);
		}
	}
	
	public static void renderFBO(float delta) {
		//Bind FBO target A
		Shaders.blurTargetA.begin();
		
		//Clear FBO A with an opaque color to minimize blending issues
		clear();

		//now we can start our batch
		batch.begin();
		
			drawScene(delta);
			
		//flush the batch, i.e. render entities to GPU
		batch.flush();
		
		//After flushing, we can finish rendering to FBO target A
		batch.end();
		Shaders.blurTargetA.end();
	}
	
	public static void ppBlurFBO() {
		//swap the shaders
		//this will send the Render.batch's (FBO-sized) projection matrix to our blur shader
		batch.setShader(Shaders.ppBlur);

		//ensure the direction is along the X-axis only
		Shaders.ppBlur.setUniformf("dir", 1f, 0f);
		
		//start rendering to target B
		Shaders.blurTargetA.begin();
		
		batch.begin();

		//no need to clear since targetA has an opaque background
		//render target A (the scene) using our horizontal blur shader
		//it will be placed into target A
		batch.draw(Shaders.blurTargetA.getColorBufferTexture(), 0, 0);

		//flush the Render.batch before ending target A
		batch.flush();

		//finish rendering target A
		Shaders.blurTargetA.end();
		
		//apply the blur only along Y-axis
		Shaders.ppBlur.setUniformf("dir", 0f, 1f);
		
		//draw the horizontally-blurred FBO B to the screen, applying the vertical blur as we go
		batch.draw(Shaders.blurTargetA.getColorBufferTexture(), 0, 0);
		
		batch.end();
	}
}

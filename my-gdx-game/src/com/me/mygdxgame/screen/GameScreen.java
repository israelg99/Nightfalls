package com.me.mygdxgame.screen;

import static com.me.mygdxgame.resources.Resources.LoadGame;
import static com.me.mygdxgame.resources.Resources.LoadResources;
import static com.me.mygdxgame.resources.Resources.map;
import static com.me.mygdxgame.resources.Resources.player;

import java.awt.Dimension;
import java.awt.Toolkit;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.me.mygdxgame.HUD.HUD;
import com.me.mygdxgame.game.Render;
import com.me.mygdxgame.game.Update;
import com.me.mygdxgame.graphics.Particles;
import com.me.mygdxgame.graphics.Shaders;
import com.me.mygdxgame.mainMenu.MainMenuMusic;
import com.me.mygdxgame.resources.Prefs;
import com.me.mygdxgame.sound.Ambience;
import com.me.mygdxgame.sound.BGM;
import com.me.mygdxgame.sound.SFX;
import com.me.mygdxgame.utility.GameSaver;
import com.me.mygdxgame.world.Physics;

public class GameScreen implements Screen {

	public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public final static int SizeX = Gdx.graphics.getWidth(), SizeY = Gdx.graphics.getHeight();
	//public final static int SizeX = 1280, SizeY = 720;

	public GameScreen(boolean loadGame, int slot) {

		System.out.println("The Game Screen has been Initialized!");
		
		System.err.println("Initializing the Assets needed for the game!");
		LoadResources();
		LoadGame(loadGame, slot);
		System.out.println("The Assets has been Initialized!");
		
		System.out.println("The Render class has been Initialized!");
		Render.Load();

		Gdx.input.setCursorCatched(true);
		Gdx.input.setInputProcessor(player);

		System.out.println("Loading Update class, Scheduling the world update task! Not in the render method!");

		Update.Load();
		
		System.out.println("Moving on to the render() method to render the game!");
	}

	public void render(float delta) {
		
		Render.clear();
		
		Update.updatePlayerCamera(player);
		
		Shaders.pass.begin();
		Shaders.pass.setUniformf("gameTime", map.getWorldTime(), map.getWorldTime(), map.getWorldTime(), 0.0f);
		Shaders.pass.end();

		Shaders.motionBlur.begin();
		Shaders.motionBlur.setUniformf("gameTime", map.getWorldTime(), map.getWorldTime(), map.getWorldTime(), 0.0f);
		Shaders.motionBlur.setUniformf("blurSize", Physics.getMotionBlur(player.speed));
		Shaders.motionBlur.end();
 
		Shaders.block.begin();
		Shaders.block.setUniformf("gameTime", map.getWorldTime(), map.getWorldTime(), map.getWorldTime(), 0.0f);
		Shaders.block.end();
		
		if(Prefs.getPPEffects()) {
			Render.renderFBO(delta);
			
			if(HUD.isInvShown() || HUD.isEscapeMenuShown()) {
				Render.ppBlurFBO();
			} else {
				Render.draw(delta);
			}
			
			Render.drawHUD(delta);
			
		} else {
			// Draw / Render
			Render.draw(delta);
		}
		
		// Update
		Update.update(delta);
	}

	public void resize(int width, int height) {

	}

	public void show() {
		// TODO Auto-generated method stub

	}

	public void hide() {
		if(!GameSaver.isSaved()) {
			System.err.println("Game is not being saved correctly, but we will do our best to save it :)");
			save();
		}
		dispose();
		System.out.println("Hided!");
	}

	public void dispose() {
		System.out.println("Desposing..");
		Render.batch.dispose();
		BGM.disposeAll();
		SFX.disposeAll();
		Ambience.disposeAll();
		HUD.dispose();
		Particles.dispose();
	}

	public void pause() {
		// TODO Auto-generated method stub

	}

	public void resume() {
		// TODO Auto-generated method stub

	}
	
	public static void exit() {
		save();
		MainMenuMusic.disposeAll();
		Gdx.app.exit();
	}
	
	public static void save() {
		GameSaver.SaveWorld(map);
		GameSaver.SavePlayer(player);

		Prefs.flush();
	}
}

package com.me.mygdxgame.HUD;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.me.mygdxgame.mainMenu.MainMenuMusic;
import com.me.mygdxgame.screen.GameScreen;
import com.me.mygdxgame.screen.MainMenuScreen;
import com.me.mygdxgame.screen.SettingsScreen;


public enum EscapeOption {
	
	ResumeGame {

		@Override
		public void trigger() {
			EscapeMenuHUD.disableEM();
			
		}
		
	},
	
	SettingsMenu {

		@Override
		public void trigger() {
			GameScreen.save();
			Gdx.input.setCursorCatched(false);
			((Game) Gdx.app.getApplicationListener()).setScreen(new SettingsScreen());
			EscapeMenuHUD.disableEM();
			MainMenuMusic.mmPlay();
			
		}
		
	},
	
	MainMenu {

		@Override
		public void trigger() {
			GameScreen.save();
			Gdx.input.setCursorCatched(false);
			((Game) Gdx.app.getApplicationListener()).setScreen(new MainMenuScreen());
			EscapeMenuHUD.disableEM();
			MainMenuMusic.mmPlay();
		}
		
	},
	
	Quit {

		@Override
		public void trigger() {
			GameScreen.exit();
			
		}
		
	};
	
	public abstract void trigger();
}

package com.me.mygdxgame;

import com.badlogic.gdx.Game;
import com.me.mygdxgame.resources.Resources;
import com.me.mygdxgame.screen.CompanySplashScreen;

public class MyGdxGame extends Game {
	
	public void create() {
		System.out.println("Initializing the game screen!");
		
		Resources.LoadMainMenuResources();
		setScreen(new CompanySplashScreen());
	}

}

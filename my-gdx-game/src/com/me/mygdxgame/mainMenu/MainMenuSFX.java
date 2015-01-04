package com.me.mygdxgame.mainMenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class MainMenuSFX {
	public static Sound click;

	public static void Load() {
		click = Gdx.audio.newSound(Gdx.files.internal("menu_sfx/click.wav"));
	}
	
	public static void playClick() {
		click.play();
	}

	public static void disposeAll() {
		click.dispose();
	}

}

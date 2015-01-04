package com.me.mygdxgame.resources;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class Prefs {
	
	private static Preferences getPrefs() {
		return Gdx.app.getPreferences("preferences");
	}
	private static Preferences getSettings() {
		return Gdx.app.getPreferences("settings");
	}
	
	public static void loadName() {
		getPrefs().putString("name", "Israel");
	}
	public static String getName() {
		return getPrefs().getString("name");
	}
	
	public static void setVolume(float vol) {
		getSettings().putFloat("volume", vol);
	}
	public static float getVolume() {
		return getSettings().getFloat("volume");
	}
	
	public static void setTextureRes(int tr) {
		getSettings().putInteger("texture_res", tr);
	}
	public static int getTextureRes() {
		return getSettings().getInteger("texture_res");
	}
	
	public static void setPPEffects(boolean ppe) {
		getSettings().putBoolean("PP_effects", ppe);
	}
	public static boolean getPPEffects() {
		return getSettings().getBoolean("PP_effects");
	}
	
	public static void flush() {
		getPrefs().flush();
		getSettings().flush();
	}
}

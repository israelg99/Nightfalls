package com.me.mygdxgame.sound;

import com.me.mygdxgame.resources.Prefs;

public class SoundUtil {
	public static float getVol(float vol) {
		return vol * Prefs.getVolume();
	}
}

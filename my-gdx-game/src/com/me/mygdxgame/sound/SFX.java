package com.me.mygdxgame.sound;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.me.mygdxgame.resources.Prefs;

public class SFX {
	private static Map<String, Music> sfx = new HashMap<String, Music>();
	private static Music ft1;
	private static Music ft2;
	private static Music ftGrass1;
	private static Music ftGrass2;
	private static Music ftWater1;
	private static Music ftWater2;
	private static Music ftWater3;
	private static Music ftSnow1;
	private static Music ftSnow2;
	private static Music ftSnow3;
	private static Music ftDesert1;
	private static Music ftDesert2;
	private static Music block_break;
	private static Music block_place;
	private static Music jump;
	private static Sound waterSplash;
	
	public final static int ftLength = 2, ftGrassLength = 2, ftWaterLength = 3, ftSnowLength = 3, ftDesertLength = 2;

	public static void Load() {
		ft1 = Gdx.audio.newMusic(Gdx.files.internal("sfx/footstep1.wav"));
		ft2 = Gdx.audio.newMusic(Gdx.files.internal("sfx/footstep2.wav"));
		ftGrass1 = Gdx.audio.newMusic(Gdx.files.internal("sfx/ftGrass.wav"));
		ftGrass2 = Gdx.audio.newMusic(Gdx.files.internal("sfx/ftGrass2.wav"));
		block_break = Gdx.audio.newMusic(Gdx.files.internal("sfx/block_break.wav"));
		block_place = Gdx.audio.newMusic(Gdx.files.internal("sfx/block_place.mp3"));
		jump = Gdx.audio.newMusic(Gdx.files.internal("sfx/jump.wav"));
		ftWater1 = Gdx.audio.newMusic(Gdx.files.internal("sfx/ftWater1.ogg"));
		ftWater2 = Gdx.audio.newMusic(Gdx.files.internal("sfx/ftWater2.ogg"));
		ftWater3 = Gdx.audio.newMusic(Gdx.files.internal("sfx/ftWater3.ogg"));
		ftSnow1 = Gdx.audio.newMusic(Gdx.files.internal("sfx/snow2.wav"));
		ftSnow2 = Gdx.audio.newMusic(Gdx.files.internal("sfx/snow.wav"));
		ftSnow3 = Gdx.audio.newMusic(Gdx.files.internal("sfx/snow1.wav"));
		ftDesert1 = Gdx.audio.newMusic(Gdx.files.internal("sfx/desert.mp3"));
		ftDesert2 = Gdx.audio.newMusic(Gdx.files.internal("sfx/desert1.ogg"));
		waterSplash = Gdx.audio.newSound(Gdx.files.internal("sfx/waterSplash.wav"));

		ft1.setVolume(SoundUtil.getVol(0.2f));
		ft2.setVolume(SoundUtil.getVol(0.2f));
		
		ftGrass1.setVolume(SoundUtil.getVol(1f));
		ftGrass2.setVolume(SoundUtil.getVol(0.1f));
		
		block_break.setVolume(SoundUtil.getVol(0.1f));
		block_place.setVolume(SoundUtil.getVol(0.1f));
		
		jump.setVolume(SoundUtil.getVol(0.1f));
		
		ftWater1.setVolume(SoundUtil.getVol(0.5f));
		ftWater2.setVolume(SoundUtil.getVol(0.5f));
		ftWater3.setVolume(SoundUtil.getVol(0.5f));
		
		ftSnow1.setVolume(SoundUtil.getVol(0.15f));
		ftSnow2.setVolume(SoundUtil.getVol(0.15f));
		ftSnow3.setVolume(SoundUtil.getVol(0.15f));
		
		ftDesert1.setVolume(SoundUtil.getVol(0.3f));
		ftDesert2.setVolume(SoundUtil.getVol(0.4f));
		
		sfx.put("ft1", ft1);
		sfx.put("ft2", ft2);
		sfx.put("ftGrass1", ftGrass1);
		sfx.put("ftGrass2", ftGrass2);
		sfx.put("block_break", block_break);
		sfx.put("block_place", block_place);
		sfx.put("jump", jump);
		sfx.put("ftWater1", ftWater1);
		sfx.put("ftWater2", ftWater2);
		sfx.put("ftWater3", ftWater3);
		sfx.put("ftSnow1", ftSnow1);
		sfx.put("ftSnow2", ftSnow2);
		sfx.put("ftSnow3", ftSnow3);
		sfx.put("ftDesert1", ftDesert1);
		sfx.put("ftDesert2", ftDesert2);
	}
	
	public static void play(String s) {
		if(Prefs.getVolume() > 0) {
			if(sfx.get(s) != null) {
				sfx.get(s).play();
			}
		}
	}
	
	public static void waterSplash() {
		if(Prefs.getVolume() > 0) {
			waterSplash.play();
		}
	}

	public static void disposeAll() {
		ft1.dispose();
		ft2.dispose();
	}

}

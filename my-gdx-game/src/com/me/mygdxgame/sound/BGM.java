package com.me.mygdxgame.sound;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.me.mygdxgame.resources.Prefs;

public class BGM {

	private static Map<String, Music> musics = new HashMap<String, Music>();
	public static Music bgm1;
	public static Music bgm2;
	public static Music bgm3;

	private static int bgmPlaying;
	private final static int bgmLength = 3;

	public static void Load() {
		bgm1 = Gdx.audio.newMusic(Gdx.files.internal("music/bgMusic1.mp3"));
		bgm2 = Gdx.audio.newMusic(Gdx.files.internal("music/bgMusic2.mp3"));
		bgm3 = Gdx.audio.newMusic(Gdx.files.internal("music/bgMusic3.mp3"));

		bgm1.setVolume(SoundUtil.getVol(0.1f));
		bgm2.setVolume(SoundUtil.getVol(0.1f));
		bgm3.setVolume(SoundUtil.getVol(0.1f));

		musics.put("bgm1", bgm1);
		musics.put("bgm2", bgm2);
		musics.put("bgm3", bgm3);

		bgmPlaying = 1;
	}

	public static void init() {
		if(Prefs.getVolume() > 0) {
			play(bgmPlaying);
		}
	}

	public static void play(int n) {
		if(Prefs.getVolume() > 0) {
			if (musics.get("bgm" + n) != null) {
				stopAll();
				bgmPlaying = n;
				musics.get("bgm" + n).play();
			}
		}
	}

	public static void update() {
		if(Prefs.getVolume() > 0) {
			if (musics.get("bgm" + bgmPlaying) != null && !musics.get("bgm" + bgmPlaying).isPlaying()) {
				bgmPlaying = bgmPlaying < bgmLength ? bgmPlaying + 1 : 1;
				play(bgmPlaying);
			}
		}

	}

	public static void stopAll() {
		bgm1.stop();
		bgm2.stop();
		bgm3.stop();
	}

	public static void disposeAll() {
		bgm1.dispose();
		bgm2.dispose();
		bgm3.dispose();
	}

}

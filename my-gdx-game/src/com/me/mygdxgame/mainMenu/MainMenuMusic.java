package com.me.mygdxgame.mainMenu;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class MainMenuMusic {

	private static Map<String, Music> musics = new HashMap<String, Music>();
	public static Music mmm1;
	public static Music mmm2;
	public static Music mmm3;

	private static int bgmPlaying;
	private final static int bgmLength = 3;

	public static void Load() {
		mmm1 = Gdx.audio.newMusic(Gdx.files.internal("menu_music/GATG.mp3"));
		mmm2 = Gdx.audio.newMusic(Gdx.files.internal("menu_music/TIME.mp3"));
		mmm3 = Gdx.audio.newMusic(Gdx.files.internal("menu_music/TYSA.mp3"));

		mmm1.setVolume(0.3f);
		mmm2.setVolume(0.3f);
		mmm3.setVolume(0.3f);

		musics.put("mmm1", mmm1);
		musics.put("mmm2", mmm2);
		musics.put("mmm3", mmm3);

		bgmPlaying = 1;
	}

	public static void init() {
		setAllLoop();
		play(3);
	}

	public static void play(int n) {
		if (musics.get("mmm" + n) != null) {
			stopAll();
			bgmPlaying = n;
			musics.get("mmm" + n).play();
		}
	}
	public static void stop(int n) {
		if (musics.get("mmm" + n) != null) {
			musics.get("mmm" + n).stop();
		}
	}

	public static void update() {
		if (musics.get("mmm" + bgmPlaying) != null && !musics.get("mmm" + bgmPlaying).isPlaying()) {
			bgmPlaying = bgmPlaying < bgmLength ? bgmPlaying + 1 : 1;
			play(bgmPlaying);
		}

	}
	
	public static void setLoop(int n) {
		if (musics.get("mmm" + n) != null) {
			musics.get("mmm" + n).setLooping(true);
		}
	}
	
	public static void setAllLoop() {
		for(int i = 1; i <= bgmLength; i++) {
			setLoop(i);
		}
	}

	public static void stopAll() {
		mmm1.stop();
		mmm2.stop();
		mmm3.stop();
	}

	public static void disposeAll() {
		mmm1.dispose();
		mmm2.dispose();
		mmm3.dispose();
	}
	public static void mmStop() {
		stopAll();
	}
	public static void mmPlay() {
		play(bgmPlaying);
	}

}

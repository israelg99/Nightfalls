package com.me.mygdxgame.sound;

import static com.me.mygdxgame.resources.Resources.player;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.me.mygdxgame.math.PosHandle;
import com.me.mygdxgame.world.Time;

public class Ambience {
	private static Map<String, Music> sounds = new HashMap<String, Music>();
	public static float timeForSounds = 15;
	
	// Birds
	public static Music bird1;
	public static Music bird2;
	public static Music bird3;

	private static int birdPlaying;
	private final static int birdLength = 3;
	
	// Crows
	public static Music crow1;
	public static Music crow2;
	public static Music crow3;

	private static int crowPlaying;
	private final static int crowLength = 3;
	
	public static void Load() {
		bird1 = Gdx.audio.newMusic(Gdx.files.internal("ambience/bird_chirp.wav"));
		bird2 = Gdx.audio.newMusic(Gdx.files.internal("ambience/bird_chirp1.wav"));
		bird3 = Gdx.audio.newMusic(Gdx.files.internal("ambience/bird_chirp2.wav"));

		bird1.setVolume(SoundUtil.getVol(0.4f));
		bird2.setVolume(SoundUtil.getVol(0.8f));
		bird3.setVolume(SoundUtil.getVol(0.8f));

		sounds.put("bird1", bird1);
		sounds.put("bird2", bird2);
		sounds.put("bird3", bird3);

		birdPlaying = 1;
		
		crow1 = Gdx.audio.newMusic(Gdx.files.internal("ambience/crow1.wav"));
		crow2 = Gdx.audio.newMusic(Gdx.files.internal("ambience/crow2.wav"));
		crow3 = Gdx.audio.newMusic(Gdx.files.internal("ambience/crow3.wav"));

		crow1.setVolume(SoundUtil.getVol(0.5f));
		crow2.setVolume(SoundUtil.getVol(0.5f));
		crow3.setVolume(SoundUtil.getVol(0.5f));

		sounds.put("crow1", crow1);
		sounds.put("crow2", crow2);
		sounds.put("crow3", crow3);

		crowPlaying = 1;
	}
	
	public static void play(String n) {
		if (sounds.get(n) != null) {
			stopAll();
			sounds.get(n).play();
		}
	}
	
	public static void update(float time) {
		if(PosHandle.getBlockY(player.getX()) < 55) {
			if(time > Time.NIGHT) {
				play("bird" + birdPlaying);
				birdPlaying = birdPlaying >= birdLength ? 1 : birdPlaying + 1;
				crowPlaying = 1;
			} else if(time < Time.NIGHT) {
				play("crow" + crowPlaying);
				crowPlaying = crowPlaying >= crowLength ? 1 : crowPlaying + 1;
				birdPlaying = 1;
			}
		}
	}

	public static void stopAll() {
		bird1.stop();
		bird2.stop();
		bird3.stop();
		crow1.stop();
		crow2.stop();
		crow3.stop();
	}

	public static void disposeAll() {
		bird1.dispose();
		bird2.dispose();
		bird3.dispose();
		crow1.dispose();
		crow2.dispose();
		crow3.dispose();
	}
}

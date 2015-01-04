package com.me.mygdxgame.game;

import static com.me.mygdxgame.resources.Resources.char_sprite;
import static com.me.mygdxgame.resources.Resources.map;
import static com.me.mygdxgame.resources.Resources.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.me.mygdxgame.player.Player;
import com.me.mygdxgame.resources.Prefs;
import com.me.mygdxgame.sound.Ambience;
import com.me.mygdxgame.sound.BGM;
import com.me.mygdxgame.utility.GameSaver;
import com.me.mygdxgame.utility.InputControls;
import com.me.mygdxgame.world.Physics;

public class Update {
	
	private static float stateTime;
	
	public static void Load() {
		
		stateTime = 0f;
		
		Timer.schedule(new Task() {

			public void run() {
				// Do your work
				map.updateWorld(player.getX(), player.getY());
			}
		}, Physics.updateWaterSec, Physics.updateWaterSec);
		
		Timer.schedule(new Task() {

			public void run() {
				// Do your work
				Ambience.update(map.getWorldTime());
			}
		}, Ambience.timeForSounds, Ambience.timeForSounds);
	}
	
	public static void updatePlayerCamera(Player player) {
		player.camera.update();
	}
	
	public static void update(float delta) {
		InputUpdate();
		GameUpdate(delta);
	}

	private static void InputUpdate() {

		if (Gdx.input.isKeyPressed(InputControls.DETAILS)) {
			Render.showDet = true;
		} else {
			Render.showDet = false;
		}

		if (Gdx.input.isKeyPressed(InputControls.ZOOM)) {
			player.camera.zoom = 3;
		} else {
			player.camera.zoom = 1;
		}
		
	}

	private static void GameUpdate(float delta) {
		
		stateTime += delta;
		Render.walkFrame = char_sprite.getWalkAnimation(player.flip).getKeyFrame(stateTime, true);
		Render.runFrame = char_sprite.getRunAnimation(player.flip).getKeyFrame(stateTime, true);
		
		player.update();

		BGM.update();

		map.updateTime();
		
	}
}

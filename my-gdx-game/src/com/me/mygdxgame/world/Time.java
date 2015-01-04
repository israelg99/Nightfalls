package com.me.mygdxgame.world;

import java.io.Serializable;

public class Time implements Serializable {

	private static final long serialVersionUID = 1L;
	private float time = 0.0f;
	private static float timeAdd = 0.00001f;
	private static float timeSub = 0.00001f;
	private static float maxTime = 0.0f;
	private static float minTime = -0.9f;
	private float timePaused = 0.0f;
	private static float toPauseTime = 0.3f;
	private static float addTimePaused = 0.00001f;
	
	private boolean dayFlip = false;
	private boolean pauseDay = false;
	private int dayCount = 1;
	
	public static final float NIGHT = -0.5f;
	
	public Time() {
		time = maxTime;
		pauseDay = true;
	}
	
	public void update() {
		if(!pauseDay) { 
			if(time >= minTime && !dayFlip) {
				time -= timeAdd;
			} else if(time <= maxTime && dayFlip) {
				time += timeSub;
			}
			
			dayUpdate();
			timePaused = 0.0f;
		} else {
			timePaused += addTimePaused;
			if(timePaused >= toPauseTime) { pauseDay = false; timePaused = 0.0f; }
		}
	}
	
	private void dayUpdate() {
		if((time >= maxTime && dayFlip) || (time <= minTime && !dayFlip)) {
			pauseDay = true;
		}
		
		if(time >= maxTime) {
			dayFlip = false;
			dayCount++;
		} else if(time <= minTime) {
			dayFlip = true;
		}
	}
	
	public int getDays() {
		return dayCount;
	}
	
	public float getTime() {
		return time;
	}
}

package com.me.mygdxgame.world;

import static com.me.mygdxgame.resources.Resources.map;

import com.me.mygdxgame.graphics.Particles;
import com.me.mygdxgame.math.PosHandle;
import com.me.mygdxgame.world.objects.Block;

public class Physics {
	private static int gravSpeed;
	private static float pullSpeed;
	public static final int XColValue = 30;
	public static final int YColValue = 30;
	public static final float updateWaterSec = 0.3f;
	public static final int waterPull = 2;
	public static final float motionBlurDivide = 30000.0f;
	
	public static void LoadPhy(int gs, float ps) {
		gravSpeed = gs;
		pullSpeed = ps;
	}
	
	public static float AddGravityPull(float grav) {
		return grav * pullSpeed;
	}
	
	public static int resetGravity() {
		return gravSpeed;
	}
	
	public static void waterPhyStream(int p) {
		if(map.map.get(p).ID == 9) {
			int mapPos = 0;
			if(map.map.get(p + map.width).ID == 0) {
				mapPos = p + map.width;
				map.map.get(mapPos).ID = 9; map.map.get(p).ID = 0; 
				Particles.add("waterMove", PosHandle.toRealX(mapPos % map.width) + Block.WIDTH / 2, PosHandle.toRealY(mapPos / map.width) + Block.HEIGHT / 3);
			}
			if(map.map.get(p + map.width).ID == 9) { map.map.get(p + map.width).ID = 9;}
			else if(map.map.get(p + 1).ID == 0) {
				mapPos = p + 1;
				map.map.get(mapPos).ID = 9; 
				Particles.add("waterMoveRight", PosHandle.toRealX(mapPos % map.width) + Block.WIDTH / 2, PosHandle.toRealY(mapPos / map.width) + Block.HEIGHT / 3);
			}
			else if(map.map.get(p - 1).ID == 0) {
				mapPos = p - 1;
				map.map.get(mapPos).ID = 9;
				Particles.add("waterMoveLeft", PosHandle.toRealX(mapPos % map.width) + Block.WIDTH / 2, PosHandle.toRealY(mapPos / map.width) + Block.HEIGHT / 3);
			}
			else {
				System.err.println("The water cannot be animated.. it got to its final position");
				return;
			}
			map.map.get(mapPos).breakLevel = 0; 
		} else {
			System.err.println("The block that was sent to the water update method wasn't water");
			return;
		}
	}
	
	public static void waterPhySea(int p) {
		if(map.map.get(p).ID == 9) {
			int mapPos = 0;
			if(map.map.get(p + map.width).ID == 0) { 
				mapPos = p + map.width;
				map.map.get(mapPos).ID = 9;
				map.map.get(p).ID = 0;
			}
			else if(map.map.get(p + 1).ID == 0) { mapPos = p + 1; map.map.get(mapPos).ID = 9; }
			else if(map.map.get(p - 1).ID == 0) { mapPos = p - 1; map.map.get(mapPos).ID = 9; }
			else {
				System.err.println("The water cannot be animated.. it got to its final position");
				return;
			}
			map.map.get(mapPos).breakLevel = 0;
		} else {
			System.err.println("The block that was sent to the water update method wasn't water");
		}
	}
	
	public static boolean isWaterCanBeStreamed(int p) {
		if(map.map.get(p).ID == 9) {
			if(map.map.get(p + map.width).ID == 0 || map.map.get(p + 1).ID == 0 || map.map.get(p - 1).ID == 0) {
				return true;
			} else {
				return false;
			}
		} else {
			System.err.println("The block that was sent to the water update method wasn't water");
			return false;
		}
	}
	
	public static boolean isWaterCanBeSea(int p) {
		if(map.map.get(p).ID == 9) {
			if(map.map.get(p + map.width).ID == 0 || map.map.get(p + 1).ID == 0 || map.map.get(p - 1).ID == 0) {
				return true;
			} else {
				return false;
			}
		} else {
			System.err.println("The block that was sent to the water update method wasn't water");
			return false;
		}
	}
	
	public static float getMotionBlur(int speed) {
		if(speed != 0) { // 1 / 0 its Infinity
			return speed / motionBlurDivide;
		} else {
			return 0;
		}
	}
}

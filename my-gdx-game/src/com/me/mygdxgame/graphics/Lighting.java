package com.me.mygdxgame.graphics;

import com.me.mygdxgame.world.World;

public class Lighting {

	private static final int sunLine = 5;
	private static final int sunLimit = 55;
	public static final float NO_LIGHT = 0.1f;
	public static final float SUN_LIGHT = 1.0f;
	public static final float WATER_LIGHT = 0.7f;
	private static final float divide = 0.1f;

	public static float checkLight(int y, int x, World world) {
		float light = checkSunLight(y, x, world);
		if (light == SUN_LIGHT) {
			return light;
		} else {
			light = checkSideBlockLight(y, x, world);
			return light;
		}
	}

	public static float checkSunLight(int y, int x, World world) {
		int up = 1;
		while (y - up > sunLine) {
			if (world.map.get((y - up) * world.width + (x)).ID != 0 || up >= sunLimit) {
				return NO_LIGHT;
			}
			up++;
		}
		return SUN_LIGHT;

	}

	public static float checkSideBlockLight(int y, int x, World world) {
		float max;
		max = getLightDivided(world.map.get((y - 1) * world.width + (x)).light);
		max = getLightDivided(world.map.get((y + 1) * world.width + (x)).light) > max ? getLightDivided(world.map.get((y + 1) * world.width + (x)).light) : max;
		max = getLightDivided(world.map.get((y) * world.width + (x - 1)).light) > max ? getLightDivided(world.map.get((y) * world.width + (x - 1)).light) : max;
		max = getLightDivided(world.map.get((y) * world.width + (x + 1)).light) > max ? getLightDivided(world.map.get((y) * world.width + (x + 1)).light) : max;

		if (world.map.get((y) * world.width + (x)).ID == 9 && max < WATER_LIGHT) {
			return WATER_LIGHT;
		}

		return max;
	}

	public static float getLightDivided(float l) {
		float temp = Float.parseFloat(String.format("%.1f", l - divide));
		return temp > NO_LIGHT ? temp : NO_LIGHT;
	}
}

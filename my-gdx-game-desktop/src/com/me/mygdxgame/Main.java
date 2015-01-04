package com.me.mygdxgame;

import java.awt.Dimension;
import java.awt.Toolkit;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		System.out.println("Initializing the Window!");
		LwjglApplicationConfiguration cfg;
		cfg = new LwjglApplicationConfiguration();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		cfg.title = "My GDX Game";
		cfg.useGL20 = true;
		cfg.vSyncEnabled = true;
		//cfg.width = (int) 1280;
		//cfg.height = (int) 720;
		cfg.width = (int) screenSize.getWidth();
		cfg.height = (int) screenSize.getHeight();
		cfg.resizable = false;
		cfg.fullscreen = true;
		System.err.println("The Window has Initialized");
		
		System.out.println("Calling the Game!");
		new LwjglApplication(new MyGdxGame(), cfg);
		
	}
}

/* Sound Left :
 * 
 * 1. Jump V
 * 2. Water V
 * 3. Ambience - not needed.
 * 4. Block break and place sounds for specific blocks - not now.
 *
 *   Bugs :
 * 
 * Possible to climb 3 height block wall if jumped several times. - It happens because we have the phy.xcol if we had the walkspeed instead it will work, we can also make the jump height no round.. but it will make the game limited.
 * isMidAir being true while you are on a edge of a block.. and you fall down because of it. - It happens because the blocks are physically smaller then how they look in game.
 * 
 * Fixes :
 * 
 * - Make the blocks physically big as they look in game.
 *   So its like the block size has changed but it didn't, so we shouldn't touch the render or the actual block size.
 * One simple fix.
 * 
 * NEW !!! - Finish all the collision plan!
 * 
 * 
 * FIXEDDDD!!!!! AND FINISHED!
 * 
 * ===========================================
 * Work for tomorrow : 28/12/13 ™ § § █ § § ™
 * -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
 * 
 * New Bug : You can get out of the map's bounds, and the game crashes afterwards.
 * 
 * Finish the look feature, everything is great except that it goes out of sight.
 * 
 * FINISHEDDD!!!!
 * AND FIXED MUIAHUAHUAHUAH!
 * 
 * Now World Generation!! 29/12/13
 * After midnight 30/12/13 Still working on the world generation thing! got ores and new textures aayayayay!
 * 
 * Our tasks for world generation :
 * Make hills so the surface won't be too smooth.
 * Add trees with leaves and everything.
 * Add more things to the surface - bushes and stuff.
 * 
 * BUG FIX : If you change fall speed to 15 or something like that it bugs like hell!!! 
 * WaterPull doesnt work on falling speed.. u can suddenly fall down from the map. ******************************************
 * 
 *  FIXED THE LIGHTING BUG AFTER A WEEK!! NEVER GIVE UP TO JAVA!!!!!!! NOW FIND A MORE EFFECTIVE WAY THAN WE USE NOW TO RENDER LIGHT!!!! OHAHAHA
 *  
 *  MAKE CLASS FOR INPUTS.. FUCKKK
 * 
 */

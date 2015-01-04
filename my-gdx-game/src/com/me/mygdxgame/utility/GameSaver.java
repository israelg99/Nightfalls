package com.me.mygdxgame.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.me.mygdxgame.game.Render;
import com.me.mygdxgame.math.PosHandle;
import com.me.mygdxgame.player.Player;
import com.me.mygdxgame.resources.Resources;
import com.me.mygdxgame.world.Physics;
import com.me.mygdxgame.world.World;
import com.me.mygdxgame.world.WorldFile;

public class GameSaver {
	
	private static String path;
	private static boolean wSaved = false;
	private static boolean pSaved = false;
	
	public static void setPath(String p) {
		path = p;
	}
	
	public static void SaveWorld(World world) {
		WorldFile wf = new WorldFile(world);
		
		new File(path).mkdirs();
        
		FileOutputStream worldOut;
		try {
			worldOut = new FileOutputStream(path + "world.wow");
			ObjectOutputStream outW = new ObjectOutputStream(worldOut);
			outW.writeObject(wf);
			outW.close();
			worldOut.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		wSaved = true;
	}
	
	public static void SavePlayer(Player player) {
		player.controlPlayer = true;
		FileOutputStream playerOut;
		try {
			playerOut = new FileOutputStream(path + "player.op");
			ObjectOutputStream outP = new ObjectOutputStream(playerOut);
			outP.writeObject(player);
			outP.close();
			playerOut.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		pSaved = true;
	}
	
	public static World getWorld(boolean load) {
		
		if(load) {
			try {
				FileInputStream worldIn = new FileInputStream(path + "world.wow");
		        ObjectInputStream inW = new ObjectInputStream(worldIn);
		        WorldFile wf = (WorldFile) inW.readObject();
		        inW.close();
		        worldIn.close();
		        
		        return wf.getWorld();
		        
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		World mappy = new World(1000, 1000);
		mappy.GenerateWorld(1);
		
		return mappy;
	}
	
	public static Player getPlayer(boolean load) {
		
		Player tempP;
		
		if(load) {
			try {
				FileInputStream playerIn = new FileInputStream(path + "player.op");
		        ObjectInputStream inP = new ObjectInputStream(playerIn);
		        tempP = (Player) inP.readObject();
		        inP.close();
		        playerIn.close();
		        
		        tempP.camera = new OrthographicCamera();
		        tempP.camera.setToOrtho(true, Render.ResX, Render.ResY);
		        
		        tempP.camera.position.x = tempP.getX();
		        tempP.camera.position.y = tempP.getY();
				
		        tempP.flip = "left";
		        
		        return tempP;
		        
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch(ClassNotFoundException c) {
				c.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		tempP = new Player(PosHandle.toRealX(Resources.map.width / 2), 3000);
		tempP.grav = Physics.resetGravity();
		
		return tempP;
	}
	
	public static boolean isSaved() {
		return wSaved && pSaved ? true : false;
	}
}

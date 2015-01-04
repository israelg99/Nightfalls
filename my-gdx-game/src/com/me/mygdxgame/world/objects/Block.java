package com.me.mygdxgame.world.objects;
import static com.me.mygdxgame.resources.Resources.map;
import static com.me.mygdxgame.resources.Resources.player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.me.mygdxgame.resources.Prefs;
import com.me.mygdxgame.sound.SFX;
import com.me.mygdxgame.utility.TextureHandle;

public class Block {
	
	private static List<GeneralBlock> blocks = new ArrayList<GeneralBlock>();
	private static List<GeneralBlock> leaf = new ArrayList<GeneralBlock>();
	private static List<GeneralBlock> collidable = new ArrayList<GeneralBlock>();
	private static List<GeneralBlock> placedOn = new ArrayList<GeneralBlock>();
	
	public final static int WIDTH = 100;
	public final static int HEIGHT = 100;
	public int ID;
	public float light = 0.3f;
	public int breakLevel = 0;
	private int breakWall = 100;
	private int breakWallDefault = 100;
	
	public int dropID;
	public int amountDrop;
	
	public static void Load() {
		
		String res = Prefs.getTextureRes() == 16 ? "" : "X2"; // I am so proud of my self! how did i came up with this :D
		
		blocks.add(new GeneralBlock(null, 0));
		blocks.add(new GeneralBlock(TextureHandle.getSpriteFlippedY("blocks/grass"+ res + ".png"), 1));
		blocks.add(new GeneralBlock(TextureHandle.getSpriteFlippedY("blocks/stone"+ res + ".png"), 2));
		blocks.add(new GeneralBlock(TextureHandle.getSpriteFlippedY("blocks/coal"+ res + ".png"), 3));
		blocks.add(new GeneralBlock(TextureHandle.getSpriteFlippedY("blocks/iron"+ res + ".png"), 4));
		blocks.add(new GeneralBlock(TextureHandle.getSpriteFlippedY("blocks/gold"+ res + ".png"), 5));
		blocks.add(new GeneralBlock(TextureHandle.getSpriteFlippedY("blocks/dirt"+ res + ".png"), 6));
		blocks.add(new GeneralBlock(TextureHandle.getSpriteFlippedY("blocks/tree"+ res + ".png"), 7));
		blocks.add(new GeneralBlock(TextureHandle.getSpriteFlippedY("blocks/leaf"+ res + ".png"), 8));
		blocks.add(new GeneralBlock(TextureHandle.getSpriteFlippedY("blocks/water"+ res + ".png"), 9));
		blocks.add(new GeneralBlock(TextureHandle.getSpriteFlippedY("blocks/snow"+ res + ".png"), 10));
		blocks.add(new GeneralBlock(TextureHandle.getSpriteFlippedY("blocks/snowLeaf"+ res + ".png"), 11, 8));
		blocks.add(new GeneralBlock(TextureHandle.getSpriteFlippedY("blocks/desert"+ res + ".png"), 12));
		blocks.add(new GeneralBlock(TextureHandle.getSpriteFlippedY("blocks/desertLeaf"+ res + ".png"), 13));
		blocks.add(new GeneralBlock(TextureHandle.getSpriteFlippedY("blocks/gravel"+ res + ".png"), 14));
		
		leaf.add(blocks.get(8));
		leaf.add(blocks.get(11));
		leaf.add(blocks.get(13));
		
		collidable.add(blocks.get(0));
		collidable.add(blocks.get(7));
		collidable.add(blocks.get(9));
		
		placedOn.add(blocks.get(0));
		placedOn.add(blocks.get(9));
	}
	
	public Block(int id) {
		ID = id;
		if(ID != 0) {
			dropID = getDropID(id);
			amountDrop = getAmount(id);
		}
	}
	public Block(int id, float l) {
		this(id);
		light = l;
	}
	
	public static boolean isAir(int id) {
		if(id == 0) {
			return true;
		} else {
			return false;
		}
	}
	public boolean isAir() {
		if(ID == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public static List<GeneralBlock> getBlocks() {
		return blocks;
	}
	
	public static boolean has(List<GeneralBlock> list, int id) {
	    for (GeneralBlock gb : list) {
	        if (gb.id == id) {
	            return true;
	        }
	    }
	    return false;
	}
	
	public static boolean isLeaf(int id) {
	    return has(leaf, id);
	}
	public boolean isLeaf() {
		return has(leaf, ID);
	}
	public static boolean isCollidable(int id) {
		return !has(collidable, id);
	}
	public boolean isCollidable() {
		return !has(collidable, ID);
	}
	public static boolean canBePlacedOn(int id) {
		return has(placedOn, id);
	}
	public boolean canBePlacedOn() {
		return has(placedOn, ID);
	}

	
	public Sprite getBlockTexture() {
		return getBlockTexture(ID);
	}
	public static Sprite getBlockTexture(int id) {
		return blocks.get(id).tex;
	}
	
	public static int getDropID(int id) {
		return blocks.get(id).dropID;
	}
	public static int getAmountMin(int id) {
		return blocks.get(id).amountDropMin;
	}
	public static int getAmountMax(int id) {
		return blocks.get(id).amountDropMax;
	}
	public static int getAmount(int id) {
		if(blocks.get(id).amountDropMax - blocks.get(id).amountDropMin <= 0) {
			return blocks.get(id).amountDropMin;
		} else {
			return new Random().nextInt(blocks.get(id).amountDropMax - blocks.get(id).amountDropMin) + blocks.get(id).amountDropMin;
		}
	}
		
	public void breakBlock(int x, int y) {
		if(breakLevel < BlockBreak.levelSize) {
			if(breakWall - player.breakSpeed <= 0) {
				breakLevel++;
				breakWall = breakWallDefault;
			} else {
				breakWall -= player.breakSpeed;
			}
		} else {
			SFX.play("block_break");
			map.setDropped(ID, x, y);
			ID = 0;
			return;
		}
	}
	
	public boolean isBreaking(int mapPos) {
		if(breakLevel > 0 && player.isBreaking && player.getLookPos() == mapPos) {
			return true;
		} else {
			breakLevel = 0;
			return false;
		}
	}
}

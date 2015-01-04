package com.me.mygdxgame.world;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.me.mygdxgame.game.Render;
import com.me.mygdxgame.graphics.Lighting;
import com.me.mygdxgame.math.PosHandle;
import com.me.mygdxgame.world.objects.Block;
import com.me.mygdxgame.world.objects.BlockBreak;
import com.me.mygdxgame.world.objects.Drop;

public class World {
	public List<Block> map;
	public List<String> bioms = new ArrayList<String>();
	public int width;
	public int height;
	public int widthBiom;
	public int heightBiom;
	public int widthBlocks;
	public int heightBlocks;
	public Random num = new Random();
	public int rarity = 10, rCoal = 10, rIron = 15, rGold = 60, hillH = 10, rHill = 5, rHillStone = 8, rHillDirt = 5, rTree = 5, treeH = 8, leefH = 3, leefMin = 2, rWaterStream = 27;
	public int update_time = 500;
	public static final int chunk = Render.ResX / Block.WIDTH + 3;
	public static final int addPart = 100;
	private int undergroundLevel = 50;
	private Time time;
	private List<Drop> drops = new ArrayList<Drop>();
	
	public World(int w, int h) {
		map = new ArrayList<Block>();
		width = w;
		height = h;
		widthBiom = w / BiomGeneration.BIOM_WIDTH;
		heightBiom = h / BiomGeneration.BIOM_HEIGHT;
		widthBlocks = PosHandle.toRealX(width);
		heightBlocks = PosHandle.toRealY(height);
		time = new Time();
	}
	
	/* Lighting Bug :
	 *  If we wish to render light to our world we must know how the lighting bug works and what his rules.
	 *  so first thing, we cant change the uniform in the loop it fucks it all up, we must change it before the loop.
	 *  which leads us to multiple loops, loop for every light, for example 4 loops for those lights (0.3f, 0.6f, 0.8f, 1.0f).
	 *  in the main loop we store all the position of the lights into arrays.. 
	 *  you get the idea..
	 *  
	 *  BY THE WAY - think about changing the project to our fighting style game.. we will leave this project as it is so we can continue later as we wish.
	 * 
	 */
	
	/*public void renderMap(SpriteBatch batch, int posx, int posy) {
		List<Integer> sunBlocks = new ArrayList<Integer>();
		List<Integer> secondBlocks = new ArrayList<Integer>();
		List<Integer> thirdBlocks = new ArrayList<Integer>();
		List<Integer> fourBlocks = new ArrayList<Integer>();
		for(int y = -(chunk / 2); y < chunk / 2; y++) {
			for(int x = -(chunk / 2); x < chunk / 2; x++) {
				int mapPosY = (y + PosHandle.getBlockY(posy));
				int mapPosX = (x + PosHandle.getBlockX(posx));
				int mapPos = (mapPosY) * width + (mapPosX);
				
				float light = Lighting.checkLight(mapPosY, mapPosX, this);
				map[mapPos].light = light;
				
				// Main and NO_LIGHT
				if(mapPos < width * height && mapPosX < width && mapPosY < height && mapPosX > 0 && mapPosY > 0 && mapPos > 0 && map[mapPos].ID != 0) {
					if( light == Lighting.SUN_LIGHT) { sunBlocks.add(mapPos); }
					else if(light == Lighting.L_SECOND) { secondBlocks.add(mapPos); }
					else if(light == Lighting.L_THIRD) { thirdBlocks.add(mapPos); }
					else if(light == Lighting.L_FOUR) { fourBlocks.add(mapPos); }
					
					batch.draw(map[mapPos].TEXTURE, (mapPosX * Block.WIDTH), (mapPosY * Block.HEIGHT), Block.WIDTH, Block.HEIGHT);
				}
			}
		}
		
		// SUN LIGHT
		for(int i = 0; i < sunBlocks.size(); i++) {
			batch.draw(map[sunBlocks.get(i)].TEXTURE, ((sunBlocks.get(i) % width) * Block.WIDTH), ((sunBlocks.get(i) / width) * Block.HEIGHT), Block.WIDTH, Block.HEIGHT);
		}
		
		// SECOND LIGHT
		for(int i = 0; i < secondBlocks.size(); i++) {
			batch.draw(map[secondBlocks.get(i)].TEXTURE, ((secondBlocks.get(i) % width) * Block.WIDTH), ((secondBlocks.get(i) / width) * Block.HEIGHT), Block.WIDTH, Block.HEIGHT);
		}
		
		// THIRD LIGHT
		for(int i = 0; i < thirdBlocks.size(); i++) {
			batch.draw(map[thirdBlocks.get(i)].TEXTURE, ((thirdBlocks.get(i) % width) * Block.WIDTH), ((thirdBlocks.get(i) / width) * Block.HEIGHT), Block.WIDTH, Block.HEIGHT);
		}
		
		// FOUR LIGHT
		for(int i = 0; i < fourBlocks.size(); i++) {
			batch.draw(map[fourBlocks.get(i)].TEXTURE, ((fourBlocks.get(i) % width) * Block.WIDTH), ((fourBlocks.get(i) / width) * Block.HEIGHT), Block.WIDTH, Block.HEIGHT);
		}
	}*/
	
	public void renderMap(SpriteBatch batch, int posx, int posy) {
		Color bc = batch.getColor();
		for(int y = -(chunk / 2); y < chunk / 2; y++) {
			for(int x = -(chunk / 2); x < chunk / 2; x++) {
				int mapPosY = (y + PosHandle.getBlockY(posy));
				int mapPosX = (x + PosHandle.getBlockX(posx));
				int mapPos = (mapPosY) * width + (mapPosX);
				
				float light = Lighting.checkLight(mapPosY, mapPosX, this);
				map.get(mapPos).light = light;
				
				// Main Light
				if(mapPos < width * height && mapPosX < width && mapPosY < height && mapPosX > 0 && mapPosY > 0 && mapPos > 0 && !map.get(mapPos).isAir()) {
					batch.setColor(light, light, light, 1.0f);
					batch.draw(map.get(mapPos).getBlockTexture(), (mapPosX * Block.WIDTH), (mapPosY * Block.HEIGHT), Block.WIDTH, Block.HEIGHT);
					if(map.get(mapPos).isBreaking(mapPos)) {
						BlockBreak.drawBreakBlock(map.get(mapPos).breakLevel, batch, PosHandle.toRealX(mapPosX), PosHandle.toRealY(mapPosY));
					}
				}
			}
		}
		batch.setColor(bc);
		for(int i = 0; i < drops.size(); i++) {
			if(drops.get(i).checkChunk(posx, posy, chunk) && drops.get(i).isReachable()) {
				drops.get(i).draw(batch);
				drops.get(i).dropCheck(map.get((PosHandle.getBlockY(drops.get(i).posy) + 1) * width + PosHandle.getBlockX(drops.get(i).posx)).ID);
			} else {
				drops.remove(i);	
			}
		}
		
		time.update();
	}
	
	public void updateWorld(int posx, int posy) {
		List<Integer> water = new ArrayList<Integer>();
		for(int y = -20; y < chunk / 2; y++) {
			for(int x = -20; x < chunk / 2; x++) {
				int mapPosY = (y + PosHandle.getBlockY(posy));
				int mapPosX = (x + PosHandle.getBlockX(posx));
				int mapPos = (mapPosY) * width + (mapPosX);
				
				if(mapPos < width * height && mapPosX < width && mapPosY < height && mapPosX > 0 && mapPosY > 0 && mapPos > 0 && !map.get(mapPos).isAir()) {
					if(map.get(mapPos).ID == 9 && Physics.isWaterCanBeStreamed(mapPos)) {
						water.add(mapPos);
					}
				}
			}
		}
		for(int i = 0; i < water.size(); i++) {
			Physics.waterPhyStream(water.get(i));
		}
	}
	
	public void updateTime() {
		time.update();
	}
	
	public int getDays() {
		return time.getDays();
	}
	public float getWorldTime() {
		return time.getTime();
	}
	public Time getTime() {
		return time;
	}
	public void setTime(Time t) {
		time = t;
	}
	public String getBiom(int x) {
		return bioms.get(PosHandle.getBlockX(x) / 100);
	}
	
	public void GenerateWorld(int seed) {
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				map.add(new Block(0));
			}
		}
		
		// Under Ground
		for(int i = undergroundLevel + 1; i < height; i++) {
			for(int j = 0; j < width; j++) {
				int mapPos = i * width + j;
				if(i < undergroundLevel + 5) {
					map.set(mapPos, new Block(6));
				} else if(i > undergroundLevel + 20) {
					map.set(mapPos, chooseRare(rCoal) ? new Block(3) : chooseRare(rIron) ? new Block(4) : chooseRare(rGold) ? new Block(5) : new Block(2));
				} else {
					map.set(mapPos, chooseRare(rCoal) ? new Block(3) : chooseRare(rIron) ? new Block(4) : new Block(2));
				}
			}
		}

		// Bioms
		map = BiomGeneration.generateBioms(map, bioms, width, undergroundLevel, 0, widthBiom);
		
	}
	
	public void setDropped(int id, int x, int y) {
		if(id > 0) {
			drops.add(new Drop(id, x, y));
		}
	}
	
	public List<Block> getWorld() {
		return map;
	}
	
	public Block getPos(int y, int x) {
		if(x > width || y > height) {
			return null;
		} else {
			return map.get(y * width + x);
		}
	}
	
	public boolean chooseRare(int r) {
		return num.nextInt(r) + 1 == r;
	}
}

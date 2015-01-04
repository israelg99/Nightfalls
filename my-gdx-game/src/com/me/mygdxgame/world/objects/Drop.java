package com.me.mygdxgame.world.objects;

import static com.me.mygdxgame.resources.Resources.map;
import static com.me.mygdxgame.resources.Resources.player;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.me.mygdxgame.graphics.Particles;
import com.me.mygdxgame.math.PosHandle;
import com.me.mygdxgame.utility.TextureHandle;

public class Drop {
	private static List<Sprite> drops = new ArrayList<Sprite>();

	public final static int WIDTH = 25;
	public final static int HEIGHT = 25;

	private final static int addTopMax = 1;

	private final static int addSideMin = 20;
	private final static int addSideMax = 80;

	public final static int droppingSpeed = 10;
	
	public int ID;

	public int posx;
	public int posy;

	public static void Load() {
		drops.add(null);
		drops.add(TextureHandle.getSpriteFlippedY("blocks/grass.png"));
		drops.add(TextureHandle.getSpriteFlippedY("blocks/stone.png"));
		drops.add(TextureHandle.getSpriteFlippedY("blocks/coal.png"));
		drops.add(TextureHandle.getSpriteFlippedY("blocks/iron.png"));
		drops.add(TextureHandle.getSpriteFlippedY("blocks/gold.png"));
		drops.add(TextureHandle.getSpriteFlippedY("blocks/dirt.png"));
		drops.add(TextureHandle.getSpriteFlippedY("blocks/tree.png"));
		drops.add(TextureHandle.getSpriteFlippedY("blocks/leaf.png"));
		drops.add(TextureHandle.getSpriteFlippedY("blocks/water1.png"));
		drops.add(TextureHandle.getSpriteFlippedY("blocks/snow.png"));
		drops.add(TextureHandle.getSpriteFlippedY("blocks/snowLeaf.png"));
		drops.add(TextureHandle.getSpriteFlippedY("blocks/desert.png"));
		drops.add(TextureHandle.getSpriteFlippedY("blocks/desertLeaf.png"));
		drops.add(TextureHandle.getSpriteFlippedY("blocks/gravel.png"));
	}

	public Drop(int id, int x, int y) {
		this.ID = Block.getDropID(id);
		this.posx = x + PosHandle.getRandom(addSideMin, addSideMax);
		this.posy = y + addTopMax;
	}

	public static Sprite getBlockTexture(int id) {
		return drops.get(id);
	}

	public void draw(SpriteBatch b) {
		// b.draw(TEXTURE, posx + getRandomAdd(addSideMin, addSideMax), posy + getRandomAdd(addTopMin, addTopMax), WIDTH, HEIGHT); Crazy idea!!!!!
		b.draw(getBlockTexture(), posx, posy, WIDTH, HEIGHT);
	}

	public Sprite getBlockTexture() {
		return getBlockTexture(ID);
	}

	public void drop() {
		posy += droppingSpeed;
	}

	public void dropCheck(int id) {
		if (id == 0) {
			drop();
		} else if (((posy + droppingSpeed) % 100) > droppingSpeed) {
			drop();
		}
	}

	public boolean isPickedUp(int x, int y) {
		if (PosHandle.getBlockedPos(x, y, map.width) == PosHandle.getBlockY(posy) * map.width + PosHandle.getBlockX(posx)) {
			player.getItem(new Item(ID, 1));
			Particles.add("dropPickUp", x, y + Block.HEIGHT - 20);
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isReachable() {
		if(map.map.get(PosHandle.getBlockY(posy) * map.width + PosHandle.getBlockX(posx)).ID == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean checkChunk(int x, int y, int chunk) {
		if(PosHandle.getBlockX(posx) > -(chunk / 2) + PosHandle.getBlockX(x) && PosHandle.getBlockX(posx) < chunk / 2 + PosHandle.getBlockX(x) && PosHandle.getBlockY(posy) > -(chunk / 2) + PosHandle.getBlockY(y) && PosHandle.getBlockY(posy) < chunk / 2 + PosHandle.getBlockY(y) && isPickedUp(x, y) == false) {
			return true;
		} else {
			return false;
		}
	}
}

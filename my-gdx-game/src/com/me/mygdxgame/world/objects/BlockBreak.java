package com.me.mygdxgame.world.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.me.mygdxgame.utility.TextureHandle;

public class BlockBreak {

	public static int levelSize = 6;
	private static Texture[] block_break = new Texture[levelSize + 1];

	public static void Load() {
		block_break[1] = TextureHandle.getTexture("block_break/break1.png", false);
		block_break[2] = TextureHandle.getTexture("block_break/break2.png", false);
		block_break[3] = TextureHandle.getTexture("block_break/break3.png", false);
		block_break[4] = TextureHandle.getTexture("block_break/break4.png", false);
		block_break[5] = TextureHandle.getTexture("block_break/break5.png", false);
		block_break[6] = TextureHandle.getTexture("block_break/break6.png", false);
	}

	public static void drawBreakBlock(int anim, SpriteBatch b, int x, int y) {
		if (anim > 0 && anim <= levelSize) {
			b.draw(block_break[anim], x, y, Block.WIDTH, Block.HEIGHT);
		} else {
			System.err.println("Wrong anim BreakBlock int passed :D");
		}
	}
}

package com.me.mygdxgame.utility;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.me.mygdxgame.player.Player;
import com.me.mygdxgame.world.objects.Block;

public class PlayerUtil {
	private static ShapeRenderer sr = new ShapeRenderer();
	
	public static void drawLookBorder(Player p) {
		sr.setProjectionMatrix(p.camera.combined);
		sr.begin(ShapeType.Line);
		sr.setColor(Color.WHITE);
		sr.rect(p.getLookX(), p.getLookY(), Block.WIDTH, Block.HEIGHT);
		sr.end();
	}
}

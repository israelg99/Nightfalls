package com.me.mygdxgame.graphics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool.PooledEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.me.mygdxgame.world.objects.Block;
import com.me.mygdxgame.world.objects.GeneralBlock;

public class Particles {
	
	private static Map<String, ParticleEffectPool> particles = new HashMap<String, ParticleEffectPool>();
	
	private static String path = "particles";
	private static Array<PooledEffect> effects;
	private static List<GeneralBlock> grass = new ArrayList<GeneralBlock>();
	private static List<GeneralBlock> stone = new ArrayList<GeneralBlock>();
	private static List<GeneralBlock> snow = new ArrayList<GeneralBlock>();
	
	public static void Load() {
		
		effects = new Array<PooledEffect>();
		
		particles.put("waterSplash", loadParticle("waterSplash.p"));
		particles.put("waterWalk", loadParticle("waterWalk.p"));
		particles.put("waterMove", loadParticle("waterMove.p"));
		particles.put("waterMoveLeft", loadParticle("waterMoveLeft.p"));
		particles.put("waterMoveRight", loadParticle("waterMoveRight.p"));
		particles.put("grassyWalk", loadParticle("grassyWalk.p"));
		particles.put("stonyWalk", loadParticle("stonyWalk.p"));
		particles.put("snowWalk", loadParticle("snowWalk.p"));
		particles.put("desertWalk", loadParticle("desertWalk.p"));
		particles.put("dropPickUp", loadParticle("dropPickUp.p"));
		
		grass.add(Block.getBlocks().get(1));
		grass.add(Block.getBlocks().get(6));
		grass.add(Block.getBlocks().get(8));
		grass.add(Block.getBlocks().get(13));
		
		stone.add(Block.getBlocks().get(2));
		stone.add(Block.getBlocks().get(3));
		stone.add(Block.getBlocks().get(4));
		stone.add(Block.getBlocks().get(5));
		stone.add(Block.getBlocks().get(14));
		
		snow.add(Block.getBlocks().get(10));
		snow.add(Block.getBlocks().get(11));
	}
	
	public static boolean isGrass(int id) {
		return Block.has(grass, id);
	}
	public static boolean isStone(int id) {
		return Block.has(stone, id);
	}
	public static boolean isSnow(int id) {
		return Block.has(snow, id);
	}

	
	
	public static void render(SpriteBatch batch, float delta) {
		for(PooledEffect effect : effects) {
			effect.draw(batch, delta);
			if(effect.isComplete()) {
				effects.removeValue(effect, true);
				effect.free();
			}
		}
	}
	
	public static void add(String name, int posx, int posy) {
		PooledEffect effect = particles.get(name).obtain();
		effect.setPosition(posx, posy);
		effects.add(effect);
	}
	
	private static ParticleEffectPool loadParticle(String name) {
		ParticleEffect temp = new ParticleEffect();
		temp.load(Gdx.files.internal(path + "/" + name), Gdx.files.internal(path));
		temp.flipY();
		temp.start();
		
		ParticleEffectPool tempPool = new ParticleEffectPool(temp, 0, 70); 
		
		return tempPool;
	}
	
	public static void dispose() {
		
	}
}

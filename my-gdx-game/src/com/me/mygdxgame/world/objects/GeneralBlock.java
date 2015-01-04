package com.me.mygdxgame.world.objects;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class GeneralBlock {
	public final Sprite tex;
	public final int dropID;
	public final int id;
	public final int amountDropMin;
	public final int amountDropMax;
	
	public GeneralBlock(Sprite t, int id, int did, int admin, int admax) {
		this.tex = t;
		this.id = id;
		this.dropID = did;
		this.amountDropMin = admin;
		this.amountDropMax = admax;
	}
	public GeneralBlock(Sprite t, int id, int did) {
		this(t, id, did, 1, 1);
	}
	public GeneralBlock(Sprite t, int id) {
		this(t, id, id, 1, 1);
	}
}

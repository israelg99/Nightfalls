package com.me.mygdxgame.world.objects;

import java.io.Serializable;

public class SerializableBlock implements Serializable {

	private static final long serialVersionUID = 1L;
	public int ID;
	public float light;
	
	public SerializableBlock(int i, float l) {
		ID = i;
		light = l;
	}
}

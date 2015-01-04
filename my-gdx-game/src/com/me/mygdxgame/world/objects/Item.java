package com.me.mygdxgame.world.objects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class Item implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private static List<String> items = new ArrayList<String>();

	public int ID;
	public String name;
	private boolean isPlaceable = true;

	public static void Load() {
		items.add("air");
		items.add("grass");
		items.add("stone");
		items.add("coal");
		items.add("iron");
		items.add("gold");
		items.add("dirt");
		items.add("tree");
		items.add("leaf");
		items.add("water");
		items.add("snow");
		items.add("snowLeaf");
		items.add("desert");
		items.add("desertLeaf");
		items.add("gravel");
	}
	
	public boolean isPlaceable() {
		return isPlaceable;
	}

	public Item(int id, int q) {
		this.ID = id;
		this.name = items.get(id);
	}

}

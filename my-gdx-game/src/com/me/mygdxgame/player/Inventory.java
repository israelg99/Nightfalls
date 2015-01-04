package com.me.mygdxgame.player;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.me.mygdxgame.world.objects.InventorySlot;
import com.me.mygdxgame.world.objects.Item;

public class Inventory implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public List<InventorySlot> inventory = new ArrayList<InventorySlot>();
	public int SIZE;
	public static final int DEFAULT_SIZE = 36;
	
	public Inventory(int size) {
		SIZE = size;
		for(int i = 0; i < SIZE; i++) {
			inventory.add(new InventorySlot());
		}
	}
	
	public void addItem(Item it) {
		
		int space = -1;
		
		for(int i = 0; i < inventory.size(); i++) {
			if(!inventory.get(i).isEmpty() && !inventory.get(i).isFull() && inventory.get(i).getID() == it.ID) {
				inventory.get(i).add();
				return;
			}
			if(inventory.get(i).isEmpty() && space == -1) {
				space = i;
			}
		}
		
		if(space != -1) {
			inventory.get(space).setEmpty(it);
		}
	}
	
	public InventorySlot getSlot(int i) {
		return inventory.get(i);
	}
	
	public InventorySlot getSlot(int row, int y, int x) {
		return inventory.get(y * row + x);
	}
	
	/*public Item getItem(int i) {
		return inventory.get(i).getContains();
	}
	
	public Item getItem(int row, int y, int x) {
		return inventory.get(y * row + x).getContains();
	}*/
	
}

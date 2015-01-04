package com.me.mygdxgame.world.objects;

import java.io.Serializable;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.me.mygdxgame.utility.TextureHandle;

public class InventorySlot implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public static final Sprite slotImage = TextureHandle.getSpriteFlippedY("ui/useEmptySlot1.png");
	public static final Sprite useSlotImage = TextureHandle.getSpriteFlippedY("ui/useEmptySlot1.png");
	public static final Sprite useSlotImage_select = TextureHandle.getSpriteFlippedY("ui/useEmptySlot_select.png");
	
	// 60 = 10 Slots per row (if InventoryHUD.WIDTH ~=~ 800)
	public static final int WIDTH = 70;
	public static final int HEIGHT = 70;
	
	public static final int ITEM_WIDTH = 50;
	public static final int ITEM_HEIGHT = 50;

	public static final int FULL_SLOT = 64;
	
	private boolean isEmpty;
	private boolean isFull;
	private Item contains;
	private int quantity;
	
	public InventorySlot() {
		isEmpty = true;
		isFull = false;
		contains = null;
	}
	public InventorySlot(Item i) {
		isEmpty = false;
		contains = i;
		add();
	}
	public InventorySlot(Item i, int q) {
		isEmpty = false;
		contains = i;
		setQuantity(q);
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void add() {
		setQuantity(quantity+1);
	}
	public void subtract() {
		quantity--;
		isFull = false;
		if(quantity <= 0) {
			setEmpty();
		}
	}
	public void setQuantity(int q) {
		if(q >= FULL_SLOT) {
			quantity = FULL_SLOT;
			isFull = true;
		} else {
			quantity = q;
			isFull = false;
		}
	}
	
	public int getID() {
		if(!isEmpty()) {
			return contains.ID;
		} else {
			return -1;
		}
	}
	
	public boolean isPlaceable() {
		return contains.isPlaceable();
	}
	
	public boolean isFull() {
		return isFull;
	}
	public boolean isEmpty() {
		return isEmpty;
	}
	public void setEmpty(Item i) {
		this.isEmpty = false;
		this.contains = i;
		add();
	}
	public void setEmpty() {
		this.isEmpty = true;
		this.contains = null;
	}
	
	public Item getContains() {
		return contains;
	}
	
	public Sprite getContainsImage() {
		if(!isEmpty()) {
			return Drop.getBlockTexture(contains.ID);
		} else {
			return slotImage;
		}
	}
}

package com.me.mygdxgame.HUD;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.me.mygdxgame.math.ScreenPosHandle;
import com.me.mygdxgame.player.Player;
import com.me.mygdxgame.resources.Resources;
import com.me.mygdxgame.world.objects.InventorySlot;

public class UseBarHUD {
	private static final int ADD = 20;
	
	private static final int SlotWidthADD = InventorySlot.WIDTH + ADD;
	private static final int SlotHeightADD = InventorySlot.HEIGHT + ADD;
	
	private static final int slotsPerRow =  InventoryHUD.slotsPerRow;
	private static final int slotsPerColumn = 1;	
	
	private static final int WIDTH =  ScreenPosHandle.getViableSize(slotsPerRow * SlotWidthADD, SlotWidthADD) + ADD;
	
	private static final int CENTER_X = ScreenPosHandle.getCenterScreenX(WIDTH);
	private static final int YPOS = (ScreenPosHandle.getSizeCenter(100, InventorySlot.HEIGHT)) + ADD / 2;
	
	private static final int CENTER_ITEM_X = ScreenPosHandle.getSizeCenter(InventorySlot.WIDTH, InventorySlot.ITEM_WIDTH);
	private static final int CENTER_ITEM_Y = ScreenPosHandle.getSizeCenter(InventorySlot.HEIGHT, InventorySlot.ITEM_HEIGHT);
	
	private static final int TEXT_QUAN_X = CENTER_ITEM_X + 30;
	private static final int TEXT_QUAN_Y = CENTER_ITEM_Y + 30;
	
	// We don't add additional rows for `rest`, because we want it to be one row use bar.
	public static void draw(SpriteBatch batch, Player p) {
		for(int j = 0; j < slotsPerRow; j++) {
			InventorySlot is = p.inv.getSlot(j);
			if(p.itemPointer == j) {
				batch.draw(InventorySlot.useSlotImage_select, (getSlotCalcX(j)), (YPOS), InventorySlot.WIDTH, InventorySlot.HEIGHT);
			} else {
				batch.draw(InventorySlot.useSlotImage, (getSlotCalcX(j)), (YPOS), InventorySlot.WIDTH, InventorySlot.HEIGHT);
			}
			if(!is.isEmpty()) {
				batch.draw(is.getContainsImage(), (getSlotCalcX(j)) + CENTER_ITEM_X, (YPOS) + CENTER_ITEM_Y, InventorySlot.ITEM_WIDTH, InventorySlot.ITEM_HEIGHT);
				Resources.font_quan.draw(batch, ("" + is.getQuantity()), (getSlotCalcX(j)) + TEXT_QUAN_X, (YPOS) + TEXT_QUAN_Y);
			}
		}
	}
	
	private static int getSlotCalcX(int place) {
		return (CENTER_X) + getSlotCalc(InventorySlot.WIDTH, place) + ADD;
	}

	private static int getSlotCalc(int size, int place) {
		return (size + ADD) * place;
	}
}
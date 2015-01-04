package com.me.mygdxgame.HUD;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.me.mygdxgame.math.ScreenPosHandle;
import com.me.mygdxgame.player.Player;
import com.me.mygdxgame.resources.Resources;
import com.me.mygdxgame.utility.TextureHandle;
import com.me.mygdxgame.world.objects.InventorySlot;

public class InventoryHUD {
	
	private static final Sprite BG = TextureHandle.getSpriteFlippedY("ui/invBG_style.png");
	
	private static final int ADDX = 5;
	private static final int ADDY = 5;
	
	private static final int SlotWidthADD = InventorySlot.WIDTH + ADDX;
	private static final int SlotHeightADD = InventorySlot.HEIGHT + ADDY;
	
	public static final int Window_WIDTH = ScreenPosHandle.getViableSize(800, SlotWidthADD)  + ADDX;
	public static final int Window_HEIGHT = ScreenPosHandle.getViableSize(800, SlotHeightADD)  + ADDY;
	
	public static final int WIDTH = ScreenPosHandle.getViableSize(700, SlotWidthADD)  + ADDX;
	public static final int HEIGHT = ScreenPosHandle.getViableSize(700, SlotHeightADD)  + ADDY;
	
	public static final int slotsPerRow =  ScreenPosHandle.SpaceFit(WIDTH, SlotWidthADD);
	public static final int slotsPerColumn = ScreenPosHandle.SpaceFit(HEIGHT, SlotHeightADD);
	
	private static final int CENTER_X = ScreenPosHandle.getCenterScreenX(WIDTH);
	private static final int CENTER_Y = ScreenPosHandle.getCenterScreenY(HEIGHT);
	
	private static final int Window_CENTER_X = ScreenPosHandle.getCenterScreenX(Window_WIDTH);
	private static final int Window_CENTER_Y = ScreenPosHandle.getCenterScreenY(Window_HEIGHT);
	
	private static final int CENTER_ITEM_X = ScreenPosHandle.getSizeCenter(InventorySlot.WIDTH, InventorySlot.ITEM_WIDTH);
	private static final int CENTER_ITEM_Y = ScreenPosHandle.getSizeCenter(InventorySlot.HEIGHT, InventorySlot.ITEM_HEIGHT);
	
	private static final int TEXT_QUAN_X = CENTER_ITEM_X + 30;
	private static final int TEXT_QUAN_Y = CENTER_ITEM_Y + 30;
	
	private static final int topPad = HEIGHT - 6 * (InventorySlot.ITEM_HEIGHT + ADDY);
	
	// We don't add additional rows for `rest`, because we want it to be symmetric.
	public static void draw(SpriteBatch batch, Player p) {
		batch.draw(BG, Window_CENTER_X, Window_CENTER_Y, Window_WIDTH, Window_HEIGHT);
		for(int i = 0; i < p.inv.SIZE / slotsPerRow; i++) {
			for(int j = 0; j < slotsPerRow; j++) {
				InventorySlot is = p.inv.getSlot(i * slotsPerRow + j);
				batch.draw(InventorySlot.slotImage, (getSlotCalcX(j)), (getSlotCalcY(i)), InventorySlot.WIDTH, InventorySlot.HEIGHT);
				if(!is.isEmpty()) {
					batch.draw(is.getContainsImage(), (getSlotCalcX(j)) + CENTER_ITEM_X, (getSlotCalcY(i)) + CENTER_ITEM_Y, InventorySlot.ITEM_WIDTH, InventorySlot.ITEM_HEIGHT);
					Resources.font_quan.draw(batch, ("" + is.getQuantity()), (getSlotCalcX(j)) + TEXT_QUAN_X, (getSlotCalcY(i)) + TEXT_QUAN_Y);
				}
			}
		}
	}
	
	private static int getSlotCalcX(int place) {
		return (CENTER_X) + slotCalcX(place) + (ADDX);
	}

	private static int getSlotCalcY(int place) {
		return (CENTER_Y) + slotCalcY(place) + (topPad);
	}
	private static int slotCalcX(int place) {
		return (InventorySlot.WIDTH + ADDX) * place;
	}
	private static int slotCalcY(int place) {
		return (InventorySlot.HEIGHT + ADDY) * place;
	}
	
	public static void dispose() {
		
	}
}
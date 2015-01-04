package com.me.mygdxgame.HUD;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.me.mygdxgame.player.Player;

public class HUD {
	private static boolean isInvShown = false;
	private static boolean isUseBarShown = true;
	private static boolean isHMBarsShown = true;
	private static boolean isEscapeMenuShown = false;

	public static void drawHUD(SpriteBatch batch, Player player) {
		if(isEscapeMenuShown) {
			EscapeMenuHUD.draw(batch);
			return;
		}
		if(isInvShown()) {
			InventoryHUD.draw(batch, player);
			return;
		}
		if(isUseBarShown()) {
			UseBarHUD.draw(batch, player);
		}
		if(isHMBarsShown()) {
			HMBarsHUD.draw(batch, player);
		}
	}
	
	public static boolean isEscapeMenuShown() {
		return isEscapeMenuShown;
	}

	public static void showEscapeMenu(boolean show) {
		isEscapeMenuShown = show;
	}
	
	public static boolean isInvShown() {
		return isInvShown;
	}
	
	public static void showInv(boolean show) {
		isInvShown = show;
	}
	
	public static boolean isUseBarShown() {
		return isUseBarShown;
	}
	public static void showUseBar(boolean show) {
		isUseBarShown = show;
	}
	
	public static boolean isHMBarsShown() {
		return isHMBarsShown;
	}
	
	public static void dispose() {
		InventoryHUD.dispose();
		HMBarsHUD.dispose();
		EscapeMenuHUD.dispose();
	}
}

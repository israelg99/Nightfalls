package com.me.mygdxgame.player;

import static com.me.mygdxgame.resources.Resources.char_Height;
import static com.me.mygdxgame.resources.Resources.char_Width;
import static com.me.mygdxgame.resources.Resources.char_sprite;
import static com.me.mygdxgame.resources.Resources.font_name;
import static com.me.mygdxgame.resources.Resources.map;
import static com.me.mygdxgame.resources.Resources.player;

import java.io.Serializable;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.me.mygdxgame.HUD.EscapeMenuHUD;
import com.me.mygdxgame.HUD.HUD;
import com.me.mygdxgame.HUD.InventoryHUD;
import com.me.mygdxgame.game.Render;
import com.me.mygdxgame.graphics.Particles;
import com.me.mygdxgame.math.MathPercentage;
import com.me.mygdxgame.math.PosHandle;
import com.me.mygdxgame.math.Vec2;
import com.me.mygdxgame.resources.Prefs;
import com.me.mygdxgame.resources.Resources;
import com.me.mygdxgame.screen.GameScreen;
import com.me.mygdxgame.sound.SFX;
import com.me.mygdxgame.utility.InputControls;
import com.me.mygdxgame.utility.PlayerUtil;
import com.me.mygdxgame.world.Background;
import com.me.mygdxgame.world.Physics;
import com.me.mygdxgame.world.objects.Block;
import com.me.mygdxgame.world.objects.Item;

public class Player implements InputProcessor, Serializable {

	private static final long serialVersionUID = 1L;
	private Vec2 pos = new Vec2();
	private Vec2 look = new Vec2();
	public int lookSense = 10;
	public boolean isMoving = false;
	public boolean isMidAir = false;
	public boolean isFlying = false;
	public boolean isInWater = false;
	public String flip = "right";
	public int grav;
	public int groundAdd = 35; // to add to ground so it will look like you stepping on it.. try to change value to understand
	public int waterAdd = 20; // to add to water so it will look like you stepping on it.. try to change value to understand
	public int walkSpeed = 5;
	public int runSpeed = 10;
	public boolean isRunning = false;
	public boolean isJump = false;
	public boolean isStanding = false;
	public boolean isWalking = false;
	public boolean isInvLooking = false;
	public boolean controlPlayer = true;
	public int jumpHeight = 220;
	public int jumpSpeed = 20;
	public int fallingSpeed = 10;
	public int speed;
	public int jumpCalc;
	public double toNextStep = 0;
	public double timeWalkSteps = 0.5;
	public double timeRunSteps = 0.3;
	public int steps = 1;
	public transient OrthographicCamera camera;
	public Inventory inv;
	public int itemPointer = 0;
	public boolean isBreaking = false;
	public int breakSpeed = 30;
	private int fullHP = 450;
	private int fullMana = 280;
	private int hp;
	private int mana;
	private int legsPos = char_sprite.BLOCK_SIZE_HEIGHT + 20;

	public Player(int x, int y) {
		pos.x = x;
		pos.y = y;
		look.x = 0;
		look.y = 0;
		camera = new OrthographicCamera();
		camera.setToOrtho(true, Render.ResX, Render.ResY);

		System.out.println("The camera position is set to the player position!");
		camera.position.x = pos.x;
		camera.position.y = pos.y;
		System.err.println("Setting camera to the position of the ");

		System.out.println("Setting up Inventory!");
		inv = new Inventory(Inventory.DEFAULT_SIZE);
		
		hp = fullHP - 100;
		mana = fullMana - 50;
	}

	public int getBlockX() {
		return PosHandle.getBlockX(pos.x);
	}

	public int getBlockY() {
		return PosHandle.getBlockY(pos.y);
	}

	public int collideNow() {
		return map.getPos(getBlockY(), getBlockX()).ID;
	}

	public int collideBot() {
		return map.getPos(getBlockY() + 1, getBlockX()).ID;
	}

	public int collideTop() {
		return map.getPos(PosHandle.getBlockY(pos.y - Physics.YColValue), getBlockX()).ID;
	}

	public int collideLeft() {
		return map.getPos(getBlockY(), PosHandle.getBlockX(pos.x - Physics.XColValue)).ID;
	}

	public int collideRight() {
		return map.getPos(getBlockY(), PosHandle.getBlockX(pos.x + Physics.XColValue)).ID;
	}

	public int collideFixedLeft() {
		return map.getPos(PosHandle.getFixedBlockY(pos.y), PosHandle.getBlockX(pos.x - Physics.XColValue)).ID;
	}

	public int collideFixedRight() {
		return map.getPos(PosHandle.getFixedBlockY(pos.y), PosHandle.getBlockX(pos.x + Physics.XColValue)).ID;
	}

	public boolean isCollideBot() {
		return restYPos() == 0 && ((map.getPos(getBlockY() + 1, PosHandle.getBlockX(pos.x - Physics.XColValue)).isCollidable() && !(map.getPos(getBlockY(), PosHandle.getBlockX(pos.x - Physics.XColValue)).isCollidable())) || (map.getPos(getBlockY() + 1, PosHandle.getBlockX(pos.x + Physics.XColValue)).isCollidable() && !(map.getPos(getBlockY(), PosHandle.getBlockX(pos.x + Physics.XColValue)).isCollidable())));
	}

	public int CollideBotBlock() {
	/*	if ((map.getPos(getBlockY() + 1, PosHandle.getBlockX(pos.x - Physics.XColValue)).isCollidable() && !(map.getPos(getBlockY(), PosHandle.getBlockX(pos.x - Physics.XColValue)).isCollidable()))) {
			return map.getPos(getBlockY() + 1, PosHandle.getBlockX(pos.x - Physics.XColValue)).ID;
		} else if ((map.getPos(getBlockY() + 1, PosHandle.getBlockX(pos.x + Physics.XColValue)).isCollidable() && !(map.getPos(getBlockY(), PosHandle.getBlockX(pos.x + Physics.XColValue)).isCollidable()))) {
			return map.getPos(getBlockY() + 1, PosHandle.getBlockX(pos.x + Physics.XColValue)).ID;
		}
		return 0;*/
		
		// HOLY COW LOOK AT THIS IMPROVE
		return map.getPos(getBlockY() + 1, getBlockX()).ID;
	}

	public int restXPos() {
		return pos.x % Block.WIDTH;
	}

	public int restYPos() {
		return pos.y % Block.HEIGHT;
	}
	
	public int getX() {
		return pos.x;
	}
	public int getY() {
		return pos.y;
	}

	public void draw(SpriteBatch batch, TextureRegion walk, TextureRegion run) {

		int modifFootAdd = !isInWater ? groundAdd : waterAdd;

		if (isMoving) {
			if (!isRunning) {
				batch.draw(walk, camera.position.x - char_Width / 2, camera.position.y - char_Height / 2 + modifFootAdd, char_Width, char_Height);
				return;
			} else {
				batch.draw(run, camera.position.x - char_Width / 2, camera.position.y - char_Height / 2 + modifFootAdd, char_Width, char_Height);
				return;
			}
		} else if (isStanding) {
			batch.draw(char_sprite.getStand(flip), camera.position.x - char_Width / 2, camera.position.y - char_Height / 2 + modifFootAdd, char_Width, char_Height);
			return;
		}

		if (isJump || isMidAir) {
			batch.draw(char_sprite.getJump(flip), camera.position.x - char_Width / 2, camera.position.y - char_Height / 2 + modifFootAdd, char_Width, char_Height);
		}
	}

	public void drawName(SpriteBatch batch) {
		// Player's Name
		font_name.draw(batch, Prefs.getName(), camera.position.x - char_Width / 2 + 65, camera.position.y - char_Height / 2 - 20 + groundAdd);
	}
	
	public int getHP() {
		return hp;
	}
	public int getMana() {
		return mana;
	}
	
	public int getHP_Percentage() {
		return MathPercentage.getPercentage(fullHP, hp);
	}
	public int getMana_Percentage() {
		return MathPercentage.getPercentage(fullMana, mana);
	}
	
	public void drawLookBorder() {
		PlayerUtil.drawLookBorder(this);
	}

	public void ResetStates() {
		isRunning = false;
		isJump = false;
		isStanding = false;
		isWalking = false;
		isMoving = false;
	}
	
	public void update() {
		if(controlPlayer) {
			
			movementInput();
	
			lookUpdate();
	
			soundUpdate();
			
			breakUpdate();
			
			drawLookBorder();
			
			applyMove();
		}
		
		airUpdate();
		
	}

	private void movementInput() {

		isInWater = collideNow() == 9 ? true : false;

		if (Gdx.input.isKeyPressed(InputControls.LEFT) && !Block.isCollidable(collideFixedLeft()) && !Block.isCollidable(map.getPos(PosHandle.getFixedBlockY(pos.y - 100), PosHandle.getBlockX(pos.x - Physics.XColValue)).ID)) {
			speed = isRunning ? -runSpeed : -walkSpeed;
			ResetStates();
			isWalking = true;
			isMoving = true;
			flip = "left";
		} else if (Gdx.input.isKeyPressed(InputControls.RIGHT) && !Block.isCollidable(collideFixedRight()) && !Block.isCollidable(map.getPos(PosHandle.getFixedBlockY(pos.y - 100), PosHandle.getBlockX(pos.x + Physics.XColValue)).ID)) {
			speed = isRunning ? runSpeed : walkSpeed;
			ResetStates();
			isWalking = true;
			isMoving = true;
			flip = "right";

		} else {
			speed = 0;
			ResetStates();
			isStanding = true;
		}

		if (Gdx.input.isKeyPressed(InputControls.RUN) && isMoving && !isMidAir && !isInWater) {
			ResetStates();
			isWalking = false;
			isMoving = true;
			isRunning = true;
		} else {
			isRunning = false;
		}

		if (Gdx.input.isKeyPressed(InputControls.JUMP) && !isMidAir && !Block.isCollidable(collideTop())) {
			ResetStates();
			if (jumpCalc == 0) {
				SFX.play("jump");
			}
			isJump = true;
		} else {
			isJump = false;
			jumpCalc = 0;
		}
	}
	
	private void breakUpdate() {
		int mapPos = getLookPos();
		if(isBreaking && map.map.get(mapPos).ID != 0 && mapPos < map.width * map.height && mapPos > 0 && mapPos != getBlockY() * map.width + getBlockX() && mapPos != (getBlockY() - 1) * map.width + getBlockX() && mapPos != (PosHandle.getFixedBlockY(pos.y)) * map.width + getBlockX() && PosHandle.getBlockY(getLookY()) > 0 && PosHandle.getBlockY(getLookY()) < map.height && PosHandle.getBlockX(getLookX()) > 0 && PosHandle.getBlockX(getLookX()) < map.width) {
			map.map.get(mapPos).breakBlock(getLookX(), getLookY());
		}
	}

	private void lookUpdate() {
		if (Gdx.input.getX() > Render.ResX) {
			Gdx.input.setCursorPosition(Render.ResX, GameScreen.SizeY - Gdx.input.getY());
		} else if (Gdx.input.getX() < 0) {
			Gdx.input.setCursorPosition(0, GameScreen.SizeY - Gdx.input.getY());
		}

		if (Gdx.input.getY() > Render.ResY) {
			Gdx.input.setCursorPosition(Gdx.input.getX(), GameScreen.SizeY - Render.ResY);
		} else if (Gdx.input.getY() < 0) {
			Gdx.input.setCursorPosition(Gdx.input.getX(), GameScreen.SizeY);
		}

		look.x = Gdx.input.getX();
		look.y = Gdx.input.getY();
	}

	private void airUpdate() {
		if (getBlockY() < map.height - 1 && !isCollideBot() && !isJump && !isFlying) {
			jumpCalc = 0;
			ResetStates();
			isMidAir = true;
			int fspeed = !isInWater ? fallingSpeed : fallingSpeed / Physics.waterPull; // Bugs like hell.. u can suddenly fall down from the map. NOT ANYMORE!! FIX IS BELOW!
			pos.y += fspeed;
			if(map.getPos(PosHandle.getBlockY(pos.y + Block.HEIGHT - 10), getBlockX()).isCollidable()) { // Not to fall of the map
				pos.y = PosHandle.getFloorBlockedY(pos.y);
			}
			camera.position.y = pos.y;
			if (collideNow() == 9 && !isInWater) {
				SFX.waterSplash();
				Particles.add("waterSplash", pos.x, pos.y);
			}
		} else {
			isMidAir = false;
		}

		if (getBlockY() > 1 && isJump && !isMidAir) {
			pos.y -= jumpSpeed;
			camera.position.y -= jumpSpeed;
			jumpCalc += jumpSpeed;
			if (jumpCalc >= jumpHeight || Block.isCollidable(collideTop())) {
				isJump = false;
				isMidAir = true;
			}
		}
	}

	private void soundUpdate() {
		if(Block.isCollidable(CollideBotBlock())) {
			if (player.isWalking) {
				stepDelayHandle(timeWalkSteps);
			} else if (player.isRunning) {
				stepDelayHandle(timeRunSteps);
			}
			return;
		}
		toNextStep = 0;
		steps = 1;
	}

	private void stepDelayHandle(double time) {
		toNextStep -= Gdx.graphics.getDeltaTime();
		if (toNextStep < 0) {
			playSoundType();
			toNextStep = time;
		}
	}

	private void playSoundType() {
		if (collideNow() == 9) { // water
			steps = steps < SFX.ftWaterLength ? steps + 1 : 1;
			SFX.play("ftWater" + steps);
			Particles.add("waterWalk", pos.x, pos.y + legsPos - 10);
			
		} else if (Particles.isGrass(CollideBotBlock())) { // grass
			steps = steps < SFX.ftGrassLength ? steps + 1 : 1;
			SFX.play("ftGrass" + steps);
			Particles.add("grassyWalk", pos.x, pos.y + legsPos);
		} else if(Particles.isSnow(CollideBotBlock())) { // snow
			steps = steps < SFX.ftSnowLength ? steps + 1 : 1;
			SFX.play("ftSnow" + steps);
			Particles.add("snowWalk", pos.x, pos.y + legsPos);
		} else if(CollideBotBlock() == 12) { // desert
			steps = steps < SFX.ftDesertLength ? steps + 1 : 1;
			SFX.play("ftDesert" + steps);
			Particles.add("desertWalk", pos.x, pos.y + legsPos);
		} else if(Particles.isStone(CollideBotBlock())) { // stone
			steps = steps < SFX.ftLength ? steps + 1 : 1;
			SFX.play("ft" + steps);
			Particles.add("stonyWalk", pos.x, pos.y + legsPos);
		}
	}

	public void applyMove() {
		if (speed != 0 && pos.x + speed > Block.WIDTH && pos.x + speed < map.width * Block.WIDTH) {
			int modif = map.map.get(getBlockY() * map.width + getBlockX()).ID != 9 ? speed : speed / 2;
			pos.x += modif;
			camera.position.x = pos.x;
			Background.applyMove(modif);
		}
	}

	public int getLookX() {
		return PosHandle.getInBlockedX(pos.x) + PosHandle.getInBlockedX(look.x - Render.ResX / 2);
	}

	public int getLookY() {
		return PosHandle.getInBlockedY(pos.y) + PosHandle.getInBlockedY(look.y - Render.ResY / 2);
	}
	
	public int getLookPos() {
		return PosHandle.getBlockY(getLookY()) * map.width + PosHandle.getBlockX(getLookX());
	}

	public void getItem(Item i) {
		inv.addItem(i);
	}

	public boolean keyDown(int keycode) {
		if(keycode == InputControls.INV) {
			ResetStates();
			HUD.showInv(!HUD.isInvShown());
			controlPlayer = !HUD.isInvShown();
		} else if(keycode == InputControls.EscapeMenu) {
			ResetStates();
			HUD.showEscapeMenu(!HUD.isEscapeMenuShown());
			controlPlayer = !HUD.isEscapeMenuShown();
		} else if(HUD.isEscapeMenuShown()) {
			if(keycode == InputControls.EMOptionUP) {
				EscapeMenuHUD.subID();
			} else if(keycode == InputControls.EMOptionDown) {
				EscapeMenuHUD.addID();
			} else if(keycode == InputControls.EMOptionClick) {
				EscapeMenuHUD.click();
			}
		}
		return false;
	}

	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		int mapPos = getLookPos();
		if (mapPos < map.width * map.height && mapPos > 0 && mapPos != getBlockY() * map.width + getBlockX() && mapPos != (getBlockY() - 1) * map.width + getBlockX() && mapPos != (PosHandle.getFixedBlockY(pos.y)) * map.width + getBlockX() && PosHandle.getBlockY(getLookY()) > 0 && PosHandle.getBlockY(getLookY()) < map.height && PosHandle.getBlockX(getLookX()) > 0 && PosHandle.getBlockX(getLookX()) < map.width) {
			switch (button) {
			case InputControls.BREAK:
				isBreaking = true;
				break;

			case InputControls.PLACE:
				if(!inv.getSlot(itemPointer).isEmpty() && map.map.get(mapPos).canBePlacedOn() && inv.getSlot(itemPointer).isPlaceable()) {
					map.map.set(mapPos, new Block(inv.getSlot(itemPointer).getID()));
					inv.getSlot(itemPointer).subtract();
					SFX.play("block_place");
				}
				break;
			}
		}
		return false;
	}

	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if(button == InputControls.BREAK) {
			isBreaking = false;
		}
		return false;
	}

	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean scrolled(int amount) {
		if(amount == 1) {
			if(itemPointer < InventoryHUD.slotsPerRow - 1) {
				itemPointer++;
			}
		} else {
			if(itemPointer > 0) {
				itemPointer--;
			}
		}
		return false;
	}
}

package com.me.mygdxgame.resources;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.me.mygdxgame.game.Render;
import com.me.mygdxgame.graphics.Particles;
import com.me.mygdxgame.graphics.Shaders;
import com.me.mygdxgame.mainMenu.MainMenuMusic;
import com.me.mygdxgame.mainMenu.MainMenuSFX;
import com.me.mygdxgame.player.CharSprite;
import com.me.mygdxgame.player.Player;
import com.me.mygdxgame.sound.Ambience;
import com.me.mygdxgame.sound.BGM;
import com.me.mygdxgame.sound.SFX;
import com.me.mygdxgame.utility.GameSaver;
import com.me.mygdxgame.utility.TextureHandle;
import com.me.mygdxgame.world.Background;
import com.me.mygdxgame.world.Physics;
import com.me.mygdxgame.world.World;
import com.me.mygdxgame.world.objects.Block;
import com.me.mygdxgame.world.objects.BlockBreak;
import com.me.mygdxgame.world.objects.Drop;
import com.me.mygdxgame.world.objects.Item;

public class Resources {

	public static CharSprite char_sprite;

	public static BitmapFont font_details;
	public static BitmapFont font_name;
	public static BitmapFont font_quan;
	public static BitmapFont font_hminfo;
	public static BitmapFont font_button;
	public static BitmapFont font_buttonS;

	public static final int char_Width = 200;
	public static final int char_Height = 160;

	public static World map;

	public static Player player;

	public final static String path = "C:/IndieGame/slot";

	public static void LoadResources() {

		System.err.println("Loading the Fonts!");
		font_details = TextureHandle.getFont("fonts/andyb.ttf", 45, Color.RED, true, 1, -1);
		font_name = TextureHandle.getFont("fonts/arial.ttf", 24, Color.BLACK, true, 1, -1);
		font_quan = TextureHandle.getFont("fonts/CHINESE ROCKS RG.TTF", 24, Color.BLACK, true, 1, -1);
		font_hminfo = TextureHandle.getFont("fonts/HARABARA.TTF", 24, Color.WHITE, true, 1, -1);
		font_button = TextureHandle.getFont("fonts/GROBOLD.ttf", 32, Color.WHITE, true, 1, -1);
		font_buttonS = TextureHandle.getFont("fonts/GROBOLD.ttf", 32, Color.RED, true, 1, -1);
		System.out.println("Finished to load the Fonts!");

		System.err.println("Loading the Preferences!");
		Prefs.loadName();
		System.out.println("Finished to load the Preferences!");

		System.err.println("Loading the Char sprite class!");
		char_sprite = new CharSprite(Render.ResX / 2 - char_Width / 2, Render.ResY / 2 - char_Height / 2);
		System.out.println("Finished to load the Char sprite class!");

		System.err.println("Loading the Blocks!!");
		Block.Load();
		System.out.println("Finished to load the Blocks!");
		
		System.err.println("Loading the Break Blocks!!");
		BlockBreak.Load();
		System.out.println("Finished to load the Break Blocks!");
		
		System.err.println("Loading the Items!!");
		Item.Load();
		System.out.println("Finished to load the Items!");

		System.err.println("Loading the Drops!!");
		Drop.Load();
		System.out.println("Finished to load the Drops!");
		
		System.err.println("Loading the Particles!!");
		Particles.Load();
		System.out.println("Finished to load the Particles!");

		System.err.println("Loading the Physics!!");
		Physics.LoadPhy(1, 1.03f);
		System.out.println("Finished to load the Physics!");

		System.out.println("Loading BGMs!");
		BGM.Load();
		BGM.init();

		System.out.println("Loading SFXs!");
		SFX.Load();
		
		System.out.println("Loading Ambience!");
		Ambience.Load();

		System.out.println("Loading Background!");
		Background.Load();

		System.out.println("Loading Shaders...!!!");
		Shaders.Load();
	}
	
	public static void LoadGame(boolean loadGame, int slot) {
		
		System.err.println("Setting up the path!!");
		GameSaver.setPath(path+slot+"/");
		
		System.err.println("Loading the Map!!");

		map = GameSaver.getWorld(loadGame);

		System.out.println("Finished to load the Map!");

		System.err.println("Loading the Player!!");

		player = GameSaver.getPlayer(loadGame);

		System.out.println("Finished to load the Player!");
		
		System.out.println("Initing Background!");
		Background.Init();
	}
	
	public static void LoadMainMenuResources() {
		MainMenuSFX.Load();
		MainMenuMusic.Load();
	}
}

package com.me.mygdxgame.screen;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.me.mygdxgame.mainMenu.MainMenuMusic;
import com.me.mygdxgame.mainMenu.MainMenuSFX;
import com.me.mygdxgame.mainMenu.MenuUtils;
import com.me.mygdxgame.tween.ActorAccessor;

public class WorldScreen implements Screen {

	private Stage stage;
	private Skin skin;
	private Table table;
	private TweenManager tweenManager;
	private FreeTypeFontGenerator genButtonText = new FreeTypeFontGenerator(Gdx.files.internal("fonts/GROBOLD.ttf"));
	private BitmapFont buttonText = genButtonText.generateFont(32);
	private TextButtonStyle blueButton;
	private TextureAtlas buttonAtlas;
	private Image gameLogo;
	private Image bg;

	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.act(delta);
		stage.draw();

		tweenManager.update(delta);
		
	}

	@Override
	public void resize(int width, int height) {
		stage.setViewport(width, height, true);
		table.invalidateHierarchy();
		
	}

	@Override
	public void show() {
		stage = new Stage();

		Gdx.input.setInputProcessor(stage);

		skin = new Skin();

		table = new Table(skin);
		table.setFillParent(true);
		
		// Logo
		gameLogo = new Image(new Texture(Gdx.files.internal("logos/gameLogo.png")));
		
		// Menu Background
		bg = new Image(new Texture(Gdx.files.internal("backgrounds/menuBG.jpg")));
		bg.setWidth(Gdx.graphics.getWidth());
		bg.setHeight(Gdx.graphics.getHeight());
				
		// Creating Buttons
		buttonAtlas = new TextureAtlas(Gdx.files.internal("buttons/mainMenuButton.pack"));
		skin.addRegions(buttonAtlas);
		blueButton = new TextButtonStyle(skin.getDrawable("blueButton"), skin.getDrawable("blueButton_down"), skin.getDrawable("blueButton_hover"), buttonText);
		blueButton.over = skin.getDrawable("blueButton_hover");

		TextButton buttonNewWorld = new TextButton("New World", blueButton);
		buttonNewWorld.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				MainMenuSFX.playClick();
				
				Timeline.createParallel().beginParallel()
				.push(Tween.to(table, ActorAccessor.ALPHA, .75f).target(0))
				.push(Tween.to(table, ActorAccessor.Y, .75f).target(table.getY() - 50)
						.setCallback(new TweenCallback() {

							@Override
							public void onEvent(int type, BaseTween<?> source) {
								((Game) Gdx.app.getApplicationListener()).setScreen(new WorldSlotsScreen(false));
							}
						}))
				.end().start(tweenManager);
			}
		});
		
		TextButton buttonLoadWorld = new TextButton("Load World", blueButton);
		buttonLoadWorld.addListener(new ClickListener() {
		
			@Override
			public void clicked(InputEvent event, float x, float y) {
				MainMenuSFX.playClick();
				
				Timeline.createParallel().beginParallel()
				.push(Tween.to(table, ActorAccessor.ALPHA, .75f).target(0))
				.push(Tween.to(table, ActorAccessor.Y, .75f).target(table.getY() - 50)
						.setCallback(new TweenCallback() {

							@Override
							public void onEvent(int type, BaseTween<?> source) {
								((Game) Gdx.app.getApplicationListener()).setScreen(new WorldSlotsScreen(true));
							}
						}))
				.end().start(tweenManager);
			}
		});
		
		TextButton buttonBack = new TextButton("Back", blueButton);
		buttonBack.addListener(new ClickListener() {
			
			@Override
			public void clicked(InputEvent event, float x, float y) {
				MainMenuSFX.playClick();
				
				Timeline.createParallel().beginParallel()
				.push(Tween.to(table, ActorAccessor.ALPHA, .75f).target(0))
				.push(Tween.to(table, ActorAccessor.Y, .75f).target(table.getY() - 50)
						.setCallback(new TweenCallback() {

							@Override
							public void onEvent(int type, BaseTween<?> source) {
								((Game) Gdx.app.getApplicationListener()).setScreen(new MainMenuScreen());
							}
						}))
				.end().start(tweenManager);
			}
		});

		// putting stuff together
		table.add(gameLogo).spaceBottom(20).padBottom(MenuUtils.logoPadBot).row();
		table.add(buttonNewWorld).spaceBottom(15).row();
		table.add(buttonLoadWorld).spaceBottom(15).row();
		table.add(buttonBack).padBottom(120);

		stage.addActor(bg);
		stage.addActor(table);

		// creating animations
		tweenManager = new TweenManager();
		Tween.registerAccessor(Actor.class, new ActorAccessor());

		// heading and buttons fade-in
		Timeline.createSequence().beginSequence()
				.push(Tween.set(buttonNewWorld, ActorAccessor.ALPHA).target(0))
				.push(Tween.set(buttonLoadWorld, ActorAccessor.ALPHA).target(0))
				.push(Tween.set(buttonBack, ActorAccessor.ALPHA).target(0))
				.push(Tween.from(gameLogo, ActorAccessor.ALPHA, .25f).target(0))
				.push(Tween.to(buttonNewWorld, ActorAccessor.ALPHA, .25f).target(1))
				.push(Tween.to(buttonLoadWorld, ActorAccessor.ALPHA, .25f).target(1))
				.push(Tween.to(buttonBack, ActorAccessor.ALPHA, .25f).target(1))
				.end().start(tweenManager);

		// table fade-in
		Tween.from(table, ActorAccessor.ALPHA, .75f).target(0).start(tweenManager);
		Tween.from(table, ActorAccessor.Y, .75f).target(Gdx.graphics.getHeight() / 8).start(tweenManager);

		tweenManager.update(Gdx.graphics.getDeltaTime());
		
	}

	@Override
	public void hide() {
		dispose();
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		stage.dispose();
		skin.dispose();
		
	}
	
	public void disposeMusic() {
		MainMenuMusic.stopAll();
		MainMenuMusic.disposeAll();
	}

}

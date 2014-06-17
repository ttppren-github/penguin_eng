package com.fy.penguineng;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.fy.penguineng.screen.GameFailScreen;
import com.fy.penguineng.screen.GamePassScreen;
import com.fy.penguineng.screen.LogoScreen;
import com.fy.penguineng.screen.MainMenuScreen;
import com.fy.penguineng.screen.SwichScreen;
import com.fy.penguineng.world.GameScreen;

public class PenguinEng extends Game {
	public GameScreen gameScreen;
	public GamePassScreen gamePassScreen;
	public GameFailScreen gameFailScreen;
	public SwichScreen swichScreen;
	public MainMenuScreen mainScreen;

	private LogoScreen logoScreen;
	private FPSLogger fps;
	private String text;
	public Assets assets;

	public IGameControl ttsListener;

	public PenguinEng() {
		assets = Assets.getInstance();
		text = new String();
	}

	@Override
	public void create() {
		logoScreen = new LogoScreen(this);
		this.setScreen(logoScreen);

		assets.loadResources();
		fps = new FPSLogger();
	}

	@Override
	public void render() {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // 清屏
		Gdx.gl.glClearColor(0.28f, 0.63f, 0.97f, 1f);
		super.render();
		// fps.log();
	}

	/**
	 * {@link Game#dispose()} only calls {@link Screen#hide()} so you need to
	 * override {@link Game#dispose()} in order to call {@link Screen#dispose()}
	 * on each of your screens which still need to dispose of their resources.
	 * SuperJumper doesn't actually have such resources so this is only to
	 * complete the example.
	 */
	@Override
	public void dispose() {
		super.dispose();

		getScreen().dispose();
		assets.unLoad();
	}

	public void setRecognize(String res) {
		if (!text.matches(res)) {
			gameScreen.setText(res);
		}
	}

	public void setVolume(int vol) {
		gameScreen.setVolume(vol);
	}

	public void setTtsListener(IGameControl ttsListener) {
		this.ttsListener = ttsListener;
	}

	public void resourceReady() {
		// first load resources
		assets.loadStyle();

		swichScreen = new SwichScreen(this);
		gameScreen = new GameScreen(this);
		mainScreen = new MainMenuScreen(this);
		gamePassScreen = new GamePassScreen(this);
		gameFailScreen = new GameFailScreen(this);

		if (null == ttsListener || !ttsListener.checkFirstRun()) {
			setScreen(mainScreen);
		}
	}
}

package com.fy.penguineng;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.FPSLogger;

public class PenguinEng extends Game {

	FPSLogger fps;
	String text;
	EasyDemoScreen gameScreen;
	
	@Override
	public void create() {
		gameScreen = new EasyDemoScreen(this);
		this.setScreen(gameScreen);

		Assets.loadAsset();
		fps = new FPSLogger();
		text = new String();
	}

	@Override
	public void render() {
		super.render();
		fps.log();
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
	}
	
	public void setRecognize(String res){
		if (!text.matches(res)){
			gameScreen.setText(res);
		}
	}
}

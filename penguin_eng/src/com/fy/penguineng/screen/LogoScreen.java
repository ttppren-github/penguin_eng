/**
 * 
 */
package com.fy.penguineng.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.actions.AlphaAction;
import com.badlogic.gdx.scenes.scene2d.actions.SizeToAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.fy.penguineng.Assets;
import com.fy.penguineng.PenguinEng;
import com.fy.penguineng.TtsCtrl;

/**
 * @author liufy
 * 
 */
public class LogoScreen extends BaseScreen {
	private final String TAG = LogoScreen.class.getSimpleName();

	private PenguinEng game;
	private Image logo, name;
	private float start;
	private final float DELAY = 3; // 3s
	private TtsCtrl player;

	/**
	 * 
	 */
	public LogoScreen(PenguinEng game) {
		this.game = game;
		start = 0;

		Assets.getInstance().preLoading();
		logo = new Image(Assets.getInstance().logo);
		name = new Image(Assets.getInstance().name);

		baseStage.addActor(logo);
		baseStage.addActor(name);
	}

	@Override
	public void render(float delta) {
		baseStage.act();
		logo.setPosition((Assets.VIRTUAL_WIDTH - logo.getWidth()) / 2,
				(Assets.VIRTUAL_HEIGHT - logo.getHeight()) * 2 / 3);
		baseStage.draw();

		start += delta;
		if (game.assets.update()) {
			float ret = game.assets.getProgress();
			if (ret == 1 && game.recognizerCtrl != null && start > DELAY) {
				Gdx.app.log(TAG, "loading resource over.");
				game.resourceReady();
			}
		}
	}

	@Override
	public void show() {
		name.setPosition((Assets.VIRTUAL_WIDTH - name.getWidth()) / 2, 100);

		Color c = logo.getColor();
		c.a = 0.4f;
		logo.setColor(c);
		AlphaAction alphaAction = new AlphaAction();
		alphaAction.setAlpha(1f);
		alphaAction.setDuration(2);
		logo.addAction(alphaAction);

		SizeToAction sizeToAction = new SizeToAction();
		sizeToAction.setDuration(2);
		sizeToAction.setSize(logo.getWidth() * 0.75f, logo.getHeight() * 0.75f);
		logo.addAction(sizeToAction);

		player = new TtsCtrl();
		player.load("sounds/common/welcome_starfish.ogg");
		player.speakOut();
	}

	@Override
	public void hide() {
		player.unload();
	}

	@Override
	public void dispose() {
		Assets.getInstance().unLoadPreLoading();
		super.dispose();
	}
}

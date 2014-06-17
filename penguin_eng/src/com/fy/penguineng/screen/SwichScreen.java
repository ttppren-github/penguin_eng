/**
 * 
 */
package com.fy.penguineng.screen;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.fy.penguineng.Assets;
import com.fy.penguineng.BaseStage;
import com.fy.penguineng.PenguinEng;
import com.fy.penguineng.world.WordPool;

/**
 * @author liufy
 * 
 */
public class SwichScreen implements Screen {
	private BaseStage stage;
	private PenguinEng gameMain;
	private Image bg;
	private ImageButton btnReturn;
	private ArrayList<ImageButton> groups;

	/**
	 * 
	 * 
	 */
	public SwichScreen(PenguinEng game) {
		this.gameMain = game;
		groups = new ArrayList<ImageButton>();

		Assets assets = Assets.getInstance();
		stage = new BaseStage();
		bg = new Image(assets.getTexture(Assets.SwitchScreen_Bg));
		stage.addActor(bg);

		btnReturn = new ImageButton(assets.skin, Assets.BtnReturn);
		btnReturn.setPosition(20, (float) (Assets.VIRTUAL_HEIGHT * 0.9) - 20);
		stage.addActor(btnReturn);
		btnReturn.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				gameMain.setScreen(gameMain.mainScreen);
			}
		});

		Table tab = new Table(assets.skin);
		for (int i = 0; i < 5; i++) {
			ImageButton tBtn = new ImageButton(assets.skin, Assets.BtnStar);
			tBtn.sizeBy(72, 72);
			Label lab = new Label(Integer.toString(i + 1), assets.skin,
					Assets.FONT);
			tBtn.add(lab);
			tab.add(tBtn).pad(20);
			if (i == 2) {
				tab.row();
			}

			tBtn.addListener(new ClickListener() {

				@Override
				public void clicked(InputEvent event, float x, float y) {
					int i = groups.indexOf(event.getListenerActor()) + 1;

					WordPool pool = WordPool.getInstance();
					String str = String.format("dic/%dstage_dic.json", i);
					pool.loadJson(str);

					if (gameMain.ttsListener != null) {
						String gram = String.format(
								"models/grammar/words/%dstage.gram", i);
						gameMain.ttsListener.loadGrammar(gram);
					}

					gameMain.setScreen(gameMain.gameScreen);
				}
			});
			groups.add(tBtn);
		}
		tab.add(new Image(assets.getTexture(Assets.LockStar)));
		tab.setPosition((float) (Assets.VIRTUAL_WIDTH - tab.getWidth()) / 2,
				(float) (Assets.VIRTUAL_HEIGHT * 0.65 - tab.getHeight()));
		stage.addActor(tab);
	}

	@Override
	public void render(float delta) {
		stage.act(delta);
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void hide() {
		Gdx.input.setInputProcessor(null);
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
		groups.clear();
		stage.dispose();
	}

}

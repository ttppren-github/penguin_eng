/**
 * 
 */
package com.fy.penguineng.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.fy.penguineng.Assets;
import com.fy.penguineng.BaseStage;
import com.fy.penguineng.FreetypeFontWrap;
import com.fy.penguineng.PenguinEng;
import com.fy.penguineng.ScoreManager;
import com.fy.penguineng.icontrol.IScoreManager;
import com.fy.penguineng.world.WordPool;

/**
 * @author liufy
 * 
 */
public class GamePassScreen implements Screen {
	private final String RETRY = "再来一次";
	private final String NEXT = "下一关";
	private final String RETURN = "主菜单";
	private final String C1 = "恭喜您：\n        在本关中成功阻止雪山融化，并获得\"%s\"称号。";
	private final String[] C2 = { "环保卫士", "环保斗士", "环保勇士" };

	private PenguinEng gameMain;

	private BaseStage stage;
	private Button btnRetry, btnBack, btnNext;
	private TextArea c1;

	/**
	 * 
	 */
	public GamePassScreen(PenguinEng game) {
		this.gameMain = game;
		stage = new BaseStage();

		FreetypeFontWrap font = new FreetypeFontWrap();
		LabelStyle labelStyle = new LabelStyle(font.getFont(RETURN + RETRY
				+ NEXT), Color.BLACK);

		btnRetry = new Button(Assets.getInstance().skin, Assets.Btn);
		btnRetry.add(new Label(RETRY, labelStyle));
		btnRetry.addListener(clickListener);

		btnBack = new Button(Assets.getInstance().skin, Assets.Btn);
		btnBack.add(new Label(RETURN, labelStyle));
		btnBack.addListener(clickListener);

		btnNext = new Button(Assets.getInstance().skin, Assets.Btn);
		btnNext.add(new Label(NEXT, labelStyle));
		btnNext.addListener(clickListener);

		final Table tableRoot = new Table();
		tableRoot.setFillParent(true);
		tableRoot.add(btnRetry);
		tableRoot.row();
		tableRoot.row().spaceTop(20);
		tableRoot.add(btnNext);
		tableRoot.row();
		tableRoot.row().spaceTop(20);
		tableRoot.add(btnBack);
		tableRoot.defaults().align(Align.center);
		tableRoot.padTop(50);
		stage.addActor(tableRoot);

		// Check score
		int key = Integer.valueOf(WordPool.getInstance().getStage());
		IScoreManager sm = ScoreManager.getInstance();
		int s = sm.getLevel(key);

		String text = String.format(C1, C2[s]);
		TextFieldStyle lStyle = new TextFieldStyle();
		lStyle.font = font.getFont(replayHZ(text), 24);
		lStyle.fontColor = Color.BLACK;
		c1 = new TextArea(text, lStyle);
		c1.setBounds(40, 540, 400, 120);
		stage.addActor(c1);
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
		stage.dispose();
	}

	private ClickListener clickListener = new ClickListener() {

		@Override
		public void clicked(InputEvent event, float x, float y) {
			if (event.getListenerActor() == btnRetry) {
				WordPool.getInstance().reload();
				gameMain.setScreen(gameMain.gameScreen);
			} else if (event.getListenerActor() == btnBack) {
				gameMain.setScreen(gameMain.mainScreen);
			} else if (event.getListenerActor() == btnNext) {
				WordPool pool = WordPool.getInstance();
				String s = pool.getStage();
				int i = Integer.valueOf(s) + 1;

				String str = String.format("dic/%dstage_dic.json", i);
				pool.loadJson(str);

				if (gameMain.recognizerCtrl != null) {
					String gram = String.format(
							"models/grammar/words/%dstage.gram", i);
					gameMain.recognizerCtrl.loadGrammar(gram);
				}
				gameMain.setScreen(gameMain.gameScreen);
			}
		}

	};

	private String replayHZ(final String text) {
		String ret = "";
		StringBuffer strBuf = new StringBuffer();
		char c;
		int j;

		if (text == null) {
			return ret;
		}

		for (int i = 0; i < text.length(); i++) {
			c = text.charAt(i);
			for (j = 0; j < strBuf.length(); j++) {
				if (c == strBuf.charAt(j)) {
					break;
				}
			}

			if (j == strBuf.length()) {
				strBuf.append(c);
			}
		}

		ret = strBuf.toString();
		return ret;
	}
}

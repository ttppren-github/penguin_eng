/**
 * 
 */
package com.fy.penguineng.screen;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Window.WindowStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.esotericsoftware.tablelayout.BaseTableLayout;
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
public class SwichScreen implements Screen {
	public static int passCnt;
	// private final String TAG = "SwichScreen";
	private BaseStage stage;
	private PenguinEng gameMain;
	private Image bg;
	private ImageButton btnReturn;
	private ArrayList<ImageButton> groups;
	private Label[] labs;
	private Table tab;
	private Window window;
	private Button btnOk;

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
		btnReturn.setPosition(20, (float) (Assets.VIRTUAL_HEIGHT * 0.92) - 20);
		stage.addActor(btnReturn);
		btnReturn.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				gameMain.setScreen(gameMain.mainScreen);
			}
		});

		tab = new Table(assets.skin);
		tab.setWidth(380);
		tab.setHeight(600);
		tab.setPosition((float) (Assets.VIRTUAL_WIDTH - tab.getWidth()) / 2,
				(float) (Assets.VIRTUAL_HEIGHT - tab.getHeight()) / 2);
		tab.align(BaseTableLayout.TOP | BaseTableLayout.LEFT);

		Pixmap pm = new Pixmap(380, 600, Format.RGBA8888);
		pm.setColor(0.28f, 0.63f, 0.97f, 0.4f);
		pm.fill();
		TextureRegion bg = new TextureRegion(new Texture(pm));
		tab.setBackground(new TextureRegionDrawable(bg));

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
		refreshStat();

		InputMultiplexer inputMultiplexer = new InputMultiplexer();
		inputMultiplexer.addProcessor(stage);
		inputMultiplexer.addProcessor(new ScreenInputHandler());
		Gdx.input.setInputProcessor(inputMultiplexer);
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

	private class ScreenInputHandler implements InputProcessor {

		@Override
		public boolean keyDown(int keycode) {
			if (keycode == Keys.BACK || keycode == Keys.BACKSPACE) {
				gameMain.setScreen(gameMain.mainScreen);
			}
			return false;
		}

		@Override
		public boolean keyUp(int keycode) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean keyTyped(char character) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean touchDown(int screenX, int screenY, int pointer,
				int button) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean touchUp(int screenX, int screenY, int pointer, int button) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean touchDragged(int screenX, int screenY, int pointer) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean mouseMoved(int screenX, int screenY) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean scrolled(int amount) {
			// TODO Auto-generated method stub
			return false;
		}
	}

	private void refreshStat() {
		IScoreManager sm = ScoreManager.getInstance();
		Assets assets = Assets.getInstance();

		int gameCnt = 12;// sm.getStageCount();
		passCnt = sm.getPassCount();
		labs = new Label[gameCnt];

		// must be clear befor add some
		tab.clearChildren();
		groups.clear();

		for (int i = 0; i < gameCnt; i++) {
			ImageButton tBtn;

			if (0 == i % 3) {
				tab.row();
			}

			// add star
			if (i > passCnt) {
				tBtn = new ImageButton(assets.skin, Assets.LockStar);
			} else {
				tBtn = new ImageButton(assets.skin, Assets.BtnStar);
			}
			tab.add(tBtn).width(110).pad(10);

			if (i > passCnt) {
				continue;
			}

			labs[i] = new Label(Integer.toString(sm.getLevel(i + 1)),
					assets.skin, Assets.FONT);
			tBtn.add(labs[i]);

			tBtn.addListener(new ClickListener() {

				@Override
				public void clicked(InputEvent event, float x, float y) {
					int i = groups.indexOf(event.getListenerActor()) + 1;

					if (i > passCnt) {
						showPopup();
						return;
					}

					WordPool pool = WordPool.getInstance();
					String str = String.format("dic/%dstage_dic.json", i);
					// Gdx.app.log(TAG, str);
					pool.loadJson(str);

					if (gameMain.recognizerCtrl != null) {
						String gram = String.format(
								"models/grammar/words/%dstage.gram", i);
						gameMain.recognizerCtrl.loadGrammar(gram);
					}

					gameMain.setScreen(gameMain.gameScreen);
				}
			});

			groups.add(tBtn);
		}
	}

	private void showPopup() {
		Assets assets = Assets.getInstance();
		FreetypeFontWrap font = new FreetypeFontWrap();
		BitmapFont bFont = font.getFont("知道了正在建设中，敬请期待。");
		LabelStyle labelStyle = new LabelStyle(bFont, Color.BLACK);

		WindowStyle style = new WindowStyle(bFont, Color.BLACK, null);

		window = new Window("正在建设中，敬请期待。", style);
		window.setWidth(400);
		window.setHeight(200);
		window.setPosition(40, 200);
		window.setModal(true);
		window.defaults().padTop(50);

		Pixmap pm = new Pixmap(380, 600, Format.RGBA8888);
		pm.setColor(0.28f, 0.63f, 0.97f, 1f);
		pm.fill();
		TextureRegion bg = new TextureRegion(new Texture(pm));
		window.setBackground(new TextureRegionDrawable(bg));

		btnOk = new Button(assets.skin, Assets.Btn);
		Label lab = new Label("知道了", labelStyle);
		btnOk.add(lab);
		btnOk.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if (event.getListenerActor() == btnOk) {
					closePopup();
				}
			}
		});
		btnOk.setPosition(80, 70);
		window.addActor(btnOk);

		stage.addActor(window);
	}

	private void closePopup() {
		if (null == window) {
			return;
		}

		if (window.isVisible()) {
			window.remove();
			window = null;
		}
	}
}

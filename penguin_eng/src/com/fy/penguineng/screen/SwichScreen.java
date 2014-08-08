/**
 * 
 */
package com.fy.penguineng.screen;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.ui.Window.WindowStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.esotericsoftware.tablelayout.BaseTableLayout;
import com.fy.penguineng.Assets;
import com.fy.penguineng.FreetypeFontWrap;
import com.fy.penguineng.PenguinEng;
import com.fy.penguineng.ScoreManager;
import com.fy.penguineng.icontrol.IScoreManager;
import com.fy.penguineng.world.WordPool;

/**
 * @author liufy
 * 
 */
public class SwichScreen extends BaseScreen {
	private final static int MAX = 12;
	// private final String TAG = "SwichScreen";
	private PenguinEng gameMain;
	private ImageButton btnReturn;
	private ArrayList<ImageButton> groups;
	private Table tab;
	private Window window;
	private Button btnOk;
	private int passCnt, gameCnt;

	/**
	 * 
	 * 
	 */
	public SwichScreen(PenguinEng game) {
		this.gameMain = game;
		groups = new ArrayList<ImageButton>();

		this.setBackground(assets.getTexture(Assets.SwitchScreen_Bg));

		btnReturn = new ImageButton(assets.skin, Assets.BtnReturn);
		btnReturn.setPosition(20, (float) (Assets.VIRTUAL_HEIGHT * 0.92) - 20);
		baseStage.addActor(btnReturn);
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

		baseStage.addActor(tab);

		this.setOnBack(backListener);
	}

	@Override
	public void show() {
		refreshStat();
		super.show();
	}

	@Override
	public void dispose() {
		groups.clear();
		super.dispose();
	}

	private onBackListener backListener = new onBackListener() {

		@Override
		public void onBack() {
			gameMain.setScreen(gameMain.mainScreen);
		}

	};

	private void refreshStat() {
		IScoreManager sm = ScoreManager.getInstance();
		Assets assets = Assets.getInstance();

		gameCnt = sm.getStageCount();
		passCnt = sm.getPassCount();

		// must be clear befor add some
		tab.clearChildren();
		groups.clear();

		for (int i = 0; i < MAX; i++) {
			ImageButton tBtn;

			if (0 == i % 3) {
				tab.row();
			}

			// add star
			if (i > passCnt) {
				tBtn = new ImageButton(assets.skin, Assets.LockStar);
			} else {
				switch (sm.getLevel(i + 1)) {
				case 1:
					tBtn = new ImageButton(assets.skin, Assets.BtnStar1);
					break;
				case 2:
					tBtn = new ImageButton(assets.skin, Assets.BtnStar2);
					break;
				case 3:
					tBtn = new ImageButton(assets.skin, Assets.BtnStar3);
					break;
				default:
					tBtn = new ImageButton(assets.skin, Assets.BtnStar);
					break;
				}
			}
			tab.add(tBtn).width(110).pad(10);

			tBtn.addListener(new ClickListener() {

				@Override
				public void clicked(InputEvent event, float x, float y) {
					int nStage = groups.indexOf(event.getListenerActor()) + 1;

					if (nStage > passCnt + 1) {
						return;
					} else if (nStage > gameCnt) {
						showPopup();
						return;
					}

					WordPool pool = WordPool.getInstance();
					String str = String.format("dic/%dstage_dic.json", nStage);
					// Gdx.app.log(TAG, str);
					pool.loadJson(str);

					if (gameMain.recognizerCtrl != null) {
						String gram = String.format(
								"models/grammar/words/%dstage.gram", nStage);
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

		baseStage.addActor(window);
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

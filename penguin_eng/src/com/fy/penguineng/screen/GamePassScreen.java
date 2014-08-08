/**
 * 
 */
package com.fy.penguineng.screen;

import java.nio.ByteBuffer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.PixmapIO;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.ui.Window.WindowStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
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
public class GamePassScreen extends BaseScreen {
	private final String RETRY = "再来一次";
	private final String NEXT = "下一关";
	private final String RETURN = "主菜单";
	private final String SHARE = "分享";
	private final String C1 = "恭喜您：\n        在本关中成功阻止雪山融化，并获得\"%s\"称号。";
	private final String[] C2 = { "环保卫士", "环保斗士", "环保勇士" };

	private PenguinEng gameMain;
	private Button btnRetry, btnBack, btnNext, btnShare;
	private TextArea letter;
	private Image letterBackground;

	/**
	 * 
	 */
	public GamePassScreen(PenguinEng game) {
		this.gameMain = game;

		FreetypeFontWrap font = new FreetypeFontWrap();
		LabelStyle labelStyle = new LabelStyle(font.getFont(RETURN + RETRY
				+ NEXT + SHARE), Color.BLACK);

		btnRetry = new Button(Assets.getInstance().skin, Assets.Btn);
		btnRetry.add(new Label(RETRY, labelStyle));
		btnRetry.addListener(clickListener);

		btnBack = new Button(Assets.getInstance().skin, Assets.Btn);
		btnBack.add(new Label(RETURN, labelStyle));
		btnBack.addListener(clickListener);

		btnNext = new Button(Assets.getInstance().skin, Assets.Btn);
		btnNext.add(new Label(NEXT, labelStyle));
		btnNext.addListener(clickListener);

		btnShare = new Button(Assets.getInstance().skin, Assets.Btn);
		btnShare.add(new Label(SHARE, labelStyle));
		btnShare.addListener(clickListener);

		final Table tableRoot = new Table();
		tableRoot.setBounds(0, 0, Assets.VIRTUAL_WIDTH,
				Assets.VIRTUAL_HEIGHT / 2);
		tableRoot.add(btnRetry);
		tableRoot.row();
		tableRoot.row().spaceTop(20);
		tableRoot.add(btnNext);
		tableRoot.row();
		tableRoot.row().spaceTop(20);
		tableRoot.add(btnBack);
		tableRoot.row();
		tableRoot.row().spaceTop(20);
		tableRoot.add(btnShare);
		tableRoot.defaults().align(Align.center);
		tableRoot.padTop(50);
		baseStage.addActor(tableRoot);

		// Check score
		int key = Integer.valueOf(WordPool.getInstance().getStage());
		IScoreManager sm = ScoreManager.getInstance();
		int s = sm.getLevel(key);

		String text = String.format(C1, C2[s]);
		TextFieldStyle lStyle = new TextFieldStyle();
		lStyle.font = font.getFont(text, 24);
		lStyle.fontColor = Color.BLACK;
		letter = new TextArea(text, lStyle);
		letter.setBounds(80, 460, 300, 200);
		letterBackground = new Image(assets.getTexture(Assets.Letter));
		letterBackground.setBounds(20, 420, 440, 360);
		baseStage.addActor(letterBackground);
		baseStage.addActor(letter);

		this.setBackground(assets.getTexture(Assets.BgSucc));

		String stage = WordPool.getInstance().getStage();
		int level = ScoreManager.getInstance().getLevel(Integer.valueOf(stage));
		for (int i = 0; i < level; i++) {
			Image img = new Image(assets.getTexture(Assets.Medal));
			img.setPosition(Assets.VIRTUAL_WIDTH / 2 + i * 70, 420);
			baseStage.addActor(img);
		}
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

				IScoreManager sm = ScoreManager.getInstance();
				if (i > sm.getStageCount()) {
					showPopup();
					return;
				}

				String str = String.format("dic/%dstage_dic.json", i);
				pool.loadJson(str);

				if (gameMain.recognizerCtrl != null) {
					String gram = String.format(
							"models/grammar/words/%dstage.gram", i);
					gameMain.recognizerCtrl.loadGrammar(gram);
				}
				gameMain.setScreen(gameMain.gameScreen);
			} else if (event.getListenerActor() == btnShare) {
				shareMedal();
			}
		}

	};
	private Button btnOk;
	private Window window;

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

	private void shareMedal() {
		Pixmap pixmap = getScreenshot(20, 420, 440, 360, true);
		PixmapIO.writePNG(Gdx.files.local("shot.png"), pixmap);
		pixmap.dispose();
	}

	private static Pixmap getScreenshot(int x, int y, int w, int h,
			boolean yDown) {
		final Pixmap pixmap = ScreenUtils.getFrameBufferPixmap(x, y, w, h);

		if (yDown) {
			// Flip the pixmap upside down
			ByteBuffer pixels = pixmap.getPixels();
			int numBytes = w * h * 4;
			byte[] lines = new byte[numBytes];
			int numBytesPerLine = w * 4;
			for (int i = 0; i < h; i++) {
				pixels.position((h - i - 1) * numBytesPerLine);
				pixels.get(lines, i * numBytesPerLine, numBytesPerLine);
			}
			pixels.clear();
			pixels.put(lines);
		}

		return pixmap;
	}
}

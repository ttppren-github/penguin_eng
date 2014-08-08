/**
 * 
 */
package com.fy.penguineng.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.ui.Window.WindowStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.fy.penguineng.Assets;
import com.fy.penguineng.FreetypeFontWrap;
import com.fy.penguineng.PenguinEng;

/**
 * @author liufy
 * 
 */
public class MainMenuScreen extends BaseScreen {

	private PenguinEng game;
	private Button btnStart, btnOk, btnCancel;
	private FreetypeFontWrap font;
	private LabelStyle labelStyle;
	private Window window;

	/**
	 * 
	 */
	public MainMenuScreen(PenguinEng game) {
		this.game = game;

		this.setBackground(assets.getTexture(Assets.MainMenu_Bg));

		font = new FreetypeFontWrap();
		labelStyle = new LabelStyle(font.getFont("开始退出"), Color.BLACK);

		Label lab = new Label("开始", labelStyle);
		btnStart = new Button(assets.skin, Assets.Btn);
		btnStart.add(lab);
		btnStart.setPosition(120, 220);
		baseStage.addActor(btnStart);
		btnStart.addListener(clicListener);

		this.setOnBack(backListener);
	}

	@Override
	public void dispose() {
		super.dispose();
		font.dispose();
	}

	private ClickListener clicListener = new ClickListener() {

		@Override
		public void clicked(InputEvent event, float x, float y) {
			if (event.getListenerActor() == btnStart) {
				game.setScreen(game.swichScreen);
			} else if (event.getListenerActor() == btnOk) {
				Gdx.app.exit();
			} else if (event.getListenerActor() == btnCancel) {
				closePopup();
			}
		}

	};

	private onBackListener backListener = new onBackListener() {

		@Override
		public void onBack() {
			if (window != null && window.isVisible()) {
				closePopup();
			} else {
				showPopup();
			}
		}

	};

	private void showPopup() {
		Assets assets = Assets.getInstance();
		WindowStyle style = new WindowStyle(assets.getFont(), Color.BLACK, null);

		window = new Window("确认退出么？", style);
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
		Label lab = new Label("退出", labelStyle);
		btnOk.add(lab);
		btnOk.addListener(clicListener);
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

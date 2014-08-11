/**
 * 
 */
package com.fy.penguineng.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.fy.penguineng.Assets;
import com.fy.penguineng.FreetypeFontWrap;
import com.fy.penguineng.PenguinEng;
import com.fy.penguineng.widgets.PopWindow;

/**
 * @author liufy
 * 
 */
public class MainMenuScreen extends BaseScreen {

	private final String MSG = "确认要退出？";
	private final String START = "开始";
	private final String ABOUT = "关于";
	private PenguinEng game;
	private Button btnStart, btnAbout;
	private FreetypeFontWrap font;
	private LabelStyle labelStyle;
	private PopWindow window;

	/**
	 * 
	 */
	public MainMenuScreen(PenguinEng game) {
		this.game = game;

		this.setBackground(assets.getTexture(Assets.MainMenu_Bg));

		font = new FreetypeFontWrap();
		labelStyle = new LabelStyle(font.getFont(START + ABOUT), Color.BLACK);

		Label lab = new Label(START, labelStyle);
		btnStart = new Button(assets.skin, Assets.Btn);
		btnStart.add(lab);
		btnStart.setPosition(120, 220);
		baseStage.addActor(btnStart);
		btnStart.addListener(clicListener);

		btnAbout = new Button(assets.skin, Assets.Btn);
		Label lab2 = new Label(ABOUT, labelStyle);
		btnAbout.add(lab2);
		btnAbout.setPosition(120, 160);
		btnAbout.addListener(clicListener);
		baseStage.addActor(btnAbout);

		this.setOnBack(backListener);

		window = PopWindow.create(baseStage, PopWindow.F_OK);
		window.setMessage(MSG);
		window.setOnOKListener(clicListener);
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
			} else if (null != window
					&& event.getListenerActor() == window.getBtnOk()) {
				Gdx.app.exit();
			} else if (event.getListenerActor() == btnAbout) {
				game.recognizerCtrl.showAbout();
			}
		}

	};

	private onBackListener backListener = new onBackListener() {

		@Override
		public void onBack() {
			if (window != null && window.isVisible()) {
				window.closePopup();
			} else {
				window.showPopup();
			}
		}

	};

}

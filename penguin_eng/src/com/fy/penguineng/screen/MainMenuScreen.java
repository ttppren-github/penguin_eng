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
	private PenguinEng game;
	private Button btnStart;
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
			} else if (null != window
					&& event.getListenerActor() == window.getBtnOk()) {
				Gdx.app.exit();
			}
		}

	};

	private onBackListener backListener = new onBackListener() {

		@Override
		public void onBack() {
			if (window != null && window.isVisible()) {
				window.closePopup();
			} else {
				window = PopWindow.create(baseStage, PopWindow.F_OK);
				window.setMessage(MSG);
				window.setOnOKListener(clicListener);
				window.showPopup();
			}
		}

	};

}

/**
 * 
 */
package com.fy.penguineng.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.ui.Window.WindowStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.fy.penguineng.Assets;
import com.fy.penguineng.BaseStage;
import com.fy.penguineng.FreetypeFontWrap;
import com.fy.penguineng.PenguinEng;

/**
 * @author liufy
 * 
 */
public class MainMenuScreen implements Screen {

	private BaseStage stage;
	private PenguinEng game;
	private Button btnStart;
	private Image bg;
	private FreetypeFontWrap font;

	/**
	 * 
	 */
	public MainMenuScreen(PenguinEng game) {
		this.game = game;

		Assets assets = Assets.getInstance();
		stage = new BaseStage();
		bg = new Image(assets.getTexture(Assets.MainMenu_Bg));
		stage.addActor(bg);

		font = new FreetypeFontWrap();
		LabelStyle labelStyle = new LabelStyle(font.getFont("开始退出"),
				Color.BLACK);

		Label lab = new Label("开始", labelStyle);
		btnStart = new Button(assets.skin, Assets.Btn);
		btnStart.add(lab);
		btnStart.setPosition(120, 220);
		stage.addActor(btnStart);
		btnStart.addListener(clicListener);
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
		stage.dispose();
		font.dispose();
	}

	private ClickListener clicListener = new ClickListener() {

		@Override
		public void clicked(InputEvent event, float x, float y) {
			if (event.getListenerActor() == btnStart) {
				game.setScreen(game.swichScreen);
			}
		}

	};

	private class ScreenInputHandler implements InputProcessor {

		@Override
		public boolean keyDown(int keycode) {
			if (keycode == Keys.BACK || keycode == Keys.BACKSPACE) {
				 if (game.recognizerCtrl != null) {
				 game.recognizerCtrl.closeGame();
				 }
				// Assets assets = Assets.getInstance();
				//
				// WindowStyle style = new WindowStyle(assets.getFont(),
				// Color.BLACK, null);
				// Window window = new Window("Hello libgdx game", style);
				//
				// window.setWidth(Gdx.graphics.getWidth() / 2);
				// window.setHeight(Gdx.graphics.getHeight() / 3);
				// window.setPosition(100, 200);
				// window.setModal(true);
				//
				// Button ok = new Button(assets.skin, assets.BtnReturn);
				// window.addActor(ok);
				//
				// stage.addActor(window);
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
}

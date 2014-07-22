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
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.ui.Window.WindowStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
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
	private Button btnStart, btnOk, btnCancel;
	private Image bg;
	private FreetypeFontWrap font;
	private LabelStyle labelStyle;
	private Window window;

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
		labelStyle = new LabelStyle(font.getFont("开始确认取消"), Color.BLACK);

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
			} else if (event.getListenerActor() == btnOk) {
				Gdx.app.exit();
			} else if (event.getListenerActor() == btnCancel) {
				window.remove();
			}
		}

	};

	private class ScreenInputHandler implements InputProcessor {

		@Override
		public boolean keyDown(int keycode) {
			if (keycode == Keys.BACK || keycode == Keys.BACKSPACE
					|| Keys.ESCAPE == keycode) {
				Assets assets = Assets.getInstance();
				WindowStyle style = new WindowStyle(assets.getFont(),
						Color.BLACK, null);
				style.titleFont = font.getFont("确认退出？");
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
				Label lab = new Label("确认", labelStyle);
				btnOk.add(lab);
				btnOk.addListener(clicListener);
				btnOk.setPosition(80, 70);
				window.addActor(btnOk);

				btnCancel = new Button(assets.skin, Assets.Btn);
				Label lab2 = new Label("取消", labelStyle);
				btnCancel.add(lab2);
				btnCancel.setPosition(80, 20);
				btnCancel.addListener(clicListener);
				window.addActor(btnCancel);

				stage.addActor(window);
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

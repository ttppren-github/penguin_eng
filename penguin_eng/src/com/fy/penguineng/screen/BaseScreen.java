package com.fy.penguineng.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.fy.penguineng.Assets;

public class BaseScreen implements Screen {
	protected Assets assets;
	protected Stage baseStage;
	private Sprite background;
	private Batch batch;

	public BaseScreen() {
		Viewport viewport = new StretchViewport(Assets.VIRTUAL_WIDTH,
				Assets.VIRTUAL_HEIGHT);
		baseStage = new Stage(viewport);
		batch = baseStage.getSpriteBatch();

		assets = Assets.getInstance();
		background = new Sprite();
	}

	@Override
	public void render(float delta) {
		batch.begin();
		background.draw(batch);
		batch.end();

		baseStage.act(delta);
		baseStage.draw();
	}

	@Override
	public void resize(int width, int height) {
		baseStage.getViewport().update(width, height, true);
	}

	@Override
	public void show() {
		InputMultiplexer inputMultiplexer = new InputMultiplexer();
		inputMultiplexer.addProcessor(baseStage);
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
		baseStage.dispose();
		Gdx.app.log(this.toString(), "dispose()");
	}

	public void setBackground(Texture background) {
		if (null == background) {
			return;
		}

		this.background.setRegion(background);
		this.background.setBounds(0, 0, background.getWidth(),
				background.getHeight());
	}

	public void setOnBack(onBackListener listener) {
		goBackHandler = listener;
	}

	public interface onBackListener {
		public void onBack();
	}

	private onBackListener goBackHandler;

	private class ScreenInputHandler implements InputProcessor {

		@Override
		public boolean keyDown(int keycode) {

			if (keycode == Keys.BACK || keycode == Keys.BACKSPACE
					|| Keys.ESCAPE == keycode) {
				if (null != goBackHandler) {
					goBackHandler.onBack();
				}
			}
			return true;
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

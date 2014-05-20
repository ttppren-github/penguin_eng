package com.fy.penguineng;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class EasyDemoScreen implements Screen {
	private static final String TAG = "Game screen";
	Game game;
	WorldControl world;
	WorldRender renderer;
	SpriteBatch batcher;
	OrthographicCamera cam;
	private Vector3 touchPoint;

	public EasyDemoScreen(Game game) {
		batcher = new SpriteBatch();
		world = new WorldControl();
		renderer = new WorldRender(batcher, world);
		touchPoint = new Vector3();

		this.game = game;
	}

	@Override
	public void render(float delta) {
		cam.update();

		world.update(delta);
		renderer.render();

		if (Gdx.input.justTouched()) {
			int x = Gdx.input.getX();
			int y = (int) (Assets.world_height - Gdx.input.getY());

			touchPoint.set(x, Gdx.input.getY(), 0);
			cam.unproject(touchPoint);

			if (pointInRectangle(world.playBtn.bounds, touchPoint.x,
					touchPoint.y)) {
				if (WorldControl.WORLD_STATE_READY == world.state
						|| WorldControl.WORLD_STATE_PAUSED == world.state) {
					world.state = WorldControl.WORLD_STATE_RUNNING;
				} else if (WorldControl.WORLD_STATE_RUNNING == world.state) {
					world.state = WorldControl.WORLD_STATE_PAUSED;
				}
			}
		}
	}

	@Override
	public void resize(int width, int height) {
		Gdx.app.log("Resize", "new width=" + width + " hei=" + height);

		cam.setToOrtho(false, width, height);
		batcher.setProjectionMatrix(cam.combined);
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

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
	public void show() {
		cam = new OrthographicCamera();

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	public void setText(String text) {
		String word =world.bob.getWord();
		if (word.matches(text)){
			world.bob.hit();
		}
		
		Gdx.app.log(TAG, "Got:" + text + ", word:" + word);
	}

	private boolean pointInRectangle(Rectangle r, float x, float y) {
		return r.x <= x && r.x + r.width >= x && r.y <= y
				&& r.y + r.height >= y;
	}
}

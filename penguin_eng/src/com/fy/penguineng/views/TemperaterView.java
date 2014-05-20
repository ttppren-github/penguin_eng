package com.fy.penguineng.views;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.fy.penguineng.Assets;
import com.fy.penguineng.WorldControl;

public class TemperaterView extends BaseView {

	private final int x_pad = 30;
	private final int y_pad = 69;

	private TextureRegion textureRegion;

	public TemperaterView(SpriteBatch batch, WorldControl world) {
		super(batch, world);
	}

	public void render() {
		float x = world.temperater.position.x;
		float y = world.temperater.position.y;

		int h = (int) (world.temperater.height * Assets.TEMP_HEIGHT);
		textureRegion = new TextureRegion(Assets.temperater_b, 0,
				Assets.TEMP_HEIGHT - h, Assets.TEMP_WIDTH, h);

		batch.draw(Assets.temperater_a, x, y);
		batch.draw(textureRegion, x + x_pad, y + y_pad, Assets.TEMP_WIDTH, h);
	}
}

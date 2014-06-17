package com.fy.penguineng.world.views;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.fy.penguineng.Assets;

public class TemperaterView extends BaseView {

	private final int x_pad = 30;
	private final int y_pad = 69;
	private float height;
	private TextureRegion textureRegion;

	public void setHeight(float height) {
		this.height = height;
	}

	public void render(Batch batch) {
		float x = position.x;
		float y = position.y;

		int h = (int) (height * Assets.TEMP_HEIGHT);
		textureRegion = new TextureRegion(assets.getTexture(Assets.TEMP_F), 0,
				Assets.TEMP_HEIGHT - h, Assets.TEMP_WIDTH, h);

		batch.draw(assets.getTexture(Assets.TEMP_B), x, y);
		batch.draw(textureRegion, x + x_pad, y + y_pad, Assets.TEMP_WIDTH, h);
	}
}

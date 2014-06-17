package com.fy.penguineng.world.views;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.fy.penguineng.Assets;

public class PlayButtonView extends BaseView {

	@Override
	public void render(Batch batch) {
		float x = bound.x;
		float y = bound.y;

		batch.draw(assets.getTexture(Assets.PAUSE), x, y, bound.width,
				bound.height);
	}
}

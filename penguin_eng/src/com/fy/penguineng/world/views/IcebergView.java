package com.fy.penguineng.world.views;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.fy.penguineng.Assets;

public class IcebergView extends BaseView {

	@Override
	public void render(Batch batch) {
		float x = position.x;
		float y = position.y;

		batch.draw(assets.getTexture(Assets.ICEBERG), x, y, bound.width,
				bound.height);
	}
}

package com.fy.penguineng.views;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fy.penguineng.Assets;
import com.fy.penguineng.WorldControl;

public class IcebergView extends BaseView {

	public IcebergView(SpriteBatch batch, WorldControl world) {
		super(batch, world);
	}

	public void render() {
		float x = world.iceberg.position.x;
		float y = world.iceberg.position.y;

		batch.draw(Assets.iceberg, world.iceberg.position.x,
				world.iceberg.position.y, world.iceberg.bounds.width,
				world.iceberg.bounds.height);

	}
}

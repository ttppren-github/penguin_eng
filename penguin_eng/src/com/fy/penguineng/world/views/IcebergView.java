package com.fy.penguineng.world.views;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.fy.penguineng.Assets;

public class IcebergView extends BaseView {

	private Pixmap cav;

	public IcebergView(Rectangle bounds) {
		bound = bounds;
		cav = new Pixmap((int) bound.width, (int) bound.height, Format.RGBA8888);
		cav.setColor(Color.BLACK);
	}

	@Override
	public void render(Batch batch) {
		float x = position.x;
		float y = position.y;

		batch.draw(assets.getTexture(Assets.ICEBERG), x, y, bound.width,
				bound.height);

		cav.drawLine(0, 6, (int) bound.width, 6);
		cav.drawLine(20, 6, 20, (int) bound.height);
		batch.draw(new Texture(cav), x, y, bound.width, bound.height);
	}
}

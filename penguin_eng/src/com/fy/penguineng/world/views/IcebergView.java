package com.fy.penguineng.world.views;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.fy.penguineng.Assets;
import com.fy.penguineng.FreetypeFontWrap;

public class IcebergView extends BaseView {

	public String strHeight;

	private BitmapFont font;

	public IcebergView(Rectangle bounds) {
		bound = bounds;

		FreetypeFontWrap fontFree = new FreetypeFontWrap();
		font = fontFree.getFont("0123456789", 30);
		font.setColor(Color.BLACK);

		strHeight = new String("8848");
	}

	@Override
	public void render(Batch batch) {
		float x = position.x;
		float y = position.y;

		batch.draw(assets.getTexture(Assets.ICEBERG), x, y, bound.width,
				bound.height);

		batch.draw(assets.getTexture(Assets.Ruler), 340, y);
		font.draw(batch, strHeight, 200, bound.height + y);
	}

	public void setHeight(String height) {
		strHeight = height;
	}
}

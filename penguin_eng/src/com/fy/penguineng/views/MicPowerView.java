package com.fy.penguineng.views;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fy.penguineng.Assets;
import com.fy.penguineng.WorldControl;

public class MicPowerView extends BaseView {

	public MicPowerView(SpriteBatch batch, WorldControl world) {
		super(batch, world);
	}

	public void render() {
		float x = world.micPower.position.x;
		float y = world.micPower.position.y;

		// Cut a rectangle of alpha value 0
		Pixmap.setBlending(Pixmap.Blending.None);
		Assets.mask.setColor(world.micPower.color);
		Assets.mask.fillCircle(40, 40, 40);
		Pixmap.setBlending(Pixmap.Blending.SourceOver);
		Texture foreground = new Texture(Assets.mask);

		batch.draw(foreground, x + Assets.mic.getWidth() / 2 - 40, y
				- Assets.mic.getHeight() + 40);
		batch.draw(Assets.mic, x, y);

		// batch.draw(bg, x, y);
	}
}

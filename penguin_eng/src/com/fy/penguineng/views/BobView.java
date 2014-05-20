package com.fy.penguineng.views;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fy.penguineng.Assets;
import com.fy.penguineng.WorldControl;
import com.fy.penguineng.modules.Bob;

public class BobView extends BaseView {

	public BobView(SpriteBatch batch, WorldControl world) {
		super(batch, world);
	}

	public void render() {
		float x = world.bob.position.x;
		float y = world.bob.position.y;

		if (Bob.BOB_STATE_IDLE != world.bob.getState()) {

			batch.draw(Assets.wordBg, world.bob.bounds.x, world.bob.bounds.y,
					world.bob.bounds.width, world.bob.bounds.height);
			Assets.font.draw(batch, world.bob.getWord(), x, y);
		}
	}
}

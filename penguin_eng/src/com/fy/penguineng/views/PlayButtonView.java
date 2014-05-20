package com.fy.penguineng.views;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fy.penguineng.Assets;
import com.fy.penguineng.WorldControl;
import com.fy.penguineng.modules.PlayButton;

public class PlayButtonView extends BaseView {

	public PlayButtonView(SpriteBatch batch, WorldControl world) {
		super(batch, world);
	}

	public void render() {
		float x = world.playBtn.bounds.x;
		float y = world.playBtn.bounds.y;

		if (world.playBtn.state == PlayButton.PLAYING) {
			batch.draw(Assets.btn_play, x, y, 64, 64);
		} else if (world.playBtn.state == PlayButton.PAUSE) {
			batch.draw(Assets.btn_pause, x, y, 64, 64);
		}
	}
}

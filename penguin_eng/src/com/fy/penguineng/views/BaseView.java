package com.fy.penguineng.views;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fy.penguineng.WorldControl;

public class BaseView {

	protected WorldControl world;
	protected SpriteBatch batch;

	public BaseView(SpriteBatch batch, WorldControl world) {
		this.world = world;
		this.batch = batch;
	}
}

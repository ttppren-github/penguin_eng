package com.fy.penguineng.modules;

import com.badlogic.gdx.Gdx;

public class Iceberg extends ActiveObject {

	private static final float MELT_VELOCITY = 1;
	private static final float BOB_WIDTH = Gdx.graphics.getWidth();
	private static final float BOB_HEIGHT = 270f;

	float stateTime;

	public Iceberg(float x, float y) {
		super(x, y, BOB_WIDTH, BOB_HEIGHT);
		stateTime = 0;

		this.velocity.x = 0;
		this.velocity.y = MELT_VELOCITY;
	}

	public void update(float deltaTime, int temperature) {
		float tmp = (float) Math.sqrt(temperature);
		float dis = tmp * deltaTime;
		this.bounds.height -= dis;

		stateTime += deltaTime;
	}
}

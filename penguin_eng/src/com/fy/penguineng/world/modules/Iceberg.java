package com.fy.penguineng.world.modules;

import com.fy.penguineng.Assets;

public class Iceberg extends ActiveObject {

	private final int MELT_VELOCITY = 1;
	private static final float BOB_WIDTH = Assets.VIRTUAL_WIDTH;
	private static final float BOB_HEIGHT = 270f;
	private int ve;

	float stateTime;

	public Iceberg(float x, float y) {
		super(x, y, BOB_WIDTH, BOB_HEIGHT);
		stateTime = 0;
		ve = MELT_VELOCITY;
	}

	public void update(float deltaTime, int temperature) {
		float tmp = (float) Math.sqrt(temperature);
		float dis = tmp * deltaTime * ve;
		this.bounds.height -= dis;

		stateTime += deltaTime;
	}

	public void stop() {
		ve = 0;
	}

	public void start() {
		ve = MELT_VELOCITY;
	}

	public void reset() {
		this.bounds.height = BOB_HEIGHT;
	}
}

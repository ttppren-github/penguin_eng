package com.fy.penguineng.modules;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;

public class MicPower extends ActiveObject {

	private static final float MELT_VELOCITY = 1;
	private static final float BOB_WIDTH = 80f;
	private static final float BOB_HEIGHT = 80f;

	public Color color;

	public MicPower(float x, float y) {
		super(x, y, BOB_WIDTH, BOB_HEIGHT);

		this.velocity.x = 0;
		this.velocity.y = MELT_VELOCITY;

		color = new Color(1f, 0f, 0f, 1f);
	}

	public void update(float deltaTime, int msb) {
		float r = MathUtils.random(0, 1);
		float g = MathUtils.random(0, 1);
		float b = MathUtils.random(0, 1);
		color.set(r, g, b, 1f);
	}
}

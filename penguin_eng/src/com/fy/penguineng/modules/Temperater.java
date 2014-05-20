package com.fy.penguineng.modules;

public class Temperater extends BaseObject {
	private static final float BOB_WIDTH = 0.8f;
	private static final float BOB_HEIGHT = 0.8f;

	public static final int MAX_T = 20;
	public float height = 0;

	public Temperater(float x, float y) {
		super(x, y, BOB_WIDTH, BOB_HEIGHT);
	}

	public void update(float deltaTime, int temperature) {
		if (temperature < MAX_T) {
			height = (float) temperature / MAX_T;
		}
	}
}

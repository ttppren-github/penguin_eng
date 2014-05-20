package com.fy.penguineng.modules;

public class PlayButton extends BaseObject {
	private static final float WIDTH = 64f;
	private static final float HEIGHT = 64f;

	public int state;

	public static final int PLAYING = 0;
	public static final int PAUSE = 1;

	public PlayButton(float x, float y) {
		super(x, y, WIDTH, HEIGHT);

		state = 0;
	}

	public void update(float deltaTime, int state) {
		this.state = state;
	}
}

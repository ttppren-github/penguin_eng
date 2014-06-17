package com.fy.penguineng.world.modules;

import com.fy.penguineng.Assets;
import com.fy.penguineng.world.WordPool;

public class WordCloud extends ActiveObject {
	public static final int BOB_STATE_IDLE = -1;
	public static final int BOB_STATE_FLYING = 0;
	public static final int BOB_STATE_HIT = 1;

	private static final float BOB_MOVE_VELOCITY = 100;
	private static final float FLY_OUT_VELOCITY = 400;
	private static final float BOB_WIDTH = 20f;
	private static final float BOB_HEIGHT = 20f;

	public int state;
	public WordPool pool;
	float stateTime;
	String word;
	float startX, startY;

	public WordCloud(float x, float y) {
		super(x, y, BOB_WIDTH, BOB_HEIGHT);
		state = BOB_STATE_IDLE;
		stateTime = 0;
		startX = x;
		startY = y;
		this.velocity.x = 0;
		this.velocity.y = BOB_MOVE_VELOCITY;
		this.bounds.height = Assets.LETTER_HEIGHT + BOB_HEIGHT * 2;

		pool = WordPool.getInstance();
	}

	public void update(float deltaTime) {
		if (state == BOB_STATE_FLYING) {
			// velocity.add(WorldControl.gravity.x * deltaTime,
			// WorldControl.gravity.y * deltaTime);
			position.add(velocity.x * deltaTime, velocity.y * deltaTime);
			bounds.x = position.x - BOB_WIDTH;
			bounds.y = position.y - BOB_HEIGHT * 2;
		}

		if (state == BOB_STATE_HIT) {
			// do some when read right
			position.sub(FLY_OUT_VELOCITY * deltaTime, 0);
			bounds.x = position.x - BOB_WIDTH;
			bounds.y = position.y - BOB_HEIGHT * 2;
		}

		stateTime += deltaTime;

		if (BOB_STATE_IDLE == state) {
			return;
		}
	}

	public void hit() {
		state = BOB_STATE_HIT;
		stateTime = 0;
		pool.hit(word);
	}

	public void reset() {
		this.word = pool.mining();
		state = BOB_STATE_FLYING;
		stateTime = 0;
		this.position.x = this.startX - word.length() / 2 * 10;
		this.position.y = this.startY;
		this.bounds.x = this.position.x - BOB_WIDTH;
		this.bounds.y = this.position.y - BOB_HEIGHT * 2;
		this.bounds.width = Assets.LETTER_WIDTH * word.length() + BOB_WIDTH * 2;
	}

	public void FlyOut() {
		state = BOB_STATE_IDLE;
		stateTime = 0;
	}

	public int getState() {
		return state;
	}

	public String getWord() {
		return this.word;
	}
}

package com.fy.penguineng;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.fy.penguineng.modules.Bob;
import com.fy.penguineng.modules.Iceberg;
import com.fy.penguineng.modules.MicPower;
import com.fy.penguineng.modules.PlayButton;
import com.fy.penguineng.modules.Temperater;

public class WorldControl {
	private static final String TAG = "World Control";
	public static final int WORLD_STATE_RUNNING = 0;
	public static final int WORLD_STATE_NEXT_LEVEL = 1;
	public static final int WORLD_STATE_GAME_FAIL = 2;
	public static final int WORLD_STATE_READY = 3;
	public static final int WORLD_STATE_PAUSED = 4;
	public static final int WORLD_STATE_GAME_PASS = 5;
	private static final float BOB_BEGIN = 0.3f;
	private static final float BOB_OVER = 0.9f;
	private static final float ICEBERG_Y = 0.36f;
	private static final float TEMPERATER_Y = 0.3f;

	public static final Vector2 gravity = new Vector2(0, 20);

	public final Bob bob;
	public final Iceberg iceberg;
	public final Temperater temperater;
	public final MicPower micPower;
	public final PlayButton playBtn;
	public int state;

	private int temperature = 0;
	WorldRender render;

	public WorldControl() {
		this.bob = new Bob(Assets.world_width / 2, Assets.world_height
				* BOB_BEGIN);
		this.iceberg = new Iceberg(0, Assets.world_height * ICEBERG_Y);
		this.temperater = new Temperater(10, Assets.world_height * TEMPERATER_Y);
		this.micPower = new MicPower(Assets.world_width - 80,
				Assets.world_height * 0.9f);
		this.playBtn = new PlayButton(Assets.world_width - 32, 32);

		bob.reset();
		state = WORLD_STATE_RUNNING;
	}

	public void update(float deltaTime) {
		if (WORLD_STATE_RUNNING == state) {
			updateBob(deltaTime);
			updateIceberg(deltaTime);
			temperater.update(deltaTime, temperature);
			micPower.update(deltaTime, 0);
		}

		// Draw paused UI
		updatePaused(deltaTime);
	}

	private void updateBob(float deltaTime) {
		// First check word pool state
		if (bob.pool.checkOver()) {
			bob.state = Bob.BOB_STATE_IDLE;
			gamePass();
		}

		if (bob.state == Bob.BOB_STATE_FLYING
				&& bob.position.y > Assets.world_height * BOB_OVER) {
			bob.goAway();
			temperature += 1;
			Gdx.app.log(TAG, "Missing word: tmep=" + temperature);
		}

		if (Bob.BOB_STATE_FALL == bob.state) {
			bob.reset();
		}
		// if (bob.state != Bob.BOB_STATE_HIT)
		// bob.velocity.x = -accelX / 10 * Bob.BOB_MOVE_VELOCITY;
		bob.update(deltaTime);
		// heightSoFar = Math.max(bob.position.y, heightSoFar);
	}

	private void updateIceberg(float deltaTime) {
		if (iceberg.bounds.height > 0) {
			iceberg.update(deltaTime, temperature);
		} else {
			gameFaill();
		}
	}

	private void updatePaused(float deltaTime) {
		if (WORLD_STATE_RUNNING == state) {
			playBtn.update(deltaTime, PlayButton.PAUSE);
		} else {
			playBtn.update(deltaTime, PlayButton.PLAYING);
		}
	}

	private void gameFaill() {
		state = WORLD_STATE_GAME_FAIL;
		Gdx.app.log(TAG, "Game over: faill");
	}

	private void gamePass() {
		state = WORLD_STATE_GAME_PASS;
		Gdx.app.log(TAG, "Game over: pass");
	}
}

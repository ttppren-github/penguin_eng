package com.fy.penguineng.world.modules;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Music.OnCompletionListener;
import com.fy.penguineng.Assets;
import com.fy.penguineng.TtsCtrl;
import com.fy.penguineng.icontrol.ITtsCtrl;
import com.fy.penguineng.world.WordPool;

public class WordCloud extends ActiveObject {
	public static final int BOB_STATE_IDLE = -1;
	public static final int BOB_STATE_RISING = 0;
	public static final int BOB_STATE_HIT = 1;
	public static final int BOB_STATE_SPEAKING = 2;

	private static final float BOB_MOVE_VELOCITY = 100;
	private static final float FLY_OUT_VELOCITY = 400;
	private static final float BOB_WIDTH = 20f;
	private static final float BOB_HEIGHT = 20f;

	public int state;
	public WordPool pool;

	private ITtsCtrl speaker;
	private String word;
	private float startX, startY;

	public WordCloud(float x, float y) {
		super(x, y, BOB_WIDTH, BOB_HEIGHT);
		state = BOB_STATE_IDLE;

		startX = x;
		startY = y;
		this.velocity.x = 0;
		this.velocity.y = BOB_MOVE_VELOCITY;
		this.bounds.height = Assets.LETTER_HEIGHT + BOB_HEIGHT * 2;

		pool = WordPool.getInstance();

		speaker = new TtsCtrl();
	}

	public void update(float deltaTime) {
		if (state == BOB_STATE_RISING) {
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

		if (BOB_STATE_IDLE == state) {
			return;
		}
	}

	public void hit() {
		state = BOB_STATE_HIT;
		pool.hit(word);
	}

	public void reset() {
		this.word = pool.mining();
		state = BOB_STATE_RISING;

		this.position.x = this.startX - word.length() / 2 * 10;
		this.position.y = this.startY;
		this.bounds.x = this.position.x - BOB_WIDTH;
		this.bounds.y = this.position.y - BOB_HEIGHT * 2;
		this.bounds.width = Assets.LETTER_WIDTH * word.length() + BOB_WIDTH * 2;

		speaker.unload();
		String path = "sounds/" + pool.getStage() + "/" + word.replace(" ", "")
				+ ".ogg";
		speaker.load(path);
	}

	public void FlyOut() {
		state = BOB_STATE_IDLE;
	}

	public int getState() {
		return state;
	}

	public String getWord() {
		return this.word;
	}

	public boolean isSpeaking() {
		return speaker.isSpeaking();
	}

	public void speak() {
		state = BOB_STATE_SPEAKING;
		speaker.setOnCompletionListener(listener);
		speaker.speakOut();
	}

	private OnCompletionListener listener = new OnCompletionListener() {

		@Override
		public void onCompletion(Music music) {
			state = BOB_STATE_IDLE;
		}

	};
}

/**
 * 
 */
package com.fy.penguineng.world;

import com.fy.penguineng.Assets;
import com.fy.penguineng.BaseStage;
import com.fy.penguineng.IGameControl;
import com.fy.penguineng.world.modules.Iceberg;
import com.fy.penguineng.world.modules.MicPower;
import com.fy.penguineng.world.modules.WordCloud;

/**
 * @author liufy
 * 
 */
public class GameStage extends BaseStage {
//	private static final String TAG = "World Control";
	private static final float BOB_BEGIN = 0.2f;
	private static final float BOB_OVER = 0.9f;
	private static final float ICEBERG_Y = 0.36f;

	protected final WordCloud bob;
	protected final Iceberg iceberg;
	protected final MicPower micPower;
	private WorldRender render;
	private int temperature = 0;
	private IPlayStateListener playStateListener;
	private int volume;
	private IGameControl ttsListener;
	private String wordFromMic;

	public interface IPlayStateListener {
		public void gameOver();

		public void gamePass();
	}

	/**
	 * 
	 */
	public GameStage(IGameControl ttsListener) {
		wordFromMic = new String();
		this.bob = new WordCloud(Assets.VIRTUAL_WIDTH / 2,
				Assets.VIRTUAL_HEIGHT * BOB_BEGIN);
		this.iceberg = new Iceberg(0, Assets.VIRTUAL_HEIGHT * ICEBERG_Y);
		this.micPower = new MicPower(Assets.VIRTUAL_WIDTH - 80,
				(float) (Assets.VIRTUAL_HEIGHT * 0.9));

		render = new WorldRender(this);
		this.ttsListener = ttsListener;
	}

	public void reset() {
		bob.reset();
		temperature = 0;
		iceberg.reset();
	}

	@Override
	public void draw() {
		batch.begin();
		render.render(batch);
		batch.end();

		super.draw();
	}

	@Override
	public void act(float deltaTime) {
		updateBob(deltaTime);
		updateIceberg(deltaTime);
		micPower.update(deltaTime, volume);

		super.act(deltaTime);
	}

	public void setWordCloudText(String text) {
		if (!ttsListener.isSpeaking()) {
			wordFromMic = text;
		}
	}

	public void setPlayCtrlListener(IPlayStateListener listener) {
		playStateListener = listener;
	}

	public void setVolume(int vol) {
		this.volume = vol;
	}

	private void updateBob(float deltaTime) {
		// First check word pool state
		if (bob.pool.checkOver()) {
			if (WordCloud.BOB_STATE_HIT != bob.state) {
				bob.state = WordCloud.BOB_STATE_IDLE;
				gamePass();
			}
		} else if (WordCloud.BOB_STATE_IDLE == bob.state
				&& !ttsListener.isSpeaking()) {
			bob.reset();
			wordFromMic = "";
			iceberg.start();
			ttsListener.startRecognizer();
		}

		if (WordCloud.BOB_STATE_FLYING == bob.state) {
			// Check word
			if (bob.getWord().matches(wordFromMic)) {
				bob.hit();
				iceberg.stop();
			} else if (bob.position.y > Assets.VIRTUAL_HEIGHT * BOB_OVER) {
				// word miss, first speak out, then renew word
				if (null != ttsListener) {
					ttsListener.stopRecognizer();
					ttsListener.speakOut(bob.getWord());
				}

				bob.FlyOut();
				temperature += 1;

				iceberg.stop();
			}
		}

		if (WordCloud.BOB_STATE_HIT == bob.state) {
			if (bob.position.x + bob.bounds.width < 0) {
				bob.FlyOut();
			}
		}

		if (bob.state == WordCloud.BOB_STATE_HIT
				|| WordCloud.BOB_STATE_FLYING == bob.state) {
			bob.update(deltaTime);
		}

	}

	private void updateIceberg(float deltaTime) {
		if (iceberg.bounds.height > 0) {
			iceberg.update(deltaTime, temperature);
		} else {
			gameFaill();
		}
	}

	private void gameFaill() {
		if (playStateListener == null) {
			throw new NullPointerException();
		}

		playStateListener.gameOver();
	}

	private void gamePass() {
		if (playStateListener == null) {
			throw new NullPointerException();
		}

		playStateListener.gamePass();
	}
}

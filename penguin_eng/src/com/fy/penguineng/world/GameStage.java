/**
 * 
 */
package com.fy.penguineng.world;

import com.fy.penguineng.Assets;
import com.fy.penguineng.BaseStage;
import com.fy.penguineng.ScoreManager;
import com.fy.penguineng.TtsCtrl;
import com.fy.penguineng.icontrol.IGameControl;
import com.fy.penguineng.icontrol.IScoreManager;
import com.fy.penguineng.world.modules.Iceberg;
import com.fy.penguineng.world.modules.MicPower;
import com.fy.penguineng.world.modules.WordCloud;

/**
 * @author liufy
 * 
 */
public class GameStage extends BaseStage {
	// private static final String TAG = "World Control";
	private static final float BOB_BEGIN = 0.2f;
	private static final float BOB_OVER = 0.9f;
	private static final float ICEBERG_Y = 0.36f;

	public int outValue;

	protected final WordCloud bob;
	protected final Iceberg iceberg;
	protected final MicPower micPower;
	private WorldRender render;
	private IPlayStateListener playStateListener;
	private int volume;
	private IGameControl recognizerCtrl;
	private String wordFromMic;
	private TtsCtrl[] goodS;
	private int correctCnt;
	private final String[] soundR = { "sounds/common/good.ogg",
			"sounds/common/great.ogg", "sounds/common/lovely.ogg",
			"sounds/common/wonderful.ogg", "sounds/common/awesome.ogg" };

	private IScoreManager scoreMgr;

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
		this.recognizerCtrl = ttsListener;

		goodS = new TtsCtrl[5];
		for (int i = 0; i < soundR.length; i++) {
			goodS[i] = new TtsCtrl();
			goodS[i].load(soundR[i]);
		}
		correctCnt = 0;

		scoreMgr = ScoreManager.getInstance();
	}

	public void reset() {
		bob.reset();
		iceberg.reset();
		outValue = 0;
		correctCnt = 0;
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
		if (!bob.isSpeaking()) {
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
		} else if (WordCloud.BOB_STATE_IDLE == bob.state) {
			bob.reset();
			wordFromMic = "";
			iceberg.start();
			if (null != recognizerCtrl) {
				recognizerCtrl.startRecognizer();
			}
		}

		if (WordCloud.BOB_STATE_RISING == bob.state) {
			// Check word
			if (bob.getWord().matches(wordFromMic)) {
				bob.hit();
				correctCnt++;
				iceberg.stop();

				if (correctCnt > 0) {
					// First should turn off recognizer, then speak.
					if (null != recognizerCtrl) {
						recognizerCtrl.stopRecognizer();
					}
					goodS[correctCnt / 4].speakOut();
					if (null != recognizerCtrl) {
						recognizerCtrl.startRecognizer();
					}
				}
			} else if (bob.position.y > Assets.VIRTUAL_HEIGHT * BOB_OVER) {
				// word miss, first speak out, then renew word
				if (null != recognizerCtrl) {
					recognizerCtrl.stopRecognizer();
				}

				bob.FlyOut();
				bob.speak();

				correctCnt = 0;
				outValue += bob.getWord().replace(" ", "").length();
				iceberg.stop();
			}
		}

		// Check if word disappear after read right
		if (WordCloud.BOB_STATE_HIT == bob.state) {
			if (bob.position.x + bob.bounds.width < 0) {
				bob.FlyOut();
			}
		}

		if (bob.state == WordCloud.BOB_STATE_HIT
				|| WordCloud.BOB_STATE_RISING == bob.state) {
			bob.update(deltaTime);
		}

	}

	private void updateIceberg(float deltaTime) {
		if (iceberg.bounds.height > 0) {
			iceberg.update(deltaTime, outValue);
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

		int socres = iceberg.mHeight - outValue;
		scoreMgr.setScores(Integer.valueOf(WordPool.getInstance().getStage()),
				socres);

		playStateListener.gamePass();
	}

	@Override
	public void dispose() {
		for (int i = 0; i < goodS.length; i++) {
			goodS[i].unload();
		}

		render.dispose();
		super.dispose();
	}

}

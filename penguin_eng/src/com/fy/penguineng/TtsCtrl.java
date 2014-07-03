package com.fy.penguineng;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.fy.penguineng.icontrol.ITtsCtrl;

public class TtsCtrl implements ITtsCtrl {

	private TtsCtrl instance;
	private Music music;

	private TtsCtrl() {
		music = null;
	}

	public TtsCtrl getInstance() {
		if (null == instance) {
			instance = new TtsCtrl();
		}

		return instance;
	}

	@Override
	public void speakOut(String word) {

	}

	@Override
	public boolean isSpeaking() {
		if (null != music && music.isPlaying()) {
			return true;
		}

		return false;
	}

}

package com.fy.penguineng.icontrol;

import com.badlogic.gdx.audio.Music.OnCompletionListener;

public interface ITtsCtrl {

	public void load(String src);

	public void unload();

	public void speakOut();

	public boolean isSpeaking();

	public void setOnCompletionListener(OnCompletionListener listener);
}

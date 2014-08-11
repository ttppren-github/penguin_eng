package com.fy.penguineng.icontrol;

public interface IGameControl {
	// Recognizer
	public void startRecognizer();

	public void stopRecognizer();

	public void loadGrammar(String file);

	public boolean checkFirstRun();

	public void shareToWX();

	public void showAbout();
}

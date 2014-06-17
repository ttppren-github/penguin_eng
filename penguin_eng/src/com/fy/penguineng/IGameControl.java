package com.fy.penguineng;

public interface IGameControl {
	// TTS
	public void speakOut(String word);

	public boolean isSpeaking();

	// Recognizer
	public void startRecognizer();

	public void stopRecognizer();

	public void loadGrammar(String file);

	// UI Contrl
	public void closeGame();

	public boolean checkFirstRun();

}

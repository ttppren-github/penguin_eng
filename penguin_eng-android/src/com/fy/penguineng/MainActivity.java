package com.fy.penguineng;

import static edu.cmu.pocketsphinx.SpeechRecognizerSetup.defaultSetup;
import static edu.cmu.pocketsphinx.Assets.syncAssets;

import java.io.File;
import java.io.IOException;

import android.os.Bundle;
import android.util.Log;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

import edu.cmu.pocketsphinx.Hypothesis;
import edu.cmu.pocketsphinx.RecognitionListener;
import edu.cmu.pocketsphinx.SpeechRecognizer;

public class MainActivity extends AndroidApplication implements
		RecognitionListener {
	private static final String DIGITS_SEARCH = "digits";
	private SpeechRecognizer recognizer;
	private PenguinEng game;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();

		game = new PenguinEng();
		initialize(game, cfg);

		File appDir;

		try {
			appDir = syncAssets(getApplicationContext());
		} catch (IOException e) {
			throw new RuntimeException("failed to synchronize assets", e);
		}

		recognizer = defaultSetup()
				.setAcousticModel(
						new File(appDir, "models/acoustic/hub4wsj_sc_8k"))
				.setDictionary(new File(appDir, "models/lm/en/turtle.dic"))
				.setRawLogDir(appDir).setKeywordThreshold(1e-5f)
				.getRecognizer();
		recognizer.addListener(this);

		File digitsGrammar = new File(appDir,
				"models/grammar/digitsLowLetter.gram");
		recognizer.addGrammarSearch(DIGITS_SEARCH, digitsGrammar);
	}

	@Override
	protected void onPause() {
		recognizer.stop();
		super.onPause();
	}

	@Override
	protected void onResume() {
		recognizer.startListening(DIGITS_SEARCH);
		super.onResume();
	}

	@Override
	public void onBeginningOfSpeech() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onEndOfSpeech() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPartialResult(Hypothesis hypothesis) {
		String text = hypothesis.getHypstr();
		String[] data = text.split(" ");
		String rel = data[data.length - 1];
		
		game.setRecognize(rel);
//		Log.d("Pengui", "text=" + rel);
	}

	@Override
	public void onResult(Hypothesis hypothesis) {
		String text = hypothesis.getHypstr();
		Log.d("Pengui", "text=" + text);
	}
}
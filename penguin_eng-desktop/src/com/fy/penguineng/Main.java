package com.fy.penguineng;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.fy.penguineng.icontrol.IGameControl;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "penguin_eng";
		// cfg.width = 320;
		// cfg.height = 480;
		cfg.width = 480;
		cfg.height = 800;

		PenguinEng eng = new PenguinEng();
		eng.setTtsListener(ttsListener);
		new LwjglApplication(eng, cfg);
	}

	private static IGameControl ttsListener = new IGameControl() {

		@Override
		public void startRecognizer() {

		}

		@Override
		public void stopRecognizer() {

		}

		@Override
		public void loadGrammar(String file) {

		}

		@Override
		public boolean checkFirstRun() {
			return false;
		}

		@Override
		public void shareToWX() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void showAbout() {
			// TODO Auto-generated method stub
			
		}
	};
}

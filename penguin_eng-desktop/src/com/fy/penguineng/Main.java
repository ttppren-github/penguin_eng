package com.fy.penguineng;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "penguin_eng";
//		cfg.width = 320;
//		cfg.height = 480;
		cfg.width = 480;
		cfg.height = 800;
		
		new LwjglApplication(new PenguinEng(), cfg);
	}
}

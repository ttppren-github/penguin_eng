package com.fy.penguineng;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {
	// private final static String TAG = "Asset";

	public static BitmapFont font;
	public static TextureRegion backgroundRegion;
	public static TextureRegion wordBg;
	public static Texture factory;
	public static Texture iceberg;
	public static Texture temperater_a;
	public static Texture temperater_b;
	public static Texture btn_play;
	public static Texture btn_pause;
	public static Texture mic;
	public static Pixmap mask;
	public static final int CLOUD_WIDTH = 100;
	public static final int CLOUD_HEIGHT = 50;
	public static final int LETTER_HEIGHT = 18;
	public static final int LETTER_WIDTH = 16;
	public static final int TEMP_WIDTH = 20;
	public static final int TEMP_HEIGHT = 224;
	public static float world_width = 480;
	public static float world_height = 800;

	private static Texture loadTexture(String file) {
		return new Texture(Gdx.files.internal(file));
	}

	public static void loadAsset() {
		// fontSkin = new BitmapFont(Gdx.files.internal("data/uiskin.fnt"),
		// Gdx.files.internal("data/uiskin.png"), false);
		// fontSkin = new Skin(Gdx.files.internal("data/uiskin.json"));
		font = new BitmapFont(Gdx.files.internal("data/font.fnt"),
				Gdx.files.internal("data/font.png"), false);

		Texture background = loadTexture("data/background.png");
		backgroundRegion = new TextureRegion(background, 0, 0, 480, 800);
		Texture wbg = loadTexture("data/cloud.png");
		wordBg = new TextureRegion(wbg, 0, 0, CLOUD_WIDTH, CLOUD_HEIGHT);

		factory = loadTexture("data/factory.png");
		iceberg = loadTexture("data/iceberg.png");
		temperater_a = loadTexture("data/temperater_a.png");
		temperater_b = loadTexture("data/temperater_b.png");
		mic = loadTexture("data/mic.png");
		btn_play = loadTexture("data/play.png");
		btn_pause = loadTexture("data/pause.png");
		mask = new Pixmap(80, 80, Format.RGBA8888);
	}
}

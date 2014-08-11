package com.fy.penguineng;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

public class FreetypeFontWrap {
	private BitmapFont freetypeBitmapFont;

	public BitmapFont getFont(String text) {
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(
				Gdx.files.internal("data/DroidSansFallbackFull.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = 36;
		parameter.characters = replayHZ(text);

		freetypeBitmapFont = generator.generateFont(parameter);
		generator.dispose(); // don't forget to dispose to avoid memory
								// leaks!

		return freetypeBitmapFont;
	}

	public BitmapFont getFont(String text, int size) {
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(
				Gdx.files.internal("data/DroidSansFallbackFull.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = size;
		parameter.characters = replayHZ(text);

		freetypeBitmapFont = generator.generateFont(parameter);
		generator.dispose(); // don't forget to dispose to avoid memory
								// leaks!

		return freetypeBitmapFont;
	}

	public void dispose() {
		freetypeBitmapFont.dispose();
	}

	private String replayHZ(final String text) {
		String ret = "";
		StringBuffer strBuf = new StringBuffer();
		char c;
		int j;

		if (text == null) {
			return ret;
		}

		for (int i = 0; i < text.length(); i++) {
			c = text.charAt(i);
			for (j = 0; j < strBuf.length(); j++) {
				if (c == strBuf.charAt(j)) {
					break;
				}
			}

			if (j == strBuf.length()) {
				strBuf.append(c);
			}
		}

		ret = strBuf.toString();
		return ret;
	}
}

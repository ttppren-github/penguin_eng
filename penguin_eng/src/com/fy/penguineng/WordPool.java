package com.fy.penguineng;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.MathUtils;

public class WordPool {

	private List<String> words;

	public WordPool() {
		words = new ArrayList<String>();
		words.add("one");
		words.add("two");
		words.add("three");
		words.add("four");
		words.add("five");
		words.add("six");
		words.add("seven");
		words.add("eight");
		words.add("nine");
		words.add("ten");
	}

	public String mining() {
		return words.get(random());
	}

	public void hit(String word) {
		words.remove(word);
	}

	public boolean checkOver() {
		return words.isEmpty();
	}

	private int random() {
		int ret = MathUtils.random(0, words.size() - 1);
		return ret;
	}

}

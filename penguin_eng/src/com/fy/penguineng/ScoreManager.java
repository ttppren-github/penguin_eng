/**
 * 
 */
package com.fy.penguineng;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.JsonWriter.OutputType;
import com.fy.penguineng.icontrol.IScoreManager;

/**
 * @author liufy
 * 
 */
public class ScoreManager implements IScoreManager {
	private final String NAME = "score.json";

	private static ScoreManager instance;
	private ScoreFactory scores;

	private ScoreManager() {
		scores = new ScoreFactory();

		loadJson(NAME);
	}

	/**
	 * @return
	 */
	public static ScoreManager getInstance() {
		if (null == instance) {
			instance = new ScoreManager();
		}

		return instance;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.fy.penguineng.icontrol.IScoreManager#getScores(int)
	 */
	@Override
	public int getScores(int stage) {
		int ret = 0;

		for (int i = 0; i < scores.size(); i++) {
			if (scores.getScore(i).stage == stage) {
				ret = scores.getScore(i).score;
				break;
			}
		}

		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.fy.penguineng.icontrol.IScoreManager#setScores(int, int)
	 */
	@Override
	public void setScores(int stage, int score) {
		int i;
		for (i = 0; i < scores.size(); i++) {
			if (scores.getScore(i).stage == stage) {
				scores.getScore(i).score = score;
				break;
			}
		}

		if (scores.size() == i) {
			scores.setScore(stage, score);
		}

		syncJson();
	}

	private void loadJson(String fileName) {
		FileHandle f = Gdx.files.local(NAME);
		if (!f.exists()) {
			syncJson();
		}

		JsonReader json = new JsonReader();
		JsonValue valRoot = json.parse(f);
		JsonValue val = valRoot.get("scores");

		for (int i = 0; i < val.size; i++) {
			JsonValue t = val.get(i);
			scores.setScore(t.getInt("stage"), t.getInt("score"));
		}
	}

	public void syncJson() {
		Json json = new Json(OutputType.json);
		json.toJson(scores, ScoreFactory.class, Gdx.files.local(NAME));
	}

	private class Score {
		public int stage;
		public int score;

		public Score(int stage, int score) {
			this.stage = stage;
			this.score = score;
		}
	}

	public class ScoreFactory {

		private ArrayList<Score> scores;

		public ScoreFactory() {
			scores = new ArrayList<Score>();
		}

		public Score getScore(int id) {
			return scores.get(id);
		}

		public void setScore(int stage, int score) {
			scores.add(new Score(stage, score));
		}

		public int size() {
			return scores.size();
		}
	}

}

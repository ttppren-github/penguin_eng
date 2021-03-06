/**
 * 
 */
package com.fy.penguineng.icontrol;

/**
 * @author liufy
 * 
 */
public interface IScoreManager {

	/**
	 * @param stage
	 * @return
	 */
	public int getScores(int stage);

	/**
	 * @param stage
	 * @param score
	 */
	public void setScores(int stage, int score);

	/**
	 * @return
	 */
	public int getLevel(int stage);

	/**
	 * @return Count of all game
	 */
	public int getStageCount();

	/**
	 * @return Count of passed game
	 */
	public int getPassCount();
}

package entities;

public class Game {

	private boolean paused;
	private int level;
	private int lines;
	private int score;
	
	public Game()
	{
		paused = true;
		level = 1;
		lines = 0;
		score = 0;
	}

	//getters and setters
	public boolean isPaused() {
		return paused;
	}
	public void setPaused(boolean paused) {
		this.paused = paused;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getLines() {
		return lines;
	}
	public void setLines(int lines) {
		this.lines = lines;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
}

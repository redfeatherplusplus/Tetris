package entities;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import entities.blocks.Block;
import entities.blocks.Tetromino;
import entities.blocks.tetrominos.I_Mino;
import entities.blocks.tetrominos.J_Mino;
import entities.blocks.tetrominos.L_Mino;
import entities.blocks.tetrominos.O_Mino;
import entities.blocks.tetrominos.S_Mino;
import entities.blocks.tetrominos.T_Mino;
import entities.blocks.tetrominos.Z_Mino;

public class TetrisGame {

	public static final int PLAY_AREA_WIDTH = 10;
	public static final int PLAY_AREA_HEIGHT = 20;
	public static final int TETROMINO_WIDTH = 2;
	
    //game data
	protected Block[][] playArea;
	protected List<Tetromino> bag;
	
	//tetromino game data
	protected Point start;
	protected Tetromino active;
	protected Tetromino next;
	
	//metagame data
	protected int level;
	protected int lines;
	protected int score;
	protected boolean paused;
	
	//default constructor
	public TetrisGame() {
		playArea = new Block[PLAY_AREA_WIDTH][PLAY_AREA_HEIGHT];
		bag = new ArrayList<Tetromino>();
		
		start = new Point(PLAY_AREA_WIDTH / 2 - TETROMINO_WIDTH, 0);
		active = nextTetrominoInBag(start);
		next = nextTetrominoInBag(start);
		
		level = 1;
		lines = 0;
		score = 0;
		paused = true;
	}
	
	//returns next tetromino in bag
	private Tetromino nextTetrominoInBag(Point position) {
		if (0 == bag.size()) {
			//bag is empty, re-fill and re-randomize bag
			bag.add(new I_Mino(position));
			bag.add(new J_Mino(position));
			bag.add(new L_Mino(position));
			bag.add(new O_Mino(position));
			bag.add(new S_Mino(position));
			bag.add(new T_Mino(position));
			bag.add(new Z_Mino(position));

			Collections.shuffle(bag, new Random(System.nanoTime()));
		}
		
		//remove and return next piece in bag
		return bag.remove(0);
	}

	//true if given tetromino is inside the play area
	public boolean inPlayArea(Tetromino tetromino) {
		Block[] blocks = tetromino.getBlocks();
		
		for (Block block : blocks) {
			int x = tetromino.getPosition().x + block.getPosition().x;
			int y = tetromino.getPosition().y + block.getPosition().y;
			
			//check if this location is in the play area
			if (x < 0 || x > PLAY_AREA_WIDTH - 1 ||
				y < 0 || y > PLAY_AREA_HEIGHT - 1) {
				return false;
			}
		}
		
		return true;
	}
	
	//true if given tetromino is colliding with a block
	public boolean isColliding(Tetromino tetromino) {
		Block[] blocks = tetromino.getBlocks();
		
		for (Block block : blocks) {
			int x = tetromino.getPosition().x + block.getPosition().x;
			int y = tetromino.getPosition().y + block.getPosition().y;
			
			//check if a block already exists at this location in the play area
			if (null != playArea[x][y]) {
				return true;
			}
		}
		
		return false;
	}
	
	//getters and setters
	public Block[][] getPlayArea() {
		return playArea;
	}
	public List<Tetromino> getBag() {
		return bag;
	}
	public Tetromino getActive() {
		return active;
	}
	public Tetromino getNext() {
		return next;
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
	public boolean isPaused() {
		return paused;
	}
	public void setPaused(boolean paused) {
		this.paused = paused;
	}
}

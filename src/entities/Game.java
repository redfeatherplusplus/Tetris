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

public class Game {

	public static final int WIDTH = 10;
	public static final int HEIGHT = 20;
	public static final int TETROMINO_WIDTH = 2;
	
    //game data
	private Block[][] blocks;
	private List<Tetromino> bag;
	
	//tetromino game data
	private Point start;
	private Tetromino active;
	private Tetromino next;
	
	//metagame data
	private int level;
	private int lines;
	private int score;
	private boolean paused;
	
	//default constructor
	public Game() {
		blocks = new Block[HEIGHT][WIDTH];
		bag = new ArrayList<Tetromino>();
		
		start = new Point(0, WIDTH / 2 - TETROMINO_WIDTH);
		active = nextTetrominoInBag(start);
		next = nextTetrominoInBag(start);
		
		level = 1;
		lines = 0;
		score = 0;
		paused = true;
	}
	
	//constructor with specified next and active pieces
	public Game(Tetromino active, Tetromino next) {
		blocks = new Block[HEIGHT][WIDTH];
		bag = new ArrayList<Tetromino>();
		
		this.active = active;
		this.next = next;
		
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

	//getters and setters
	public Block[][] getBlocks() {
		return blocks;
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

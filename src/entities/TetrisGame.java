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
	protected long updateInterval;
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
		
		updateInterval = 200;
		level = 1;
		lines = 0;
		score = 0;
		paused = false;
	}
	
	//steps the game one time state forward
	public void update() {
		//move the active tetromino down, note: this method places
		//the tetromino if it cannot be moved down
		moveActiveDown();
	}
	
	//returns next tetromino in bag
	private Tetromino nextTetrominoInBag(Point position) {
		if (0 == bag.size()) {
			//bag is empty, re-fill and re-randomize bag
			bag.add(new I_Mino((Point) position.clone()));
			bag.add(new J_Mino((Point) position.clone()));
			bag.add(new L_Mino((Point) position.clone()));
			bag.add(new O_Mino((Point) position.clone()));
			bag.add(new S_Mino((Point) position.clone()));
			bag.add(new T_Mino((Point) position.clone()));
			bag.add(new Z_Mino((Point) position.clone()));

			Collections.shuffle(bag, new Random(System.nanoTime()));
		}
		
		//remove and return next piece in bag
		return bag.remove(0);
	}

	//converts the tetromino into blocks
	private void placeTetromino(Tetromino tetromino) {
		Block[] blocks = tetromino.getBlocks();
		
		for (Block block : blocks) {
			block.getPosition().x += tetromino.getPosition().x;
			block.getPosition().y += tetromino.getPosition().y;
			
			playArea[block.getPosition().x][block.getPosition().y] = block;
		}
	}
	
	//true if given tetromino is inside the play area
	private boolean inPlayArea(Tetromino tetromino) {
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
	private boolean isColliding(Tetromino tetromino) {
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
	
	//block manipulation methods
	public void moveActiveUp() {
		//do nothing if paused
		if (paused) { return; }
		
		//test on a copy if the active tetromino can be moved up
		Tetromino activeCopy = active.Copy();
		activeCopy.moveUp();
		
		if (inPlayArea(activeCopy) && !isColliding(activeCopy)) {
			active.moveUp();
		}
	}
	public void moveActiveRight() {
		//do nothing if paused
		if (paused) { return; }
		
		//test on a copy if the active tetromino can be moved right
		Tetromino activeCopy = active.Copy();
		activeCopy.moveRight();
		
		if (inPlayArea(activeCopy) && !isColliding(activeCopy)) {
			active.moveRight();
		}
	}
	public void moveActiveDown() {
		//do nothing if paused
		if (paused) { return; }
		
		//test on a copy if the active tetromino can be moved down
		Tetromino activeCopy = active.Copy();
		activeCopy.moveDown();
		
		if (inPlayArea(activeCopy) && !isColliding(activeCopy)) {
			active.moveDown();
		}
		else {
			//active tetromino cannot be moved down, place it instead
			placeTetromino(active);
			active = next;
			next = nextTetrominoInBag(start);
		}
	}
	public void moveActiveLeft() {
		//do nothing if paused
		if (paused) { return; }
		
		//test on a copy if the active tetromino can be moved left
		Tetromino activeCopy = active.Copy();
		activeCopy.moveLeft();
		
		if (inPlayArea(activeCopy) && !isColliding(activeCopy)) {
			active.moveLeft();
		}
	}
	public void moveActiveClockwise() {
		//do nothing if paused
		if (paused) { return; }
		
		//test on a copy if the active tetromino can be moved clockwise
		Tetromino activeCopy = active.Copy();
		activeCopy.rotateClockWise();
		
		if (inPlayArea(activeCopy) && !isColliding(activeCopy)) {
			active.rotateClockWise();
		}
	}
	public void moveActiveCounterClockwise() {
		//do nothing if paused
		if (paused) { return; }
		
		//test on a copy if the active tetromino can be moved counter clockwise
		Tetromino activeCopy = active.Copy();
		activeCopy.rotateCounterClockWise();
		
		if (inPlayArea(activeCopy) && !isColliding(activeCopy)) {
			active.rotateCounterClockWise();
		}
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
	public long getUpdateInterval() {
		return updateInterval;
	}
	public void setUpdateInterval(long updateInterval) {
		this.updateInterval = updateInterval;
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

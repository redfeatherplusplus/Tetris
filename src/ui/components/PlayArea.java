package ui.components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Rectangle;

import ui.BlockPainter;
import api.Conversions;
import entities.TetrisGame;
import entities.blocks.Block;

public class PlayArea extends Component {
	
	private static final long serialVersionUID = 6479917092059257798L;
	
	private static final int OFFSET_X = 16;
	private static final int OFFSET_Y = 16;
	private static final int WIDTH = 240;
	private static final int HEIGHT = 480;
	private static final Rectangle FRAME = new Rectangle(
			OFFSET_X - 2, 
			OFFSET_Y - 2, 
			WIDTH + 4 - 1,
			HEIGHT + 4 - 1);
	
	private Block[][] playArea;
	private Rectangle[][] blockGrid;
	
	//constructor with TetrisGame argument
	public PlayArea(TetrisGame game) {
		playArea = game.getPlayArea();
		
		blockGrid = new Rectangle
				[TetrisGame.PLAY_AREA_WIDTH]
				[TetrisGame.PLAY_AREA_HEIGHT];
		
		for (int i = 0; i < blockGrid.length; i++) {
			for (int j = 0; j < blockGrid[i].length; j++) {
				blockGrid[i][j] = new Rectangle(
						OFFSET_X + i * BlockPainter.PIXEL_SIZE,
						OFFSET_Y + j * BlockPainter.PIXEL_SIZE,
						BlockPainter.PIXEL_SIZE - 1,
						BlockPainter.PIXEL_SIZE - 1);
			}
		}
	}
	
	@Override
	public void paint(Graphics graphics) {
		//draw frame
		Conversions.drawRect(graphics, FRAME, Color.BLACK);
		
		//draw block grid
		graphics.setColor(Color.WHITE);
		for (int i = 0; i < blockGrid.length; i++) {
			for (int j = 0; j < blockGrid[i].length; j++) {
				Conversions.drawRect(graphics, blockGrid[i][j]);
			}
		}
		
		//draw each block
		for (int i = 0; i < playArea.length; i++) {
			for (int j = 0; j < playArea[i].length; j++) {
				BlockPainter.paint(graphics, playArea[i][j], OFFSET_X, OFFSET_Y);
			}
		}
	}
}

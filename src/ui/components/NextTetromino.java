package ui.components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import ui.BlockPainter;
import ui.TetrominoPainter;
import api.Conversions;
import entities.TetrisGame;
import entities.blocks.Block;
import entities.blocks.Tetromino;
import entities.blocks.tetrominos.I_Mino;
import entities.blocks.tetrominos.O_Mino;

public class NextTetromino extends Component {
	
	private static final long serialVersionUID = 1L;
	
	public static final int OFFSET_X = 272;
	public static final int OFFSET_Y = 16;
	public static final int WIDTH = 128;
	public static final int HEIGHT = 80;
	public static final Rectangle FRAME = new Rectangle(
			OFFSET_X - 2, 
			OFFSET_Y - 2, 
			WIDTH + 4 - 1,
			HEIGHT + 4 - 1);
	
	private Tetromino next;
	
	//constructor with TetrisGame argument
	public NextTetromino(TetrisGame game) {
		next = game.getNext();
		
	}
	
	@Override
	public void paint(Graphics graphics) {
		Conversions.drawRect(graphics, FRAME, Color.BLACK);
	
		//compute the offset needed to center the tetromino
		Point offset = new Point();
		if (next instanceof I_Mino || next instanceof O_Mino) {
			//center as if four blocks wide and two tall
			offset.x = OFFSET_X + (WIDTH - 4 * BlockPainter.PIXEL_SIZE) / 2;
			offset.y = OFFSET_Y + (HEIGHT - 2 * BlockPainter.PIXEL_SIZE) / 2;
		}
		else {
			//center as if three blocks wide and two tall
			offset.x = OFFSET_X + (WIDTH - 3 * BlockPainter.PIXEL_SIZE) / 2;
			offset.y = OFFSET_Y + (HEIGHT - 2 * BlockPainter.PIXEL_SIZE) / 2;
		}
		
		//manually paint each block to ignore the tetromino's position
		for(Block block : next.getBlocks()) {
			BlockPainter.paint(graphics, block, 
					offset.x, 
					offset.y);
		}
	}
}
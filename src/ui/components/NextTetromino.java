package ui.components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Rectangle;

import api.Conversions;
import entities.TetrisGame;
import entities.blocks.Tetromino;

public class NextTetromino extends Component {
	
	private static final long serialVersionUID = 1L;
	
	private static final int OFFSET_X = 272;
	private static final int OFFSET_Y = 16;
	private static final int WIDTH = 128;
	private static final int HEIGHT = 80;
	private static final Rectangle FRAME = new Rectangle(
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
	}
}
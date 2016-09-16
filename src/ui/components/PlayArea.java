package ui.components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Rectangle;

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
	}
	
	@Override
	public void paint(Graphics graphics) {
		Conversions.drawRect(graphics, FRAME, Color.BLACK);
	}
}

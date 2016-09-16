package ui.components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;

import api.Conversions;
import entities.TetrisGame;
import entities.blocks.Block;

public class PlayArea extends Component {

	private static final long serialVersionUID = 6479917092059257798L;
	
	private Block[][] playArea;
	private Rectangle test;
	
	//constructor with TetrisGame argument
	public PlayArea(TetrisGame game) {
		playArea = game.getPlayArea();
		test = new Rectangle(0, 0, 30, 30);
	}
	
	@Override
	public void paint(Graphics g) {
		Dimension d = getParent().getSize();
		g.setColor(Color.RED);
//		g.drawRect(
//				Conversions.toDeviceX(test.x), 
//				Conversions.toDeviceY(test.y), 
//				(int)((test.width - 1) / Conversions.pixelSize),
//				(int)((test.height - 1) / Conversions.pixelSize));
		g.drawRect(
				Conversions.toDeviceX(0), 
				Conversions.toDeviceY(0), 
				(int)((416 - 1) / Conversions.pixelSize),
				(int)((512 - 1) / Conversions.pixelSize));
	}
}

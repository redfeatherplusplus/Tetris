package ui.components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;

import entities.TetrisGame;
import entities.blocks.Tetromino;

public class NextTetromino extends Component {
	
	private static final long serialVersionUID = 1L;
	
	private Tetromino next;
	
	//constructor with TetrisGame argument
	public NextTetromino(TetrisGame game) {
		next = game.getNext();
	}
	
	@Override
	public void paint(Graphics g) {
		Dimension d = getParent().getSize();
		System.out.println("Size:" + d.width + ", " + d.height);
		g.setColor(Color.BLUE);
		g.drawRect(2, 2, d.width - 5, d.height - 5);
	}
}
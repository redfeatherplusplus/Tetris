package ui.components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;

import entities.TetrisGame;
import entities.blocks.Block;

public class PlayArea extends Component {

	private static final long serialVersionUID = 6479917092059257798L;
	
	private Block[][] playArea;
	
	//constructor with TetrisGame argument
	public PlayArea(TetrisGame game) {
		playArea = game.getPlayArea();
	}
	
	@Override
	public void paint(Graphics g) {
		Dimension d = getParent().getSize();
		g.setColor(Color.RED);
		g.drawRect(0, 0, d.width - 1, d.height - 1);
	}
}

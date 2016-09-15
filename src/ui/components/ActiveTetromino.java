package ui.components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;

import entities.TetrisGame;
import entities.blocks.Tetromino;

public class ActiveTetromino extends Component {
	
	private static final long serialVersionUID = -4723951479040468780L;
	
	private Tetromino active;
	
	//constructor with TetrisGame argument
	public ActiveTetromino(TetrisGame game) {
		active = game.getActive();
	}
	
	@Override
	public void paint(Graphics g) {
		Dimension d = getParent().getSize();
		System.out.println("Size:" + d.width + ", " + d.height);
		g.setColor(Color.GREEN);
		g.drawRect(1, 1, d.width - 3, d.height - 3);
	}
}
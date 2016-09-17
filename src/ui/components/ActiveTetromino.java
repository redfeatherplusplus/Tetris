package ui.components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

import ui.TetrominoPainter;
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
	public void paint(Graphics graphics) {
		TetrominoPainter.paint(graphics, active, 
				PlayArea.OFFSET_X, 
				PlayArea.OFFSET_Y);
	}
}
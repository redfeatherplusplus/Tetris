package ui.components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Rectangle;

import entities.TetrisGame;
import api.Conversions;

public class ScoreIndicator extends Component {
	
	private static final long serialVersionUID = -7888415274711525286L;

	public static final int OFFSET_X = 272;
	public static final int OFFSET_Y = 112;
	public static final int WIDTH = 128;
	public static final int HEIGHT = 192;
	public static final Rectangle FRAME = new Rectangle(
			OFFSET_X - 2, 
			OFFSET_Y - 2, 
			WIDTH + 4 - 1,
			HEIGHT + 4 - 1);
	
	private TetrisGame game;
	
	//constructor with width and height argument
	public ScoreIndicator(TetrisGame game) {
		this.game = game;
	}
	
	@Override
	public void paint(Graphics graphics) {
		Conversions.drawRect(graphics, FRAME, Color.BLACK);
	}
}

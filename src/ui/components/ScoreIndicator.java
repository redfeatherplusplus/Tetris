package ui.components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

import entities.TetrisGame;
import api.Conversions;

public class ScoreIndicator extends Component {
	
	private static final long serialVersionUID = -7888415274711525286L;

	public static final int WIDTH = 128;
	public static final int HEIGHT = 192;
	public static final int OFFSET_X = PlayArea.OFFSET_X + PlayArea.WIDTH + 16;
	public static final int OFFSET_Y = NextTetromino.OFFSET_Y + NextTetromino.HEIGHT + 16;
	public static final Rectangle FRAME = new Rectangle(
			OFFSET_X - 2, 
			OFFSET_Y - 2, 
			WIDTH + 4 - 1,
			HEIGHT + 4 - 1);
	
	private TetrisGame game;
	private Font scoreFont;
	
	//constructor with width and height argument
	public ScoreIndicator(TetrisGame game) {
		this.game = game;
		
		scoreFont = new Font("Arial", Font.BOLD, 16);
	}
	
	@Override
	public void paint(Graphics graphics) {
		Conversions.drawRect(graphics, FRAME, Color.BLACK);

		graphics.setColor(Color.BLACK);
		graphics.setFont(Conversions.toDevice(scoreFont));
		
		//note: the offsets below are just values I felt looked nice
		
		Conversions.drawString(graphics, "Level:", OFFSET_X + 20, OFFSET_Y + 54);
		Conversions.drawString(graphics, "Lines:", OFFSET_X + 20, OFFSET_Y + 102);
		Conversions.drawString(graphics, "Score:", OFFSET_X + 20, OFFSET_Y + 150);
		
		Conversions.drawString(graphics, 
				String.valueOf(game.getLevel()), OFFSET_X + 100, OFFSET_Y + 54);
		Conversions.drawString(graphics, 
				String.valueOf(game.getLines()), OFFSET_X + 100, OFFSET_Y + 102);
		Conversions.drawString(graphics, 
				String.valueOf(game.getScore()), OFFSET_X + 100, OFFSET_Y + 150);
	}
}

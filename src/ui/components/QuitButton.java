package ui.components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Rectangle;

import api.Conversions;

public class QuitButton extends Component {
	
	private static final long serialVersionUID = 3692741696342705655L;
	
	private static final int OFFSET_X = 272;
	private static final int OFFSET_Y = 472;
	private static final int WIDTH = 104;
	private static final int HEIGHT = 24;
	private static final Rectangle FRAME = new Rectangle(
			OFFSET_X - 2, 
			OFFSET_Y - 2, 
			WIDTH + 4 - 1,
			HEIGHT + 4 - 1);
	
	public QuitButton() { 
		super();
	}
	
	@Override
	public void paint(Graphics graphics) {
		Conversions.drawRect(graphics, FRAME, Color.BLACK);
	}
}

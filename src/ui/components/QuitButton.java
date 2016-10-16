package ui.components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import api.Conversions;

public class QuitButton extends Component {
	
	private static final long serialVersionUID = 3692741696342705655L;

	public static final int WIDTH = 104;
	public static final int HEIGHT = 24;
	public static final int OFFSET_X = PlayArea.OFFSET_X + PlayArea.WIDTH + 16;
	public static final int OFFSET_Y = PlayArea.OFFSET_Y + PlayArea.HEIGHT - HEIGHT;
	public static final Rectangle FRAME = new Rectangle(
			OFFSET_X - 2, 
			OFFSET_Y - 2, 
			WIDTH + 4 - 1,
			HEIGHT + 4 - 1);
	
	private Font quitButtonFont;
	private boolean hovered;
	
	public QuitButton() { 
		quitButtonFont = new Font("Arial", Font.BOLD, 16);
		hovered = false;
	}
	
	@Override
	public void paint(Graphics graphics) {
		Conversions.drawRect(graphics, FRAME, Color.BLACK);

		graphics.setColor(Color.BLACK);
		graphics.setFont(Conversions.toDevice(quitButtonFont));
		
		//note: the offset below is just a value I felt looked nice
		
		Conversions.drawString(graphics, "QUIT", OFFSET_X + 32, OFFSET_Y + 18);
	}
	
	//getters and setters
	public void setHovered(boolean hovered) {
		this.hovered = hovered;
		
		//change font style if hovered
		if (hovered) {
			quitButtonFont = new Font("Arial", Font.BOLD + Font.ITALIC, 16);
		}
		else {
			quitButtonFont = new Font("Arial", Font.BOLD, 16);
		}
	}
	public boolean isHovered() { return hovered; }
}

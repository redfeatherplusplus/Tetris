package ui.components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;

public class PlayArea extends Component {
	
	@Override
	public void paint(Graphics g) {
		Dimension d = getParent().getSize();
		System.out.println("Size:" + d.width + ", " + d.height);
		g.setColor(Color.RED);
		g.drawRect(0, 0, d.width - 1, d.height - 1);
	}
}

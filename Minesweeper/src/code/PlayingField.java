package code;

import java.awt.Dimension;

import javax.swing.JPanel;
import java.awt.Color;

/*
 * the panel representing the playing field
 */
public class PlayingField extends JPanel {
	
	private final Dimension START_SIZE;
	
	private Dimension size;
	
	private int posX, posY;
	
	private int zoom = 1;

	/**
	 * Create the panel.
	 */
	public PlayingField(Dimension s) {
		START_SIZE = new Dimension(s.height / 4 * 3, s.height / 4 * 3);
		
		size = START_SIZE;
		
		posX = 0;
		posY = 0;
	
		initialize();
	}

	/**
	 * initializes some stuff of the panel
	 */
	private void initialize() {
		setBounds(0, 0, size.width, size.height);
	
		setBackground(Color.PINK);
	}
	
	/*
	 * zoom of the playing field
	 * 
	 * -1 -> zoom out ; 1 -> zoom in
	 */
	public void zoom(int a) {
		posX -= a * 25;
		posY -= a * 25;
		
		size.width += a * 50;
		size.height += a * 50;
		
		setBounds(posX, posY, size.width, size.height);
	}
}

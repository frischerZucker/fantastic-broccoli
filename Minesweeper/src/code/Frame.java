package code;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

/*
the main frame
*/

public class Frame extends JFrame {
	private final Dimension SCREEN_SIZE;
	
	private final String TITLE = "minesweeper";
	
	private Field field;
	
	private IO io;

	public Frame() {
		SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
		
		initFrame();
		
		field = new Field(10, 10, 20);
		
		io = new IO();
	}
	
	private void initFrame() {
		setSize(SCREEN_SIZE.width / 2, SCREEN_SIZE.height / 2);
		
		setLocationRelativeTo(null);
		
		setTitle(TITLE);
		
		setVisible(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		Frame f = new Frame();
	}

}

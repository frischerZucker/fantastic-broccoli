package code;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

/*
 * the main frame
 */

public class Frame extends JFrame{
	private final Dimension SCREEN_SIZE, FRAME_SIZE;

	private final String TITLE = "minesweeper";

	private Panel panel;

	public Frame() {
		SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();

		FRAME_SIZE = new Dimension(SCREEN_SIZE);

		panel = new Panel(FRAME_SIZE);

		initFrame();
	}

	/*
	 * initializes some variables of the frame
	 */
	private void initFrame() {
		setLayout(null);

		setPreferredSize(FRAME_SIZE);

		add(panel);

		pack();

		setLocationRelativeTo(null);

		setTitle(TITLE);

		setVisible(true);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		Frame f = new Frame();
	}
}

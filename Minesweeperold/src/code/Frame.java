package code;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

/*
 * the main frame
 */

public class Frame extends JFrame implements Runnable {
	private final Dimension SCREEN_SIZE, FRAME_SIZE;

	private final String TITLE = "minesweeper";

	private Panel panel;

	private Thread thread;
	
	private Boolean isRunning = false;
	
	public Frame() {
		SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();

		FRAME_SIZE = new Dimension(SCREEN_SIZE);

		panel = new Panel(FRAME_SIZE);
		
		thread = new Thread(this);

		initFrame();
		
		start();
	}
	
	/*
	 * starts the thread
	 */
	private void start() {
		isRunning = true;
		
		thread.start();
	}
	
	/*
	 * stops the thread
	 */
	private void stop() {
		isRunning = false;
		
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
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

		setVisible(false);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		Frame f = new Frame();
	}

	/*
	 * called when the thread starts
	 */
	@Override
	public void run() {
		/*
		 * main-loop
		 */
		while(isRunning) {
			IO.getInput(panel.field);
		}
	}
}

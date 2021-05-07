package code;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import javax.swing.JToggleButton;
import javax.swing.JSlider;
import javax.swing.JTextField;

/*
 * the main frame of the game
 */
public class Frame implements Runnable, ItemListener {

	private JFrame mainFrame;

	private final Dimension SCREEN_SIZE, FRAME_SIZE;

	private PlayingField playingField;

	private JPanel bgPanel;

	private Boolean isRunning = false, isExplodeMode = true;

	private Thread thread;

	private JToggleButton selectMarkModeBtn, selectExplodeModeBtn;
	private JTextField heightInput;
	private JTextField widthInput;
	private JTextField mineInput;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Frame window = new Frame();
		window.mainFrame.setVisible(true);
	}

	/**
	 * Create the application.
	 */
	public Frame() {
		SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();

		FRAME_SIZE = new Dimension(SCREEN_SIZE.width / 6 * 5, SCREEN_SIZE.height / 6 * 5);

		playingField = new PlayingField(FRAME_SIZE);

		initialize();
	}

	/*
	 * update method; called once per main loop run
	 */
	private void tick() {

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		/**
		 * the frame
		 */
		mainFrame = new JFrame();
		mainFrame.setTitle("minesweeper");
		mainFrame.setBounds(0, 0, FRAME_SIZE.width, FRAME_SIZE.height);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		/**
		 * background for better location handling
		 */
		bgPanel = new JPanel();
		bgPanel.setBackground(Color.ORANGE);
		mainFrame.getContentPane().add(bgPanel, BorderLayout.CENTER);
		bgPanel.setLayout(null);

		/**
		 * part of the frame where the playing field will be visible
		 */
		JPanel playingFieldUnderground = new JPanel();
		playingFieldUnderground.setBounds(FRAME_SIZE.height / 11, FRAME_SIZE.height / 11, FRAME_SIZE.height / 4 * 3,
				FRAME_SIZE.height / 4 * 3);
		playingFieldUnderground.setBackground(Color.WHITE);
		bgPanel.add(playingFieldUnderground);
		playingFieldUnderground.setLayout(null);

		/**
		 * adds the actual playing field
		 */
		playingFieldUnderground.add(playingField);

		addButtons();

		thread = new Thread(this);

		start();
	}

	/*
	 * starts the thread
	 */
	public void start() {
		isRunning = true;
		thread.start();
	}

	/*
	 * stops the thread
	 */
	public void stop() {
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/*
	 * used to add all the buttons to everything
	 */
	private void addButtons() {
		/**
		 * button used to zoom out of the playing field
		 */
		JButton zoomOutBtn = new JButton("-");
		zoomOutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playingField.zoom(-1);
			}
		});
		zoomOutBtn.setBounds(FRAME_SIZE.height / 11, FRAME_SIZE.height / 11 + FRAME_SIZE.height / 4 * 3,
				FRAME_SIZE.height / 20, FRAME_SIZE.height / 20);
		bgPanel.add(zoomOutBtn);

		/**
		 * button used to zoom in to the playing field
		 */
		JButton zoomInBtn = new JButton("+");
		zoomInBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playingField.zoom(1);
			}
		});
		zoomInBtn.setBounds(FRAME_SIZE.height / 11 + FRAME_SIZE.height / 20,
				FRAME_SIZE.height / 11 + FRAME_SIZE.height / 4 * 3, FRAME_SIZE.height / 20, FRAME_SIZE.height / 20);
		bgPanel.add(zoomInBtn);

		selectExplodeModeBtn = new JToggleButton("X");
		selectExplodeModeBtn.setBounds(FRAME_SIZE.height / 11 + FRAME_SIZE.height / 4 * 3 - FRAME_SIZE.height / 10,
				FRAME_SIZE.height / 11 + FRAME_SIZE.height / 4 * 3, FRAME_SIZE.height / 20, FRAME_SIZE.height / 20);
		selectExplodeModeBtn.setSelected(true);
		selectExplodeModeBtn.addItemListener(this);
		bgPanel.add(selectExplodeModeBtn);

		selectMarkModeBtn = new JToggleButton("O");
		selectMarkModeBtn.setBounds(FRAME_SIZE.height / 11 + FRAME_SIZE.height / 4 * 3 - FRAME_SIZE.height / 20,
				FRAME_SIZE.height / 11 + FRAME_SIZE.height / 4 * 3, FRAME_SIZE.height / 20, FRAME_SIZE.height / 20);
		selectMarkModeBtn.addItemListener(this);
		bgPanel.add(selectMarkModeBtn);

		widthInput = new JTextField();
		widthInput.setToolTipText("input width");
		widthInput.setBounds(FRAME_SIZE.height / 11 * 3 + FRAME_SIZE.height / 4 * 3, FRAME_SIZE.height / 11,
				FRAME_SIZE.height / 9, FRAME_SIZE.height / 40);
		bgPanel.add(widthInput);
		widthInput.setColumns(10);

		heightInput = new JTextField();
		heightInput.setToolTipText("input height");
		heightInput.setBounds(FRAME_SIZE.height / 11 * 3 + FRAME_SIZE.height / 4 * 3,
				FRAME_SIZE.height / 11 + FRAME_SIZE.height / 20, FRAME_SIZE.height / 9, FRAME_SIZE.height / 40);
		bgPanel.add(heightInput);
		heightInput.setColumns(10);

		mineInput = new JTextField();
		mineInput.setToolTipText("input number of mines");
		mineInput.setBounds(FRAME_SIZE.height / 11 * 3 + FRAME_SIZE.height / 4 * 3,
				FRAME_SIZE.height / 11 + FRAME_SIZE.height / 10, FRAME_SIZE.height / 9, FRAME_SIZE.height / 40);
		bgPanel.add(mineInput);
		mineInput.setColumns(10);

		JButton createFieldBtn = new JButton("create field");
		createFieldBtn.setBounds(FRAME_SIZE.height / 11 * 3 + FRAME_SIZE.height / 4 * 3,
				FRAME_SIZE.height / 11 + FRAME_SIZE.height / 7, FRAME_SIZE.height / 9, FRAME_SIZE.height / 40);
		bgPanel.add(createFieldBtn);
	}

	@Override
	public void run() {
		while (isRunning) {
			tick();
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getItem() == selectExplodeModeBtn && selectExplodeModeBtn.isSelected()) {
			isExplodeMode = true;
			selectMarkModeBtn.setSelected(false);
		} else if (e.getItem() == selectMarkModeBtn && selectMarkModeBtn.isSelected()) {
			isExplodeMode = false;
			selectExplodeModeBtn.setSelected(false);
		}
	}
}

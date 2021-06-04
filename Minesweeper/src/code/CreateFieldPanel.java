package code;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class CreateFieldPanel extends JPanel implements ActionListener {

	private final Dimension FRAME_SIZE, SIZE;

	private JTextField widthInput, heightInput, mineInput;

	private JButton createFieldBtn;

	/**
	 * Create the panel.
	 */
	public CreateFieldPanel(Dimension s) {
		setBackground(Color.ORANGE);
		FRAME_SIZE = s;

		SIZE = new Dimension(FRAME_SIZE.height / 9, FRAME_SIZE.height / 6);

		setSize(SIZE);

		setLocation(FRAME_SIZE.height / 11 * 3 + FRAME_SIZE.height / 4 * 3, FRAME_SIZE.height / 11);

		setBorder(null);

		addContent();
	}

	/*
	 * creates an array representing the playing field
	 * 
	 * -1 -> mine
	 */
	public int[][] createField() {
		int[][] field;
		
		/*
		 * gets input from the input textfields
		 */
		int width = Integer.parseInt(widthInput.getText());

		int height = Integer.parseInt(heightInput.getText());

		int mines = Integer.parseInt(mineInput.getText());

		/*
		 * creates field with given size
		 */
		field = new int[height][width];

		Random random = new Random(System.nanoTime());

		/*
		 * sets random position of the field to -1
		 */
		for (int a = 0; a < mines; a++) {
			int h = random.nextInt(height - 1);

			int w = random.nextInt(width - 1);

			/*
			 * "extra round" if position is already a mine
			 */
			if (field[h][w] != -1) {
				field[h][w] = -1;
			} else {
				a -= 1;
			}
		}

		/*
		 * calculates the mines around every field
		 * 
		 * positions: 1 2 3
		 * 
		 * 8 x 4
		 * 
		 * 7 6 5
		 */
		for (int h = 0; h < height; h++) {
			for (int w = 0; w < width; w++) {
				int c = 0;

				/*
				 * 1
				 */
				try {
					if (field[h - 1][w - 1] == -1) {
						c++;
					}
				} catch (Exception e) {
				}

				/*
				 * 2
				 */
				try {
					if (field[h - 1][w] == -1) {
						c++;
					}
				} catch (Exception e) {
				}

				/*
				 * 3
				 */
				try {
					if (field[h - 1][w + 1] == -1) {
						c++;
					}
				} catch (Exception e) {
				}

				/*
				 * 4
				 */
				try {
					if (field[h][w + 1] == -1) {
						c++;
					}
				} catch (Exception e) {
				}

				/*
				 * 5
				 */
				try {
					if (field[h + 1][w + 1] == -1) {
						c++;
					}
				} catch (Exception e) {
				}

				/*
				 * 6
				 */
				try {
					if (field[h + 1][w] == -1) {
						c++;
					}

				} catch (Exception e) {
				}

				/*
				 * 7
				 */
				try {
					if (field[h + 1][w - 1] == -1) {
						c++;
					}
				} catch (Exception e) {
				}

				/*
				 * 8
				 */
				try {
					if (field[h][w - 1] == -1) {
						c++;
					}
				} catch (Exception e) {
				}

				if (field[h][w] != -1) {
					field[h][w] = c;
				}
			}
		}

		for (int a = 0; a < height; a++) {
			for (int b = 0; b < width; b++) {
				System.out.print(field[a][b] + " ");
			}
			System.out.println();
		}
		System.out.println();

		return field;
	}

	private void addContent() {
		setLayout(null);
		widthInput = new JTextField();
		widthInput.setText("5");
		widthInput.setToolTipText("input width");
		widthInput.setBounds(0, SIZE.height / 7, SIZE.width, SIZE.height / 7);
		add(widthInput);
		widthInput.setColumns(10);

		heightInput = new JTextField();
		heightInput.setText("7");
		heightInput.setToolTipText("input height");
		heightInput.setBounds(0, SIZE.height / 7 * 3, SIZE.width, SIZE.height / 7);
		add(heightInput);
		heightInput.setColumns(10);

		mineInput = new JTextField();
		mineInput.setText("10");
		mineInput.setToolTipText("input number of mines");
		mineInput.setBounds(0, SIZE.height / 7 * 5, SIZE.width, SIZE.height / 7);
		add(mineInput);
		mineInput.setColumns(10);

		createFieldBtn = new JButton("create field");
		createFieldBtn.setBounds(0, SIZE.height / 7 * 6, SIZE.width, SIZE.height / 7);
		createFieldBtn.addActionListener(this);
		add(createFieldBtn);

		JLabel widthLabel = new JLabel("width:");
		widthLabel.setBounds(0, 0, 46, 14);
		add(widthLabel);

		JLabel heightLabel = new JLabel("height:");
		heightLabel.setBounds(0, SIZE.height / 7 * 2, 46, 14);
		add(heightLabel);

		JLabel mineLabel = new JLabel("mines:");
		mineLabel.setBounds(0, SIZE.height / 7 * 4, 46, 14);
		add(mineLabel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(createFieldBtn)) {
			PlayingField.playingField = createField();
		}
	}
}

package code;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

/*
 * small sector of the field
 */
public class FieldSector extends JPanel implements ActionListener{
	private final Dimension SIZE;

	public final String ID;

	public FieldSector(int size, int posX, int posY, Color c) {
		SIZE = new Dimension(size, size);
		
		ID = posX + " " + posY;
		
		initPanel(c);
	}

	/*
	 * initializes some variables of the panel of the fieldsector
	 */
	private void initPanel(Color c) {

		setPreferredSize(SIZE);

		setBackground(c);

		setVisible(true);
		
		/*
		 * adding a button to the pannel
		 * 
		 * just temporary
		 */
		JButton b = new JButton();
		b.setPreferredSize(SIZE);
		b.setBackground(new Color(1, 1, 1, 0));
		b.setRolloverEnabled(false);
		b.setOpaque(false);
		b.setVisible(true);
		b.setActionCommand("button");
		b.addActionListener(this);
		
		add(b);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() =="button") {
			Field.sectorClicked(ID);
		}
	}

}

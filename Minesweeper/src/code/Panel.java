package code;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

/*
 * panel that is used for drawing all the stuff
 */
public class Panel extends JPanel {
	private final Dimension PANEL_SIZE;

	private final int SECTOR_SIZE = 20;

	private Field field;
	
	private FieldSector[][] fieldsSectors;

	public Panel(Dimension size) {
		PANEL_SIZE = size;

		field = new Field(50, 50, 250);
		
		fieldsSectors = new FieldSector[field.FIELD_HEIGHT][field.FIELD_WIDTH];

		initPanel();
	}

	/*
	 * initializes some variables of the panel
	 */
	private void initPanel() {
		setSize(new Dimension(field.FIELD_WIDTH * SECTOR_SIZE, field.FIELD_HEIGHT * SECTOR_SIZE));

		setLayout(new GridLayout(field.FIELD_WIDTH, field.FIELD_HEIGHT));

		setBackground(Color.pink);

		setVisible(true);

		addFieldSectors();
	}

	/*
	 * adds the fieldsectors to the panel
	 */
	private void addFieldSectors() {
		/*
		 * adds fieldsectors to an array
		 * 
		 * a and b are just some variables to get the sectors alternately black and white
		 */
		int a = 0;
		
		for(int posH = 0; posH < field.FIELD_HEIGHT; posH++) {
			int b = 0;
			
			if(a == 0) {
				/*
				 * adds a black-white row
				 */
				for(int posW = 0; posW < field.FIELD_WIDTH; posW++){
					if(b == 0) {
						fieldsSectors[posH][posW] = new FieldSector(SECTOR_SIZE, posW, posH, Color.black);
						b = 1;
					} else {
						fieldsSectors[posH][posW] = new FieldSector(SECTOR_SIZE, posW, posH, Color.white);
						b = 0;
					}
				}
				a = 1;
			} else {
				/*
				 * adds a white-black row
				 */
				for(int posW = 0; posW < field.FIELD_WIDTH; posW++){
					if(b == 0) {
						fieldsSectors[posH][posW] = new FieldSector(SECTOR_SIZE, posW, posH, Color.white);
						b = 1;
					} else {
						fieldsSectors[posH][posW] = new FieldSector(SECTOR_SIZE, posW, posH, Color.black);
						b = 0;
					}
				}
				a = 0;
			}
			
		}
		
		/*
		 * adds every fieldsector from the array to the panel
		 * 
		 * fieldSector.ID is used as name
		 */
		for(int posH = 0; posH < field.FIELD_HEIGHT; posH++) {
			for(int posW = 0; posW < field.FIELD_WIDTH; posW++){
				add(fieldsSectors[posH][posW].ID ,fieldsSectors[posH][posW]);
			}
		}
	}
}

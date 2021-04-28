package code;

/*
all kinds off stuff for the matchfield
*/

public class Field {
	public final int FIELD_HEIGHT, FIELD_WIDTH, START_MINES;
	private final int MINE = -1;

	public int[][] field;

	public Field(int height, int width, int mines) {
		FIELD_HEIGHT = height;
		FIELD_WIDTH = width;
		START_MINES = mines;

		field = generateField();

		for (int a = 0; a < FIELD_HEIGHT; a++) {
			for (int b = 0; b < FIELD_WIDTH; b++) {
				System.out.print(field[a][b] + " ");
			}
			System.out.println();
		}

	}
	
	public static void sectorClicked(String sectorId) {
		System.out.println(sectorId);
	}

	/*
	 * generates an array that represents the field
	 */
	private int[][] generateField() {
		/*
		 * creates an array with the specified height and width
		 */
		int[][] f = new int[FIELD_HEIGHT][FIELD_WIDTH];

		/*
		 * places mines at random spots of the field
		 * 
		 * START_MINES: number of mines that will be placed
		 */
		for (int a = 0; a < START_MINES; a++) {
			int posH, posW;

			do {
				posH = (int) (Math.random() * FIELD_HEIGHT - 1);
				posW = (int) (Math.random() * FIELD_WIDTH - 1);
			} while (f[posH][posW] == MINE);

			f[posH][posW] = MINE;
		}

		/*
		 * calculates number of mines near to every position
		 */
		for (int posH = 0; posH < FIELD_HEIGHT; posH++) {
			for (int posW = 0; posW < FIELD_WIDTH; posW++) {
				if (f[posH][posW] != -1) {
					int counter = 0;

					/*
					 * field ids:
					 * 
					 * 1 2 3
					 * 
					 * 8 x 4
					 * 
					 * 7 6 5
					 */

					/*
					 * 1
					 */
					try {
						if (f[posH - 1][posW - 1] == -1) {
							counter++;
						}
					} catch (Exception e) {
					}

					/*
					 * 2
					 */
					try {
						if (f[posH - 1][posW] == -1) {
							counter++;
						}
					} catch (Exception e) {
					}

					/*
					 * 3
					 */
					try {
						if (f[posH - 1][posW + 1] == -1) {
							counter++;
						}
					} catch (Exception e) {
					}

					/*
					 * 4
					 */
					try {
						if (f[posH][posW + 1] == -1) {
							counter++;
						}
					} catch (Exception e) {
					}

					/*
					 * 5
					 */
					try {
						if (f[posH + 1][posW + 1] == -1) {
							counter++;
						}
					} catch (Exception e) {
					}

					/*
					 * 6
					 */
					try {
						if (f[posH + 1][posW] == -1) {
							counter++;
						}
					} catch (Exception e) {
					}

					/*
					 * 7
					 */
					try {
						if (f[posH + 1][posW - 1] == -1) {
							counter++;
						}
					} catch (Exception e) {
					}

					/*
					 * 8
					 */
					try {
						if (f[posH][posW - 1] == -1) {
							counter++;
						}
					} catch (Exception e) {
					}

					f[posH][posW] = counter;
				}
			}
		}

		return f;
	}
}

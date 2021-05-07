package code;

import java.util.Scanner;

public class IO {
	public IO() {
		
	}
	
	/*
	 * outputs the given field as text in the console
	 * 
	 * needs an int-array as input
	 */
	public static void printIntField(int[][] field, int height, int width) {
		for (int a = 0; a < height; a++) {
			for (int b = 0; b < width; b++) {
				System.out.print(field[a][b] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	/*
	 * outputs the given field as text in the console
	 * 
	 * needs a string-array as input
	 */
	public static void printStringField(String[][] field, int height, int width) {
		for (int a = 0; a < height; a++) {
			for (int b = 0; b < width; b++) {
				System.out.print(field[a][b] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public static void getInput(Field field) {
		Scanner scanner = new Scanner(System.in);
		
		String input = scanner.nextLine();
		
		String[] splitInput = input.split(" ");
		
		for(int a = 0; a < splitInput.length; a++) {
			System.out.println(splitInput[a]);
		}
		
		if(splitInput[0].equals("detonate")) {
			int posH = Integer.parseInt(splitInput[1]) - 1;
			
			int posW = Integer.parseInt(splitInput[2]) - 1;
			
			field.detonate(posH, posW);
		}
	}
}

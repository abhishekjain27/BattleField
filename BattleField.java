import java.util.Scanner;

import java.util.Random;

public class BattleField {
	private int randomRow, randomCol;
	public static char[][] arr;

	public BattleField(int limit) {
		for (int firstRow = 0; firstRow < limit; firstRow++) {
			arr[0][firstRow] = 0;
			arr[firstRow][0] = 0;
		}
		for (int rowIt = 1; rowIt < limit; rowIt++) {
			for (int colIt = 1; colIt < limit; colIt++) {
				arr[rowIt][colIt] = '*';
			}
		}
	}

	public static void main(String[] args) {
		int squareLimit = Integer.parseInt(args[0]) + 1;
		arr = new char[squareLimit][squareLimit];
		BattleField tank = new BattleField(squareLimit);
		tank.generateRandom(squareLimit);
		System.out.println("You've got 3 chances to guess:");
		for (int chances = 1; chances <= 3; chances++) {
			tank.getUserInput(squareLimit);
		}
		System.out.println("The submarine was at :" + tank.randomRow + " " + tank.randomCol);
	}

	public void generateRandom(int limit) {
		randomRow = new Random().nextInt(limit);
		randomCol = new Random().nextInt(limit);
	}

	public void printBoard(int row, int col) {
		if (row != randomRow || col != randomCol) {
			arr[row][col] = '$';
		}
		for (int rowIterator = 1; rowIterator <= 5; rowIterator++) {
			for (int colIterator = 1; colIterator <= 5; colIterator++) {
				System.out.print(" " + arr[rowIterator][colIterator]);
			}
			System.out.println();
		}
	}

	public void getUserInput(int limit) {
		Scanner data = new Scanner(System.in);
		System.out.println();
		System.out.println("Enter the possible row value for submarine :");
		int row = data.nextInt();
		System.out.println("Enter the possible column value for submarine:");
		int col = data.nextInt();
		int value = check(row, col, limit);
		if (value == 0) {
			getUserInput(limit);
		} else {
			printBoard(row, col);
			attackOrNot(row, col);
		}
	}

	public void attackOrNot(int row, int col) {
		int count = 1;
		if (row == randomRow && col == randomCol) {
			System.out.println("You caught the submarine . !!");
			System.exit(0);
		} else if (count == 3) {
			System.out.println("Better luck next time.!!");
		} else {
			++count;
			System.out.println("Try Again.!!");
		}
	}

	public int check(int row, int col, int limit) {
		if (row >= limit || col >= limit) {
			System.out.println("Invalid Range");
			return 0;
		} else if (arr[row][col] == '$') {
			System.out.println("You have aready guessed that value !!");
			return 0;
		}
		return 1;
	}
}

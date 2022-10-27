package pckg;

// this module contains the main method for deciding whether or not the game should continue.
// it also has helper methods of the game principles. 
public class GamePrinciples {

	// if the board has a winner or the board is full, the game ends otherwise the
	// game continues.
	public static boolean gameEnd(int[][] array, int win, int player) {
		if (winningBoard(array, win, player) == 1 || winningBoard(array, win, player) == -1) {
			return true;
		}
		if (fullBoard(array)) {
			return true;
		}
		return false;
	}

	// given a board, if full, returns true.
	// Take note that A draw state happens when the board is full and there are no
	// winners.
	public static boolean fullBoard(int[][] array) {
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				if (array[i][j] == 0) {
					return false;
				}
			}
		}
		return true;
	}

	// given an array checks whether all elements in the array are equal or not.
	public static boolean allSame(int[] array) {
		for (int i = 0; i < array.length - 1; i++) {
			if (array[i] != array[i + 1]) {
				return false;
			}
		}
		return true;
	}

	// the method checks if an array is a winning array.
	// An array is a winning array if there are "win" number of consecutive cells
	// holding the same value. if the array is winning and the player has won
	// returns 1 if computer has won returns -1.
	// if win = array.length this function would be the same as allSame. in other
	// words, it would not be needed.
	public static int checkWinArray(int[] array, int win, int player) {
		for (int i = 0; i < array.length - win + 1; i++) {
			int[] arr = new int[win];
			for (int j = i; j < i + win; j++) {
				arr[j - i] = array[j];
			}
			if (allSame(arr) && arr[0] == player) {
				return 1;
			}
			if (allSame(arr) && arr[0] == nextPlayer(player)) {
				return -1;
			}
		}
		return 0;
	}

	// now we check the whole board (arrays of arrays) to see whether there exists a
	// winner.
	public static int winningBoard(int[][] array, int win, int player) {
		// first check the rows to see the winning array. Take note that a board can
		// only have one winning row/column/diag/antidiag
		for (int i = 0; i < array.length; i++) {
			int[] arr = array[i];
			if (checkWinArray(arr, win, player) == 1) {
				return 1;
			}
			if (checkWinArray(arr, win, player) == -1) {
				return -1;
			}
		}
		// checking the columns which is exactly as same as checking the rows of the
		// transpose given that we have access to the rows easier.
		array = transpose(array);
		for (int i = 0; i < array.length; i++) {
			int[] arr = array[i];
			if (checkWinArray(arr, win, player) == 1) {
				return 1;
			}
			if (checkWinArray(arr, win, player) == -1) {
				return -1;
			}
		}
		// checking the diagonal.
		int[] diagArr = new int[array.length];
		for (int i = 0; i < array.length; i++) {
			diagArr[i] = array[i][i];
		}
		if (checkWinArray(diagArr, win, player) == 1) {
			return 1;
		}
		if (checkWinArray(diagArr, win, player) == -1) {
			return -1;
		}
		// checking the anti diagonal.
		int[] diagArrOpp = new int[array.length];
		for (int i = 0; i < array.length; i++) {
			diagArrOpp[i] = array[i][array.length - i - 1];
		}
		if (checkWinArray(diagArrOpp, win, player) == 1) {
			return 1;
		}
		if (checkWinArray(diagArrOpp, win, player) == -1) {
			return -1;
		}
		return 0;
	}

	// given a board transposes the board.
	public static int[][] transpose(int[][] array) {
		int[][] newArray = new int[array[0].length][array.length];
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[0].length; j++) {
				newArray[j][i] = array[i][j];
			}
		}
		return newArray;
	}

	// given a player gives the next player.
	// Tic-Tac-Toe is a game of two with interval turns.
	public static int nextPlayer(int player) {
		if (player == 1) {
			return 2;
		}
		return 1;
	}
}

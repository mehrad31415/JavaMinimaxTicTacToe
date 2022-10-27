package pckg;

// this module is the main game play.
import java.util.Scanner;

public class Main {
	public static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.println("welcome to the game of tic-tac-toe!");
		System.out.println(
				"do you want to start as the first player or second? (for first press 1 and for second press 2: ");
		int player = Integer.valueOf(Main.scanner.nextLine());
		System.out.println(
				"how many rows & columns do you want in the game? (the number of rows and columns are always equal) ");
		final int DIM = Integer.valueOf(Main.scanner.nextLine());
		System.out.println("how many in a row/column/diagnol will win a game? ");
		final int WIN = Integer.valueOf(Main.scanner.nextLine());
		int[][] array = new int[DIM][DIM];
		for (int i = 0; i < DIM; i++) {
			for (int j = 0; j < DIM; j++) {
				array[i][j] = 0;
			}
		}
		Display.prettyPrint(array);
		System.out.println("");
		// by default the first player starts the game (obviously!)
		int turn = 1;
		// main game loop.
		while (!GamePrinciples.gameEnd(array, WIN, player)) {
			if (turn == player) {
				System.out.println("its your turn!");
				System.out.println("which location do you want to play? ");
				System.out.println("row: ");
				int row = Integer.valueOf(Main.scanner.nextLine());
				System.out.println("col: ");
				int col = Integer.valueOf(Main.scanner.nextLine());
				// this loop checks if the cell is empty. No overwriting is allowed in the game.
				while (array[row][col] != 0) {
					System.out.println("you cannot claim an already full place.");
					System.out.println("which location do you want to play? ");
					System.out.println("row: ");
					row = Integer.valueOf(Main.scanner.nextLine());
					System.out.println("col: ");
					col = Integer.valueOf(Main.scanner.nextLine());
				}
				array[row][col] = player;
				Display.prettyPrint(array);
				turn = GamePrinciples.nextPlayer(player);
				System.out.println("");
			} else {
				System.out.println("its the computers turn!");
				array = Minimax.minimax(array, WIN, player, turn);
				Display.prettyPrint(array);
				// the below lines are the algorithm for a computer playing with a strategy that
				// does random moves
				// int row = (int)(Math.random()*DIM);
				// int col = (int)(Math.random()*DIM);
				// while (array[row][col]!=0) {
				// row = (int)(Math.random()*DIM);
				// col = (int)(Math.random()*DIM);
				// }
				// array[row][col] = GamePrinciples.nextPlayer(player);
				// Display.prettyPrint (array);
				turn = player;
				System.out.println("");
			}
		}
		// the game is a draw or has a winner!
		if (GamePrinciples.winningBoard(array, WIN, player) == 1) {
			System.out.println("player " + player + " wins");
		} else if (GamePrinciples.winningBoard(array, WIN, player) == -1) {
			System.out.println("player " + GamePrinciples.nextPlayer(player) + " wins");
		} else {
			System.out.println("The game is a draw!");
		}
	}
}

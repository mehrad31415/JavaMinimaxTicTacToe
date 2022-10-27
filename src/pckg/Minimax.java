package pckg;

// the Minimax algorithm.
public class Minimax {

	// the main minimax algorithm. given a state returns a new state which is the
	// optimal state for winning or atleast not losing. This move is done by the
	// computer.
	public static int[][] minimax(int[][] state, int win, int player, int turn) {
		// computer turn
		int computer = GamePrinciples.nextPlayer(player);
		// defining a very large number. Take note that in GamePrinciples we defined
		// that if player wins we return a 1 and if computer wins we return -1 so the
		// computer is searching for a new state with the lowest possible score
		// (minimum). In other words the computer is the minimizer.
		int score = 10000;
		int[][] temp = new int[state.length][state[0].length];
		for (int i = 0; i < state.length; i++) {
			for (int j = 0; j < state[0].length; j++) {
				if (state[i][j] == 0) {
					state[i][j] = computer;
					int s = score(state, win, player, GamePrinciples.nextPlayer(turn));
					// if the computer can do a game winning move then it will do it (high
					// priority).
					if (GamePrinciples.winningBoard(state, win, player) == -1) {
						return state;
					}
					// otherwise it will look for the board with the lowest score among its
					// children.
					// each children is a board resulting from the state with ONLY one move
					// the number of children of board is equal to the number of empty spaces of the
					// board.
					if (s <= score) {
						score = s;
						// do not write equal to state because array is a referencing variable!
						// meaning that with the change of state temp will change as well.
						// but in the below case they are independent.
						temp = myCopy(state);
					}
					state[i][j] = 0;
				}
			}
		}
		return temp;
	}

	// copying an array independently.
	public static int[][] myCopy(int[][] state) {
		int[][] temp = new int[state.length][state[0].length];
		for (int k = 0; k < state.length; k++) {
			for (int m = 0; m < state[0].length; m++) {
				temp[k][m] = state[k][m];
			}
		}
		return temp;
	}

	// gives a score to a board.
	// uncomment the print comments to see how the method runs step by step.
	public static int score(int[][] state, int win, int player, int turn) {
		int score = 0;
		int computer = GamePrinciples.nextPlayer(player);
		// if the board is in winning state and player has won the board get score 1.
		if (GamePrinciples.winningBoard(state, win, player) == 1) {
			// System.out.println(11);
			return 1;
		}
		// if the board is in winning state and computer has won the board get score -1.
		if (GamePrinciples.winningBoard(state, win, player) == -1) {
			// System.out.println(111);
			return -1;
		}
		// if the board has no winner but is full then it means it is a draw and the
		// board gets score 0.
		if (GamePrinciples.fullBoard(state)) {
			// System.out.println(1111);
			return 0;
		}
		// do not use a for-each loop here. A for each loop does not allow you to change
		// the element in place.
		// the reason in short is that a for-each loop creates a copy of the cell
		// instead of giving you access to the cell itself
		// the score of each board is the sum of the scores of its children.
		for (int i = 0; i < state.length; i++) {
			for (int j = 0; j < state.length; j++) {
				if (state[i][j] == 0) {
					if (turn == computer) {
						state[i][j] = computer;
						score += score(state, win, player, GamePrinciples.nextPlayer(turn));
						// Display.prettyPrint(state);
						state[i][j] = 0;
						// System.out.println(score);
					} else {
						state[i][j] = player;
						score += score(state, win, player, GamePrinciples.nextPlayer(turn));
						// Display.prettyPrint(state);
						state[i][j] = 0;
						// System.out.println(score);
					}
				}
			}
		}
		// System.out.println(11111);
		return score;

	}

}

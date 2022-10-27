package pckg;

// this module displays the board in a pretty way.
public class Display {

	// pretty prints the board.
	public static void prettyPrint(int[][] array) {
		// gets the maximum width of a cell in the board.
		final int WIDTH = maxWidth(array);
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				System.out.print(sameWidth(icons(array[i][j]), WIDTH));
				if (j != array[i].length - 1) {
					System.out.print("|");
				}
			}
			System.out.println();
			if (i != array.length - 1) {
				dash(WIDTH, array[i].length);
			}
		}
	}

	// this is the function that gets the maximum width of the icons.
	// We will use this number to further adjust all the cells to be the same width.
	// in this game it does not matter because all icons are of same length (X) &
	// (O).
	// but if one icon was "OOO" and the other was "x" then this function would be
	// handy.
	public static int maxWidth(int[][] array) {
		int width = 0;
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				if (icons(array[i][j]).length() > width) {
					width = icons(array[i][j]).length();
				}
			}
		}
		return width;
	}

	// given a string and a width (in this case maximum width) returns a string with
	// the desired width. this is done by adding spaces to the right of the string
	// until it reaches the desired width.
	// it is assumed that the width given is larger than the length of the string.
	public static String sameWidth(String s, int width) {
		int difference = width - s.length();
		for (int i = 0; i < difference; i++) {
			s += " ";
		}
		return s;
	}

	// prints the dividers between the rows.
	public static void dash(int width, int iteration) {
		for (int j = 0; j < iteration; j++) {
			for (int i = 0; i < width; i++) {
				System.out.print("-");
			}
			if (j != iteration - 1) {
				System.out.print("|");
			}
		}
		System.out.println();
	}

	// traditionally player one has the X icon and the second player has O icon.
	public static String icons(int number) {
		if (number == 1) {
			return " X ";
		} else if (number == 2) {
			return " O ";
		}
		return "   ";
	}
}

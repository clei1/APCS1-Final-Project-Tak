import cs1.Keyboard;

public class Woo {

    public static void main( String[] args ) {
	
	// setting up game
	System.out.println("Let's play a game.");
	System.out.println("The board can be of size 3 to 8.");
	System.out.println("What board size do you want: ");

	int input = Keyboard.readInt();

	while (input < 3 || input > 8) {
	    System.out.println("Please put in a valid size (3 to 8)");
	    input = Keyboard.readInt();
	}

	TakGame game = new TakGame(input);
	boolean isWhiteTurn = true;
	String s;

	// main game loop
	while (true) {
	    System.out.println(game);

	    if (isWhiteTurn) {
		s = "white";
	    }
	    else {
		s = "black";
	    }
	    
	    System.out.println("Now it's " + s + "'s turn.");	    

	    while (true) {
		try {
		    System.out.println("Place a piece.");
		    System.out.println("Row: ");
		    int row = Keyboard.readInt();
		    System.out.println("Column: ");
		    int col = Keyboard.readInt();
		    game.addPiece(row, col, s);
		    break;
		}
		catch (IndexOutOfBoundsException e) {
		    System.out.println("Please give a valid location on the board");
		}
		catch (IllegalArgumentException e) {
		    System.out.println("Please place the piece on an empty tile.");
		}
	    }

	    System.out.println("Move a stack.");
	    System.out.println("Row: ");
	    int row = Keyboard.readInt();
	    System.out.println("Column: ");
	    int col = Keyboard.readInt();
	    System.out.println("Stack size: ");
	    int stackSize = Keyboard.readInt();

	    game.moveStack(row, col, "test", stackSize);

	    System.out.println("Display a stack.");
	    System.out.println("Row: ");
	    row = Keyboard.readInt();
	    System.out.println("Column: ");
	    col = Keyboard.readInt();
	    
	    game.displayStack(row, col);

	    isWhiteTurn = !(isWhiteTurn);

	}

    }

}

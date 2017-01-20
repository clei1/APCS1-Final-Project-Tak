import cs1.Keyboard;

public class Woo {
    
    /*~~~~~~~~~~~~~METHODS~~~~~~~~~~~~~*/
    public static void playerTurn(Player p, Board b) {
	System.out.println("Player " + p.name + ", it's now your turn.");
	int counter = 1;
	int moveStone = -1;
	int moveStack = -1;
	int viewStack = -1;

	if(p.hasStones()){
	    moveStone = counter;
	    System.out.println(moveStone + ": Place a piece.");
	    counter += 1;
	}
	if(p.hasStacks(b)){
	    moveStack = counter;
	    System.out.println(moveStack + ": Move a stack.");
	    counter += 1;
	    viewStack = counter;
	    System.out.println(viewStack + ": Display a stack");
	}
        
	boolean playerMoved = false;
	
	while (!playerMoved) {
	    System.out.println("Your move: ");
	    int move = Keyboard.readInt();

	    if (move == moveStone && move != -1) {
		playerPlaceStone( p, b ); //would it be possible to have playerPlaceStone(p, b) return true or false so we could update playerMoved = playerPlaceStone(p, b);
		playerMoved = true;
	    }
	    else if (move == moveStack && move != -1) {
		playerMoveStack( p, b ); //same thing here
		playerMoved = true;
	    }
	    else if (move == viewStack && move != -1) {
		playerDisplayStack( p, b );
	    }
	    else {
		System.out.println("Please pick a valid option.");
	    }
	}
    }

    public static void playerPlaceStone(Player p, Board b) {
	System.out.println("Place a stone, wall, or capstone.");

	System.out.println("Choose a piece to place");
	String pieceType = Keyboard.readString();
	while(!pieceType.equals("stone") && !pieceType.equals("wall") && !pieceType.equals("capstone")){
	    System.out.println("That is an invalid input. Please input either 'stone,' 'capstone,' or 'wall'.");
	    pieceType = Keyboard.readString();
	}
	
	System.out.print("Location: ");
	int x = Keyboard.readInt();
	int y = Keyboard.readInt();

	boolean stonePlaced = false;
	while (!stonePlaced) {
	    try {
		p.placePiece(x, y, b, pieceType);
		stonePlaced = true;
	    }
	    catch (Exception e) {
		System.out.println("Piece could not be placed. Try again.");
		System.out.print("Location: ");
		x = Keyboard.readInt();
		y = Keyboard.readInt();
	    }
	}
    }
    
    public static void playerMoveStack( Player p, Board b ) {
	System.out.println("Move a stack.");

	System.out.println("Which stack would you like to move?");
	System.out.print("Location: ");
	int x = Keyboard.readInt();
	int y = Keyboard.readInt();
	System.out.println("Stack Size: ");
	int z = Keyboard.readInt();

	while (z > b.board[x][y].size()) {
	    System.out.println("Not a valid input.");
	    System.out.print("Stack Size: ");
	    z = Keyboard.readInt();
	}
       
	String s = "You can move in these directions: ";
	if (x != 0) {
	    s += "up ";
	}
	if (y != 4) {
	    s += "right ";
	}
	if (x != 4) {
	    s += "down ";
	}
	if (y != 0) {
	    s += "left ";
	}
	System.out.println(s);
        System.out.print("Direction: ");
	String answer = Keyboard.readString();
	
	boolean flag = true;
	while (flag) {
	    if (answer.equals("up") && x != 0) {
		p.moveStack(x, y, z, 0, b);
		flag = false;
	    }
	    else if (answer.equals("right") && y != 4) {
		p.moveStack(x, y, z, 1, b);
		flag = false;
	    }
	    else if (answer.equals("down") && x != 4) {
		p.moveStack(x, y, z, 2, b);
		flag = false;
	    }
	    else if (answer.equals("left") && y != 0) {
		p.moveStack(x, y, z, 3, b);
		flag = false;
	    }
	    else {
		System.out.println("Not a valid input.");
		System.out.println("Direction: ");
		answer = Keyboard.readString();
	    }
	}
	    
    }
	
        


    public static void playerDisplayStack( Player p, Board b ) {
	boolean flag = true;
	System.out.print("Location: ");
	int x = Keyboard.readInt();
	int y = Keyboard.readInt();

	while (flag) {
	    try {
		b.displayStack(x, y);
		flag = false;
	    }
	    catch (Exception e) {
		System.out.println("Not a valid input.");
		System.out.print("Location: ");
		x = Keyboard.readInt();
		y = Keyboard.readInt();
	    }

	}
    }

    public static void main (String[] args){
	Board woah = new Board();
	Player player1 = new Player("Alpha", 1); // player white
	Player player2 = new Player("Bravo", 0); // player black
	System.out.println(woah);
	boolean isPlayerOneTurn = true;

	// main game loop
	while ( (!woah.isRoad(0) && !woah.isRoad(1)) && (player1.numCap + player1.numStones > 0) && (player2.numCap + player2.numStones > 0) ){
	    if (isPlayerOneTurn) {
		playerTurn(player1, woah);
	    }
	    else {
		playerTurn(player2, woah);
	    }
	    System.out.println(woah);
	    isPlayerOneTurn = !isPlayerOneTurn;
	}
	
	if (woah.isRoad(0)) {
	    System.out.println("Black won!");
	}
	else {
	    System.out.println("White won!");
	}
    }


}


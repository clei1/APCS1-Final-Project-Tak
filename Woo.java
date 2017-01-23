import cs1.Keyboard;

public class Woo {
    
    /*~~~~~~~~~~~~~METHODS~~~~~~~~~~~~~*/
    public static void playerTurn(Player p, Board b) {
	System.out.println(p.name + ", it's now your turn.");
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
	}
	if(b.hasStacks()){
	    viewStack = counter;
	    System.out.println(viewStack + ": Display a stack");
	}
        
	boolean playerMoved = false;
	
	while (!playerMoved) {
	    System.out.println("Your move: ");
	    int move = Keyboard.readInt();

	    if (move == moveStone && move != -1) {
		playerMoved = playerPlaceStone( p, b );
	    }
	    else if (move == moveStack && move != -1) {
		playerMoved = playerMoveStack( p, b );
	    }
	    else if (move == viewStack && move != -1) {
		playerDisplayStack( p, b );
	    }
	    else {
		System.out.println("Please pick a valid option.");
	    }
	}
    }

    public static boolean playerPlaceStone(Player p, Board b) {
	System.out.println("Place a stone, wall, or capstone.");

	System.out.println("Choose a piece to place");
	String pieceType = Keyboard.readString();
	while(!pieceType.equals("stone") && !pieceType.equals("wall") && !pieceType.equals("capstone")){
	    System.out.println("That is an invalid input. Please input either 'stone,' 'capstone,' or 'wall'.");
	    pieceType = Keyboard.readString();
	}
	while(p.numCap == 0 && pieceType.equals("capstone")){
	    System.out.println("You don't have any capstones left. Please input either wall or stone");
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

	return true;
    }
    
    public static boolean playerMoveStack( Player p, Board b ) {
	System.out.println("Move a stack.");

	System.out.println("Which stack would you like to move?");

	System.out.print("Location: ");
	int x = Keyboard.readInt();
	int y = Keyboard.readInt();

	while ( b.stackOwner(x,y) != p.color ) {
	    System.out.println("You do not own this stack, please select a new one.");
	    System.out.print("Location: ");
	    x = Keyboard.readInt();
	    y = Keyboard.readInt();
	}

	System.out.println("How many stones from this stack would you like to carry.");
	System.out.println("Number: ");
	int z = Keyboard.readInt();

	while (z > b.board[x][y].size() || z > b.size ) {
	    System.out.println("You cannot carry this many stones.");
	    System.out.print("Stack Size: ");
	    z = Keyboard.readInt();
	}
       
	String s = "You can move in these directions: ";
	if (x != 0) {
	    s += "up ";
	}
	if (y != b.size-1) {
	    s += "right ";
	}
	if (x != b.size-1) {
	    s += "down ";
	}
	if (y != 0) {
	    s += "left ";
	}
	System.out.println(s);
        System.out.print("Direction: ");
	String answer = Keyboard.readString();
	int counter = z-1;
	
	boolean flag = true;
	while ( !( (answer.equals("up") && x != 0) ||
		   (answer.equals("right") && y != b.size-1) ||
		   (answer.equals("down") && x != b.size-1) ||
		   (answer.equals("left") && y != 0) ) ) {
	    System.out.println("Not a valid input.");
	    System.out.println("Direction: ");
	    answer = Keyboard.readString();
	}

	p.moveStack(x, y, z, answer, b);

	if (answer.equals("up")) {
	    x--;
	}
	else if (answer.equals("right")) {
	    y++;
	}
	else if (answer.equals("down")) {
	    x++;
	}
	else if (answer.equals("left")) {
	    y--;
	}

	while ( ( (answer.equals("up") && x != 0) ||
		  (answer.equals("right") && y != b.size-1) ||
		  (answer.equals("down") && x != b.size-1) ||
		  (answer.equals("left") && y != 0) )
		&& counter > 0 ) {

	    System.out.println("Would you like to end your turn now? (yes/no)");
	    System.out.print("Answer: ");
	    String endTurn = Keyboard.readString();

	    if (endTurn.equals("yes")) {
		break;
	    }

	    System.out.println("You can move up to " + counter + " stones.");
	    System.out.println("How many stones would you like to move?");
	    System.out.print("Number: ");
	    z = Keyboard.readInt();

	    while (z > counter) {
		System.out.println("You cannot carry this many stones.");
		System.out.print("Stack Size: ");
		z = Keyboard.readInt();
	    }

	    p.moveStack(x, y, z, answer, b);

	    if (answer.equals("up")) {
		x--;
	    }
	    else if (answer.equals("right")) {
		y++;
	    }
	    else if (answer.equals("down")) {
		x++;
	    }
	    else if (answer.equals("left")) {
		y--;
	    }
	   
	    counter = z - 1;
	}
	  
	return true;
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
	System.out.println("To play a beautiful game or to not play at all?");

	// NAME SELECTION
	System.out.print("Player 1 name is: ");
	String p1Name = Keyboard.readString();

	System.out.print("Player 2 name is: ");
	String p2Name = Keyboard.readString();

	// COLOR SELECTION
	System.out.println("What color would you like to be, " + p1Name + "?");
	System.out.println("0: Black");
	System.out.println("1: White");

	int p1Color = Keyboard.readInt();
	int p2Color = 0;
	boolean isPlayerOneTurn = true;
	
	if (p1Color == 0){
	    p2Color = 1;
	    isPlayerOneTurn = false;
	}

	// BOARD SIZE SELECTION
	System.out.println("Board size is: (input a number between 3 and 8 inclusive)");
	int size = Keyboard.readInt();
	while (size < 3 || size > 8) {
	    System.out.println("Yo, that's not a valid size. Please put what I told you to put.");
	    size = Keyboard.readInt();
	}
	
	// INITIALIZE BOARD AND PLAYERS
	Board woah = new Board(size);
	Player player1 = new Player(p1Name, p1Color, size);
	Player player2 = new Player(p2Name, p2Color, size);
	System.out.println(woah);

	// MAIN GAME LOOP
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
	
	// DISPLAY WHOM WON
	if (woah.isRoad(0)) {
	    System.out.println("Black won!");
	}
	else {
	    System.out.println("White won!");
	}
    }


}


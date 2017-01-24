import cs1.Keyboard;

public class Woo {
    
    /*~~~~~~~~~~~~~METHODS~~~~~~~~~~~~~*/
    
    /*
      void playerTurn(player p, Board b)
      precondition: board and player have been instatiated
      postcondition: the player is allowed to place a piece, move a stack, or view a stack
    */
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
	    System.out.println("\nYour move: ");
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

    /*
      boolean playerPlaceStone(Player p, Board b)
      precondition: instantiated board and player
      postcondition: returns true when the player successfully places a piece
    */

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
    /*
      boolean playerMoveStack(Player p, Board b)
      precondition: instantiated player and board
      postcondition: returns true when the player successfully moves a stack
    */
    
    public static boolean playerMoveStack( Player p, Board b ) {
	System.out.println("Move a stack.");

	System.out.println("\nWhich stack would you like to move?");

	System.out.print("Location: ");
	int x = Keyboard.readInt();
	int y = Keyboard.readInt();
	
	while ( b.stackOwner(x,y) != p.color ) {
	    System.out.println("You do not own this stack, please select a new one.");
	    System.out.print("Location: ");
	    x = Keyboard.readInt();
	    y = Keyboard.readInt();
	}

	System.out.println("\nHow many stones from this stack would you like to carry?");
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
    /*
      void playerDisplayStack(Player p, Board b)
      precondition: instantiated player and board
      postcondition: shows the player a stack if the player inputs acceptable coordinates
    */
    
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

    /*~~~~~~~~~~~~~MAIN METHOD~~~~~~~~~~~~~*/
    public static void main (String[] args){
	System.out.println("\n====================================================================\n");
	System.out.println("TAK: A BEAUTIFUL GAME");
	System.out.println("~Based on the KingKillerChronicles by Patrick Rothfuss (literally the greatest series ever written)~");
	String[] quotes = {"To play a beautiful game or to not play at all?",
			   "The entire game, not just the fiddling about with stones. The point is not to play as tight as you can. The point is to be bold. To be dangerous. Be elegant. -Bredon",
			   "A well-played game of tak reveals the moving of a mind. -Bredon",
			   "Why would I want to win anything other than a beautiful game? - Bredon",
			   "I call it Bredon's defense, but that's what I call any maneuver when I get out of a tight corner by being uncommonly clever. -Bredon"};
	System.out.println(quotes[(int)(Math.random() * 5)]+"\n");

	System.out.println("Welcome youngling! I see you're interested in Tak. I get you. It is indeed a beautiful game.");
	System.out.println("But enough about that. listen here, it's kind of important.\n");
	
	System.out.println("The objective of Tak:");
	System.out.println("Players will enter different commands and use the grid to identify spaces on a board.");
	System.out.println("The first person to create a 'road' with their 'stones' between two opposite sides of the board wins. Roads do not have to be in a straight line. Players will attack, capture and defend their own pieces.\n");

	System.out.println("The 'normal' way to win is creating a road.");
	System.out.println("However, the game also ends when the board is completely filled or at least one player runs out of pieces.");
	System.out.println("In this case, the player with the most regular stones on top of the tiles and stacks wins.\n");

	System.out.println("Meet the pieces:");

	System.out.println("Stone: a stone is your standard piece in Tak. it is counted as a part of roads, and can be moved anywhere, except on capstones and walls.");

	System.out.println("Wall: a wall is a standing stone. It is used to block roads from being formed. It can be moved anywhere, except on other walls and capstones. In addition, it can be flattened by capstones, essentially turning it back into a regular stone.");

	System.out.println("Capstone: a capstone is a special piece that can be moved anywhere, except on other capstones. It has the power to flatten walls back into stones.\n");

	System.out.println("How to input a board location correctly: Follow this format 'column row'");
	System.out.println("Example:");
	System.out.println("Location: 5 3");
	System.out.println("This tells the board that your piece will be placed in the 6th row at the 4th column. This may get tricky, so always keep it in mind.\n");

	System.out.println("Rules:");
	System.out.println("1. Roads are created by going from one end of the board to the other. This means from north to south (or vice versa) or east to west (or vice versa).");
	System.out.println("2. Stones cannot be placed on walls or capstones, and walls and capstones both do not count as part of a road.");
	System.out.println("3. Capstones can flatten walls back into regular stones.\n");

	System.out.println("Well, that's pretty much all you need to know. Now venture forth, into a beautiful world.\n");
	System.out.println("====================================================================\n");

	String[] titles = {"Edema Ruh", "Master Arcanist", "Fae", "Master Artificer", "E'lir", "Re'lar",
			   "University Chancellor", "Master Alchemist", "Master Arithmatician", "Master Linguist",
			   "Master Namer", "Master Physicker", "Master Rhetorician", "Master Sympathist", "Scriv",
			   "Giller", "Baron", "Maer"};
	
	// NAME SELECTION
	String p1Name = titles[(int)(Math.random() * titles.length)];
	System.out.print(p1Name + "'s name is: ");
	p1Name += " " + Keyboard.readString();

	String p2Name = titles[(int)(Math.random() * titles.length)];
	System.out.print(p2Name + "'s name is: ");
        p2Name += " " + Keyboard.readString();

	// COLOR SELECTION
	System.out.println("\nWhich shade of gray are you feeling, " + p1Name + "?");
	System.out.println("0: Black");
	System.out.println("1: White");

	//If the user doesn't select 0 or 1 during color selection phase
	int p1Color = Keyboard.readInt();
	while(p1Color != 0 && p1Color != 1){
	    System.out.println("Black and yellow. Black and yellow. Joking. Black or white? 0 or 1? Oreos or milk? Pandas or pandas?");
	    p1Color = Keyboard.readInt();
	}
	int p2Color = 0;
	boolean isPlayerOneTurn = true;
	
	if (p1Color == 0){
	    p2Color = 1;
	    isPlayerOneTurn = false;
	}

	// BOARD SIZE SELECTION
	System.out.println("\nBoard size is: (input a number between 3 and 8 inclusive)");
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
	    System.out.println("\nBlack won!");
	}
	else {
	    System.out.println("\nWhite won!");
	}
    }


}


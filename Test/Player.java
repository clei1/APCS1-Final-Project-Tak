import java.util.ArrayList;

public class Player {

    /*~~~~~~~~~~~~~INSTANCE VARIABLES~~~~~~~~~~~~~*/
    String name; 
    int numStones;
    int numCap;
    int color;

    /*~~~~~~~~~~~~~CONSTRUCTOR~~~~~~~~~~~~~*/
    //certain number of stones and capstones needed because the max number varies with the board size
    public Player( String n, int c, int size ) {
	name = n;
	color = c;

	switch (size) {
	case 3:
	    numStones = 10;
	    numCap = 0;
	    break;
	case 4:
	    numStones = 15;
	    numCap = 0;
	    break;
	case 5:
	    numStones = 21;
	    numCap = 1;
	    break;
	case 6:
	    numStones = 30;
	    numCap = 1;
	    break;
	case 7:
	    numStones = 40;
	    numCap = 2;
	    break;
	case 8:
	    numStones = 50;
	    numCap = 2;
	    break;
	}
    }

    public String getName()
	return name;

    public int getColor()
	return color;

    public boolean hasStones()
	return (numStones +  numCap > 0)

    public boolean noPiecesLeft()
	return ! hasStones();

    public boolean hasStacks(Board woah)
	return woah.hasStacks(color);
 
    public void firstTurn(Board woah){
	System.out.println("During the first turn, players will place one of their opponent's flatstones down on the board. You are allowed to place their piece on any tile on the board. Pick a location!");
	int x = Keyboard.readInt();
	int y = Keyboard.readInt();
	while((x < 0) ||
	      (x >= size) ||
	      (y < 0) ||
	      (y >= size)){
	    System.out.println("Your chosen location doesn't exist. Have you been eating plum bob? If so, that was a bad decision.");
	     x = Keyboard.readInt();
	     y = Keyboard.readInt();
	}

	while(woah.isOccupied(x, y)){
	    System.out.println("That space is occupied already. Pieces cannot be placed on already occupied pieces!");
	    x = Keyboard.readInt();
	    y = Keyboard.readInt();
	    while((x < 0) ||
		  (x >= size) ||
		  (y < 0) ||
		  (y >= size)){
		System.out.println("Your chosen location doesn't exist. Have you been eating plum bob? If so, that was a bad decision.");
		x = Keyboard.readInt();
		y = Keyboard.readInt();
	    }
	}
	b.board[x][y].add(new Stone(color, x, y, false));	
    }
    
    public void turn(Board woah){
	System.out.println(name + ", it's now your turn.");
	int counter = 1;
	int moveStone = 0;
	int moveStack = 0;
	int viewStack = 0;
	int move = -1;
	
	if(p.hasStones()){
	    moveStone = counter;
	    System.out.println(counter + ": Place a piece.");
	    counter += 1;
	}
	if(p.hasStacks(woah)){
	    moveStack = counter;
	    System.out.println(counter + ": Move a stack.");
	    counter += 1;
	}
	if(woah.hasStacks()){
	    viewStack = counter;
	    System.out.println(counter + ": Display a stack.");
	    counter += 1;
	}
        
        int move = Keyboard.readInt();
	
	while ((move <= 0) || (move >= counter)) {
	    System.out.println("There are three things all wise men fear: the sea in storm, a night with no moon, and the anger of a gentle man... Please use your eyes and brain.");
	    move = Keyboard.readInt();
	}

	while(move == viewStack){
	    displayStack(woah);
	    System.out.println("What is you next move?");
	    move = Keyboard.readInt();
	    while ((move <= 0) || (move >= counter)) {
		System.out.println("There are three things all wise men fear: the sea in storm, a night with no moon, and the anger of a gentle man... Please use your eyes and brain.");
		move = Keyboard.readInt();
	    }
	}
	
	switch(move){
	    case moveStone:
		placeStone (woah);
		break;
	    case moveStack:
	        moveStack(woah);
		break;
	}
    }

    public void placeStone(Board woah){
	System.out.println("Choose a piece to place: ");
	int counter = 1;
	int stone = 0;
	int wall = 0;
	int capstone = 0;
	int move = -1;
	
	if(numStones > 0){
	    stone = counter;
	    System.out.println(counter + ": Stone");
	    counter ++;
	    wall = counter;
	    System.out.println(counter + ": Wall");
	    counter ++;
	}
	if(numCap > 0){
	    capstone = counter;
	    System.out.println(counter + ": Capstone");
	    counter ++;
	}

	int move = Keyboard.readInt();
	while (move <= 0 || move >= counter) {
	    System.out.println("There are three things all wise men fear: the sea in storm, a night with no moon, and the anger of a gentle man... Please use your eyes and brain. \nIf a piece type is not showing up, it is because you don't have enough of that piece.");
	    move = Keyboard.readInt();
	}

	System.out.println("Location: (format is '0 0' where the first integer is the row and the second the column)");
	int x = Keyboard.readInt();
	int y = Keyboard.readInt();
	while((x < 0) ||
	      (x >= size) ||
	      (y < 0) ||
	      (y >= size)){
	    System.out.println("Your chosen location doesn't exist. Have you been eating plum bob? If so, that was a bad decision.");
	     x = Keyboard.readInt();
	     y = Keyboard.readInt();
	}

	while(woah.isOccupied(x, y)){
	    System.out.println("That space is occupied already. Pieces cannot be placed on already occupied pieces!");
	    x = Keyboard.readInt();
	    y = Keyboard.readInt();
	    while((x < 0) ||
		  (x >= size) ||
		  (y < 0) ||
		  (y >= size)){
		System.out.println("Your chosen location doesn't exist. Have you been eating plum bob? If so, that was a bad decision.");
		x = Keyboard.readInt();
		y = Keyboard.readInt();
	    }
	}

	switch(move){
	    case stone:
		b.board[x][y].add(new Stone(color, x, y, false));
		break;
	    case wall:
		b.board[x][y].add(new Stone(color, x, y, true));
		break;
	    case capstone:
		b.board[x][y].add(new Capstone(color, x, y, false));
		break;
	}
    }


    
    /*
      void moveStack(int x, int y, int stackSize, String direction, Board b)
      precondition: an instantiated board and players
      postcondition: moves a stack of a specified size; basically, you are dropping pieces off from a stack in the direction you move
      stones/walls/capstones are placed on new tiles
    */
    public void moveStack( int x, int y, int stackSize, String direction, Board b ) {
	ArrayList<Piece> stack =  b.board[x][y];
	int size = stack.size();
	
	//temporary ArrayList for the stack the user is moving
	ArrayList<Piece> holder = new ArrayList<Piece>();
	for (int i = size-1; i > size-1-stackSize; i--) {
	    holder.add( stack.remove(i) );
	}
	if (direction.equals("up")) {
	    for (Piece p : holder) {
		b.board[x-1][y].add(p);
	    }
	    x--;
	}
	else if (direction.equals("left")) {
	    for (Piece p : holder) {
		b.board[x][y-1].add(p);
	    }
	    y--;
	}
	else if (direction.equals("down")) {
	    for (Piece p : holder) {
		b.board[x+1][y].add(p);
	    }
	    x++;
	}
	else if (direction.equals("right")) {
	    for (Piece p : holder) {
		b.board[x][y+1].add(p);
	    }
	    y++;
	}

	stack = b.board[x][y];
	size = stack.size();
	
	//if the top of the tile that the player is moving a capstone to is a wall, flatten it
	if ( (stack.get(size-1) instanceof Capstone) && (stackSize == 1) ) {
	    try {
		((Capstone)(stack.get(size-1))).flattenWall((Stone)stack.get(size-2));
	    }
	    catch (Exception e) {}
	}

    }
}

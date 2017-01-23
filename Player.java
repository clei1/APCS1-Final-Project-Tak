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
	numStones = 21;
	numCap = 1;
	if(size == 8){
	    numStones = 50;
	    numCap = 2;
	}
	if(size == 7){
	    numStones = 40;
	    numCap = 2;
	}
	if(size == 6){
	    numStones = 30;
	}
	if(size == 4){
	    numStones = 15;
	    numCap = 0;
	}
	if(size == 3){
	    numStones = 10;
	    numCap = 0;
	}
    }

    /*~~~~~~~~~~~~~METHODS~~~~~~~~~~~~~*/
    /*
      void placePiece(int x, int y, Board b, String type)
      precondition: an instantiated board
      postcondition: returns nothing, but places a piece on the board
    */
    public void placePiece(int x, int y, Board b, String type) {
	//a player is only allowed to place a piece on an unoccupied tile
	//they can, however, MOVE a piece to an occupied tile.
	//note that placing and moving are different

	//if the tile IS empty...
	if (b.isEmpty(x,y)) {
	    //add a capstone if the input was a capstone
	    if(type.equals("capstone")){
		//but only if you have any capstones remaining
		if(numCap > 0) {
		    b.board[x][y].add(new Capstone(color, x, y, false));
		    numCap--;
		}
		else
		    throw new IllegalArgumentException("No more capstones to place.");
	    }

	    //add a stone if the input was a stone
	    else if(type.equals("stone")){
		//but only if you have any stones remaining (don't worry, these don't deplete as quickly as capstones)
		if(numStones > 0) {
		    b.board[x][y].add(new Stone(color, x, y, false));
		    numStones--;
		}
		else
		    throw new IllegalArgumentException("No more stones to place.");
	    }
	    //add a wall (which is basically a standing stone) if the input was wall
	    else if (type.equals("wall")){
		//that's why you check the numStones again
		if(numStones > 0) {
		    b.board[x][y].add(new Stone(color, x, y, true));
		    numStones--;
		}
		else
		    throw new IllegalArgumentException("No more stones to place.");
	    }
	    else
		throw new IllegalArgumentException("Not a valid type of piece to place.");
	}
	else
	    throw new IllegalArgumentException("You cannot place a piece on an occupied tile.");
}

    /*
      boolean hasStones()
      precondition: an instantiated board and players
      postcondition: returns true if the player has stones remaining, false otherwise
    */
    public boolean hasStones(){
	return (numStones +  numCap > 0);
    }

    /*
      boolean hasStacks()
      precondition: an instantiated board and players
      postcondition: returns true if the player controls (has a piece on top) of any stacks, false otherwise
    */
    public boolean hasStacks(Board b){
	return b.hasStacks(color);
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

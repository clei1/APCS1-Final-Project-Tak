import java.util.ArrayList;

public class Player {

    /*~~~~~~~~~~~~~INSTANCE VARIABLES~~~~~~~~~~~~~*/
    String name;
    int numStones;
    int numCap;
    int color;

    /*~~~~~~~~~~~~~CONSTRUCTOR~~~~~~~~~~~~~*/
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
    public void placePiece(int x, int y, Board b, String type) {
	/*	
	System.out.println(b.isFull());
	System.out.println("true means board is full");
	if(!b.isFull()){//is board is not full, try placing pieces
	    System.out.println("BOARD IS NOT FULL");
	*/
	if (b.isEmpty(x,y)) {
	    if(type.equals("capstone")){
		if(numCap > 0) {
		    b.board[x][y].add(new Capstone(color, x, y));
		    numCap--;
		}
		else
		    throw new IllegalArgumentException("No more capstones to place.");
	    }
	    else if(type.equals("stone")){
		if(numStones > 0) {
		    b.board[x][y].add(new Stone(color, x, y, false));
		    numStones--;
		}
		else
		    throw new IllegalArgumentException("No more stones to place.");
	    }
	    else if (type.equals("wall")){
		if(numStones > 0) {
		    b.board[x][y].add(new Stone(color, x, y, true));
		    numStones;
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

    public boolean hasStones(){
	return (numStones +  numCap > 0);
    }

    public boolean hasStacks(Board b){
	return b.hasStacks(color);
    }

    public void moveStack( int x, int y, int stackSize, String direction, Board b ) {
	ArrayList<Piece> stack =  b.board[x][y];
	int size = stack.size();

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

	if ( (stack.get(size-1) instanceof Capstone) && (stackSize == 1) ) {
	    try {
		((Capstone)(stack.get(size-1))).flattenWall((Stone)stack.get(size-2));
	    }
	    catch (Exception e) {}
	}

    }
}

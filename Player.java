import java.util.ArrayList;

public class Player {

    /*~~~~~~~~~~~~~INSTANCE VARIABLES~~~~~~~~~~~~~*/
    String name;
    int numStones;
    int numCap;
    int color;

    /*~~~~~~~~~~~~~CONSTRUCTOR~~~~~~~~~~~~~*/
    public Player( String n, int c ) {
	name = n;
	numStones = 21;
	numCap = 1;
	color = c;
    }

    /*~~~~~~~~~~~~~METHODS~~~~~~~~~~~~~*/
    public void placePiece(int x, int y, Board b, String type) {
        if (b.isEmpty(x,y)) {
	    if (type.equals("stone")) {
		if (numStones > 0) {
		    if(b.isCapstone(x,y)){
			throw new IllegalArgumentException("You cannot place a stone on a capstone.");
		    }
		    else if(b.isTopPieceWall(x,y)){
			throw new IllegalArgumentException("You cannot place a stone on a wall.");
		    }
		    else{
			b.board[x][y].add(new Stone(color, x, y, false));
		    }
		}
		else {
		    // throw exception; no more stones to place
		    throw new IllegalArgumentException("No more stones to place.");
		}
	    }

	    else if (type.equals("wall")) {
		if (numStones > 0) {
		    b.board[x][y].add(new Stone(color, x, y, true));
		}
		else {
		    // throw exception; no more walls to place
		    throw new IllegalArgumentException("No more stones to place.");
		}
	    }
	    else if (type.equals("capstone")) {
		if (numCap > 0) {
	    	    b.board[x][y].add(new Capstone(color, x, y));
	    	}
	    	else {
		    //throw exception; no more capstones to place
		    throw new IllegalArgumentException("No more capstones to place.");
	    	}
	    }
	    else {
		// throw exception; not a valid type of piece to place
		throw new IllegalArgumentException("Not a valid type of piece to place");
	    }

	}
	else {
	    // throw exception; player can't place stack on occupied tile
	    throw new IllegalArgumentException("You cannot place a piece on an occupied tile");
	}
	
    }

    public void moveStack( int x, int y, int stackSize, int direction, Board b ) {
	ArrayList<Piece> stack =  b.board[x][y];
	int size = stack.size();

	if (b.stackOwner(x,y) != color) {
	    // throw exception: player cannot move other player's stack
	}
	else {
	    ArrayList<Piece> holder = new ArrayList<Piece>();
	    for (int i = size-1; i > size-1-stackSize; i--) {
		holder.add( stack.remove(i) );
	    }
	    if (direction == 0) {
		for (Piece p : holder) {
		    b.board[x-1][y].add(p);
		}
		if(b.isTopPieceWall(x-1,y) && b.isCapstone(x-1,y)){
		    ((Capstone)((b.board[x-1][y]).get((b.board[x-1][y]).size() -1))).flattenWall(b,( (b.board[x-1][y]).get( (b.board[x-1][y]).size() -2) ), x-1,y);
		}
		
	    }
	    else if (direction == 1) {
		for (Piece p : holder) {
		    b.board[x][y-1].add(p);
		}
		if(b.isTopPieceWall(x,y-1) && b.isCapstone(x,y-1)){
		    ((Capstone)((b.board[x][y-1]).get((b.board[x][y-1]).size() -1))).flattenWall(b,((b.board[x][y-1]).get((b.board[x][y-1]).size() -2)), x,y-1);
		}
	    }
	    else if (direction == 2) {
		for (Piece p : holder) {
		    b.board[x+1][y].add(p);
		}
		if(b.isTopPieceWall(x+1,y) && b.isCapstone(x+1,y)){
		    ((Capstone)((b.board[x+1][y]).get((b.board[x+1][y]).size() -1))).flattenWall(b,((b.board[x+1][y]).get((b.board[x+1][y]).size() -2)), x+1,y);
		}
	    }
	    else if (direction == 3) {
		for (Piece p : holder) {
		    b.board[x][y+1].add(p);
		}
		if(b.isTopPieceWall(x,y+1) && b.isCapstone(x,y+1)){
		    ((Capstone)((b.board[x][y+1]).get((b.board[x][y+1]).size() -1))).flattenWall(b,((b.board[x][y+1]).get((b.board[x][y+1]).size() -2)), x,y+1);
		}
	    }
	}
    }
}

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
	if(b.isEmpty()){
	    if(!(numCap > 0) || (numStones > 0)){
		//exit, game is over, run roadCheck
	    }
            else if (b.isEmpty(x,y)) {
	        if(type.equals("capstone")){
		    if(numCap > 0)
	    	        b.board[x][y].add(new Capstone(color, x, y));
	            else
		        throw new IllegalArgumentException("No more capstones to place.");
	        }
                else if(type.equals("stone")){
		    if(numStones > 0)
	                b.board[x][y].add(new Stone(color, x, y, false));
		    else
			throw new IllegalArgumentException("No more stones to place.");
		}
	        else if (type.equals("wall")){
		    if(numStones > 0)
	                b.board[x][y].add(new Stone(color, x, y, true));
		    else
			throw new IllegalArgumentException("No more stones to place.");
		}
                else
		    throw new IllegalArgumentException("Not a valid type of piece to place.");
	    }
	    else
	        throw new IllegalArgumentException("You cannot place a piece on an occupied tile.");
	}
	else
	    throw new IllegalArgumentException("You cannot place pieces because the board has no empty tiles. You can only move existing pieces.");
    }

    public boolean hasStones(){
	return (numStones +  numCap > 0);
    }

    public boolean hasStacks(Board b){
	return b.hasStacks(color);
				      
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

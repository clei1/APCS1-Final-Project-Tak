public class Player {

    /*~~~~~~~~~~~~~INSTANCE VARIABLES~~~~~~~~~~~~~*/
    String name;
    int numStones;
    int numCap;

    /*~~~~~~~~~~~~~CONSTRUCTOR~~~~~~~~~~~~~*/
    public Player( String n ) {
	name = n;
	numStones = 21;
	numCap = 1;
    }

    /*~~~~~~~~~~~~~METHODS~~~~~~~~~~~~~*/
    public void placePiece(int x, int y, Board b, String type) {
        if (b.isEmpty(x,y)) {
	    if (type.equals("stone")) {
		if (numStones > 0) {
		    b[x][y].add(new Stone());
		}
		else {
		    // throw exception; no more stones to place
		}
	    }
	    else if (type.equals("wall")) {
		if (numStones > 0) {
		    b[x][y].add(new Stone());
		}
		else {
		    // throw exception; no more walls to place
		}
	    }
	    else if (type.equals("capstone")) {
		if (numCap > 0) {
		    b[x][y].add(new Capstone());
		}
		else {
		    // throw exception; no more capstones to place
		}
	    }
	    else {
		// throw exception; not a valid type of piece to place
	    }

	}
	else {
	    // throw exception; player can't place stack on occupied tile
	}
	
    }
}

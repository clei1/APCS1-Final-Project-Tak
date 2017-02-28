public class Stone extends Piece {
    
    /*~~~~~~~~~~~~~OVERLOADED CONSTRUCTOR~~~~~~~~~~~~~*/
    public Stone(int color, boolean b){
	super(color, b);
    }
    /*~~~~~~~~~~~~~OVERLOADED TOSTRING()~~~~~~~~~~~~~*/
    public String toString() {
	String s;
	if (color == 0 && !isWall) {
	    s = "\u25A0";
	}
	else if (color == 1 && !isWall) {
	    s = "\u25A2 ";
	}
	else if (color == 0 && isWall) {
	    s = "\u25B2";
	}
	else if (color == 1 && isWall) {
	    s = "\u25B3 ";
	}
	else {
	    throw new IllegalArgumentException("Not a valid color.");
	}
	return s;
    }
}

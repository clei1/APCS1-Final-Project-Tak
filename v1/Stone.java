public class Stone extends Piece {
    
    /*~~~~~~~~~~~~~OVERLOADED CONSTRUCTOR~~~~~~~~~~~~~*/
    public Stone(int color, int x, int y, boolean b){
	super(color, x, y, b);
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
    /*~~~~~~~~~~~~~METHODS~~~~~~~~~~~~~*/
    /*
      void wallToStone()
      precondition: an instantiated board with stones
      poscondition: changes a stone's status from wall to stone if it was flattened
    */
    public void wallToStone(){
	isWall = false;
    }
	

}

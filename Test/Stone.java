public class Stone extends Piece {
    
    /*~~~~~~~~~~~~~OVERLOADED CONSTRUCTOR~~~~~~~~~~~~~*/
    public Stone(int color, int x, int y, boolean b){
	super(color, x, y, b);
    }
    /*~~~~~~~~~~~~~OVERLOADED TOSTRING()~~~~~~~~~~~~~*/
    public String toString() {
	String s;
	if (color == 0) {
	    s = "BLACK ";
	}
	else if (color == 1) {
	    s = "WHITE ";
	}
	else {
	    throw new IllegalArgumentException("Not a valid color.");
	}
	if (isWall) {
	    s += "WALL";
	}
	else {
	    s += "STONE";
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

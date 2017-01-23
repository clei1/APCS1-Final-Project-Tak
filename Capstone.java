public class Capstone extends Piece{

    /*~~~~~~~~~~~~~OVERLOADED CONSTRUCTOR~~~~~~~~~~~~~*/
    public Capstone(int color, int x, int y, boolean wall){
	super(color,x,y, wall);
    }

    /*~~~~~~~~~~~~~METHODS~~~~~~~~~~~~~*/
    public void flattenWall(Stone s){
	s.wallToStone(); //fix to ONLY work with stones that are walls, not any piece
    }
    /*~~~~~~~~~~~~~OVERLOADED TOSTRING~~~~~~~~~~~~~*/
    public String toString(){
	String s;
	if (color == 0) {
	    s = "BLACK CAPSTONE";
	}
	else if (color == 1) {
	    s = "WHITE CAPSTONE";
	}
	else {
	    throw new IllegalArgumentException("Not a valid color.");
	}
	return s;
    }
}

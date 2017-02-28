public class Capstone extends Piece{

    /*~~~~~~~~~~~~~OVERLOADED CONSTRUCTOR~~~~~~~~~~~~~*/
    public Capstone(int color, boolean wall){
	super(color, wall);
    }
    
    /*~~~~~~~~~~~~~OVERLOADED TOSTRING~~~~~~~~~~~~~*/
    public String toString(){
	String s;
	if (color == 0) {
	    s = "\u2654 ";
	}
	else if (color == 1) {
	    s = "\u265A ";
	}
	else {
	    throw new IllegalArgumentException("Not a valid color.");
	}
	return s;
    }
}

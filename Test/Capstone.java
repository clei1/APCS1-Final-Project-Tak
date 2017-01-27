public class Capstone extends Piece{

    /*~~~~~~~~~~~~~OVERLOADED CONSTRUCTOR~~~~~~~~~~~~~*/
    public Capstone(int color, int x, int y, boolean wall){
	super(color,x,y, wall);
    }

    /*~~~~~~~~~~~~~METHODS~~~~~~~~~~~~~*/
    /*
      void flattenWall(Stone s)
      precondition: none
      postcondition: if the Stone under the Capstone is a wall, it will get flattened and will turn into a regular Stone
    */
    public void flattenWall(Stone s){
	s.wallToStone(); //invokes wallToStone() method on the Stone
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

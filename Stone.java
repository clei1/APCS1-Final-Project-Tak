public class Stone extends Piece {

    boolean isWall;

    public Stone(int color, int x, int y, boolean b){
	super(color, x, y);
	isWall = b;
    }

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

    public void wallToStone(){
	isWall = false;
    }
	

}

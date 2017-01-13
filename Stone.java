public class Stone extends Piece {

    boolean isWall;

    public Stone(int color, int x, int y, boolean b){
	Super(color, x, y);
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
	    throw new IllegalArgumentException();
	}
	if (isWall) {
	    s += "WALL";
	}
	else {
	    s += "STONE";
	}
    }

}

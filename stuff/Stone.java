public class Stone extends Piece{

    boolean isWall;
    
    public Stone(String team) {
	super(team);
	isWall = false;
    }

    public String toString() {
	if (team == "black") {
	    return "BLACK STONE";
	}
	else {
	    return "WHITE STONE";
	}

    }

}

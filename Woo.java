import cs1.Keyboard;

public class Woo {

    /*~~~~~~~~~~~~~INSTANCE VARIABLES~~~~~~~~~~~~~*/
    Player player1;
    Player player2;
    int oneNum; // number of stones player1 has
    int oneCap; // number of capstones player1 has
    int twoNum;
    int twoCap;

    /*~~~~~~~~~~~~~METHODS~~~~~~~~~~~~~*/
    /*public static void playerTurn() {
      }*/
    
    public void gameStart() {
	oneNum = 21;
	twoNum = 21;
	oneCap = 1;
	twoCap = 1;
    }

    public boolean piecesLeft() {
	return oneNum == 0 || twoNum == 0;
    }

    public String printBoard() {
	return "";
    }

    public String vertView(int x, int y) {
	return "";
    }

    public static void main (String[] args){
	int turns = 5;
	while(turns > 0){
	    Board woah = new  Board();
	}
    }


}

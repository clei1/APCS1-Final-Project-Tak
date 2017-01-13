import cs1.Keyboard;

public class Woo {

    // /*~~~~~~~~~~~~~INSTANCE VARIABLES~~~~~~~~~~~~~*/
    // Player player1;
    // Player player2;
    // int oneNum; // number of stones player1 has
    // int oneCap; // number of capstones player1 has
    // int twoNum;
    // int twoCap;

    /*~~~~~~~~~~~~~METHODS~~~~~~~~~~~~~*/
    public static void playerTurn(Player p, Board b) {
	System.out.println("Player " + p.name + ", it's now your turn.");
	System.out.println("Place a stone.");
	System.out.print("Row: ");
	int x = Keyboard.readInt();
	System.out.print("Col: ");
	int y = Keyboard.readInt();
	p.placePiece(x, y, b, "stone");
    }
    
    // public void gameStart() {
    // 	oneNum = 21;
    // 	twoNum = 21;
    // 	oneCap = 1;
    // 	twoCap = 1;
    // }

    // public boolean piecesLeft() {
    // 	return oneNum == 0 || twoNum == 0;
    // }

    // public String printBoard() {
    // 	return "";
    // }

    // public String vertView(int x, int y) {
    // 	return "";
    // }

    public static void main (String[] args){
	Board woah = new Board();
	Player player1 = new Player("Alpha", 1); // player white
	Player player2 = new Player("Bravo", 0); // player black
	System.out.println(woah);
	boolean isPlayerOneTurn = true;

	// main game loop
	while (!woah.isRoad()){
	    if (isPlayerOneTurn) {
		playerTurn(player1, woah);
	    }
	    else {
		playerTurn(player2, woah);
	    }
	    System.out.println(woah);
	    isPlayerOneTurn = !isPlayerOneTurn;
	}
    }


}

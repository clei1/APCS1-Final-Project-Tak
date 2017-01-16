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
	System.out.println("Place a stone, wall, or capstone.");

	System.out.println("Choose a piece to place");
	String pieceType = Keyboard.readString();
	while(!pieceType.equals("stone") && !pieceType.equals("wall") && !pieceType.equals("capstone")){
	    System.out.println("That is an invalid input. Please input either 'stone,' 'capstone,' or 'wall'.");
	    pieceType = Keyboard.readString();
	}
	
	System.out.print("Location: ");
	int x = Keyboard.readInt();
	int y = Keyboard.readInt();

	boolean stonePlaced = false;
	while (!stonePlaced) {
	    try {
		p.placePiece(x, y, b, pieceType);
		stonePlaced = true;
	    }
	    catch (Exception e) {
		System.out.println("Stone could not be placed. Try again.");
		System.out.print("Location: ");
		x = Keyboard.readInt();
		y = Keyboard.readInt();
	    }
	}
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
	while (!woah.isRoad(0) && !woah.isRoad(1)){
	    if (isPlayerOneTurn) {
		playerTurn(player1, woah);
	    }
	    else {
		playerTurn(player2, woah);
	    }
	    System.out.println(woah);
	    isPlayerOneTurn = !isPlayerOneTurn;
	}
	
	if (woah.isRoad(0)) {
	    System.out.println("Black won!");
	}
	else {
	    System.out.println("White won!");
	}
    }


}

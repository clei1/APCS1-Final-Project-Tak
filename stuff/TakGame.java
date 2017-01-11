import java.util.ArrayList;

public class TakGame {

    ArrayList[][] board;
    int boardSize;
    int whitePieces;
    int blackPieces;

    public TakGame(int size) {
	boardSize = size;
	board = new ArrayList[size][size];
	for (ArrayList[] i : board) {
	    for (int j = 0; j < i.length; j++) {
		i[j] = new ArrayList<Piece>();
	    }
	}
    }

    public String toString() {
	String s = "";
	for (ArrayList[] i : board) {
	    for (int j = 0; j < i.length; j++) {
		ArrayList list = i[j];
		if (list.size() == 0) {
		    s += "[EMPTY SPACE] ";
		}
		else {
		    s += "[" + list.get( list.size()-1 ).toString() + "] ";
		}
	    }
	    s += "\n";
	}
	return s;
    }

    public void addPiece(int row, int col, String team) {
        if (row > boardSize || col > boardSize) {
	    throw new IndexOutOfBoundsException();
	} 
	else if (board[row][col].size() != 0) {
	    throw new IllegalArgumentException();
	}
	else {
	    board[row][col].add( new Stone(team) );
	}
    }

    public void moveStack(int row, int col, String location, int stackSize) {
        ArrayList<Object> tempAL = board[row][col];
	if (tempAL.size() == 0) {
	    throw new IllegalArgumentException(); // no stack to move at location
	}
	else if (tempAL.size() < stackSize) {
	    throw new IndexOutOfBoundsException(); // not enough pieces to move at location
	}
        else {
	    ArrayList<Object> tempHolder = new ArrayList<Object>();
	    for (int i = 0; i < stackSize; i++) {
		tempHolder.add( tempAL.remove( tempAL.size()-1 ) );
	    }
	    for (int i = stackSize-1; i >= 0; i--) {
		board[row+1][col].add( tempHolder.get(i) );
	    }


	}


    }

    public void displayStack(int row, int col) {
	ArrayList<Object> a = board[row][col];
	System.out.println("TOP");
	for (int i = a.size()-1; i >= 0; i--) {
	    System.out.println( a.get(i).toString() );
	}
	System.out.println("BOTTOM");
    }

    public static void main( String[] args ) {
	TakGame game = new TakGame(7);
	System.out.println(game);
	game.addPiece(2, 2, "black");
	game.addPiece(3, 4, "white");
	System.out.println(game.board[2][2]);
	System.out.println();
	System.out.println(game);
	game.addPiece(2, 2, "white");

    }




}

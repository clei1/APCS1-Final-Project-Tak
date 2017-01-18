import java.util.ArrayList;
public class Board{
    
    /*~~~~~~~~~~~~~INSTANCE VARIABLES~~~~~~~~~~~~~*/
    ArrayList<Piece>[][] board;
    ArrayList<Piece>  stack;

    /*~~~~~~~~~~~~~DEFAULT CONSTRUCTOR~~~~~~~~~~~~~*/
    public Board(){
	board = new ArrayList[5][5];
	for(int row = 0; row < 5; row++){
	    for(int column = 0; column < 5; column++){
		board[row][column] = new ArrayList<Piece>();
	    }
	}
    }

    /*~~~~~~~~~~~~~OVERLOADED TOSTRING~~~~~~~~~~~~~*/
    /*=================================
      String toString()
      precond: an instantiated board
      postcond: the toString() of the board will be updated to reflect player moves
      =================================*/

    public String toString(){
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

    /*~~~~~~~~~~~~~METHODS~~~~~~~~~~~~~*/
    /*=================================
      boolean isEmpty(int row, int column)
      precond: an instantiated board
      postcond: returns true if there is no piece on the current tile
      returns false if there is a piece on the current tile
      =================================*/

    public boolean isEmpty(int row, int column){
	return (board[row][column]).size() == 0;
    }

    /*=================================
      int stackOwner(int x, int y)
      precond: an instantiated board
      postcond: -1 if empty, 0 if black, 1 if white
      =================================*/
    public int stackOwner(int x, int y){
	ArrayList<Piece> p = board[x][y];
        int lastPos = p.size()-1;
	if (p.isEmpty()) {
	    return -1;
	}
	else {
	    return p.get(lastPos).getColor();
	}
    }

    public void displayStack(int x, int y){
	System.out.println("TOP");
	for (int i = board[x][y].size()-1; i >= 0; i-- ) {
	    System.out.println(board[x][y].get(i));
	}
	System.out.println("BOTTOM");
    }    
    /*=================================
      boolean isNextTo(int row, int column)
      precond: an instantiated board
      postcond: returns true if the inputted tile is directly adjacent to the tile the player is currently on
      returns false otherwise
      =================================*/

    // public boolean isNextTo(int row, int column){
    // 	return (row == x + 1 || row == x - 1 || row == x) && (column == y+1 || column == y - 1 || column = y);
    // }
    /*=================================
      boolean isRow()
      precond: an instantiated board
      postcond: returns true if the player has successfully created a row
      returns false otherwise
      =================================*/

    public boolean hasRowControl(int row, int color){
	for (int j = 0; j < board.length; j++) {
	    if (stackOwner(row, j) != color) {
		return false;  
	    }
	}
	return true;
    }

    public boolean isRoad(int color){
	for (int i = 0; i < board.length; i++) {
	    if (hasRowControl(i, color)) {
		return true;
	    }
	}
	return false;
    }

}


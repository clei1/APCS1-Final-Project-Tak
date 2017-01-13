import java.util.ArrayList;
public class Board{
    
    /*~~~~~~~~~~~~~INSTANCE VARIABLES~~~~~~~~~~~~~*/
    ArrayList<Piece>[][] board;
    ArrayList<Piece>  stack;

    /*~~~~~~~~~~~~~DEFAULT CONSTRUCTOR~~~~~~~~~~~~~*/
    public Board(){
	board = new ArrayList<Piece>[5][5];
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
	return board;
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
      boolean isNextTo(int row, int column)
      precond: an instantiated board
      postcond: returns true if the inputted tile is directly adjacent to the tile the player is currently on
      returns false otherwise
      =================================*/

    public boolean isNextTo(int row, int column){
	return (row == x + 1 || row == x - 1 || row == x) && (column == y+1 || column == y - 1 || column = y);
    }
    

}


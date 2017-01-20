import java.util.ArrayList;
public class Board{
    
    /*~~~~~~~~~~~~~INSTANCE VARIABLES~~~~~~~~~~~~~*/
    ArrayList<Piece>[][] board;
    ArrayList<Piece>  stack;
    int firstRowPos;
    int firstColPos;
    int lastRowPos;
    int lastColPos;
    int size;
    int[][] checkedTile;

    /*~~~~~~~~~~~~~DEFAULT CONSTRUCTOR~~~~~~~~~~~~~*/
    public Board(){
	board = new ArrayList[5][5];
	for(int row = 0; row < 5; row++){
	    for(int column = 0; column < 5; column++){
		board[row][column] = new ArrayList<Piece>();
	    }
	}
	firstRowPos = 0;
	firstColPos = 0;
	size = 5;
	lastRowPos = size -1;
	lastColPos = size - 1;    
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

    public boolean hasStacks( int color){
	for(int col = 0; col < size; col ++){
	    for(int row = 0; row < size; row ++){
		if(board[col][row].size() > 0 && isTopPieceColor(col, row, color)){
		    return true;
		}
	    }
	}
	return false;
    }
    
    public boolean isFull(){
    	for(int col = 0; col < size; col ++){
	    for(int row = 0; row < size; row ++){
		if(isEmpty(col, row))
		    return true;
	    }
	}
	return false;
    }	
	
    public boolean isEmpty(int col, int row){
	return (board[col][row]).size() == 0;
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

    public boolean hasRowControl(int row, int color){
	for (int j = 0; j < board.length; j++) {
	    if (stackOwner(row, j) != color) {
		return false;  
	    }
	}
	return true;
    }

    public boolean isTopPieceColor(int col , int row, int color){
	//board[col][row] is an ArrayList<Piece>
	//ArrayList<Piece>.get(last item in the Array) is a Piece
	//Piece.getColor() returns an integer, white = 1, black = 0
	int lastPos = board[col][row].size() - 1;
	int topPieceColor  = board[col][row].get(lastPos).getColor();
	return topPieceColor == color;
    }

    public boolean isTopPieceWall(int col, int row){
	int lastPos = board[col][row].size() - 1;
	return board[col][row].get(lastPos).isWall;
    }

    public boolean isCapstone(int col, int row){
	int lastPos = board[col][row].size() - 1;
	if(board[col][row].get(lastPos).toString() == "BLACK CAPSTONE" ||
	   board[col][row].get(lastPos).toString() == "WHITE CAPSTONE"){
	    return true;
	}
	else{
	    return false;
	}
    }

    /*=================================
      boolean isRow(int color)
      precond: an instantiated board
      postcond: returns true if the player has successfully created a row
      returns false otherwise
      =================================*/
    
    public boolean isRoad(int color){

	//checks if there are the input colored non-wall stones in the first column
	boolean firstCol = false; //sets base to be false, ensuring that this will only be set true if the top of any stack in the first column is the same color as input and isn't a wall
	for(int row = 0; row < size && !firstCol; row ++){ //iterates from the top of the column to the bottom, exits when already true
	    if(board[firstColPos][row].size() != 0 && //checks if there are any stones in the tile
	       isTopPieceColor(firstColPos, row, color) && //checks if the top stone of the stack is the corresponding color
	       (!isTopPieceWall(firstColPos, row))){ //checks if the top stone of the stack isn't a wall
		firstCol = true;
	    }
	}

	//repeat of first column checking, but in last column 
	boolean lastCol = false;
	for(int row = 0; row < size && !lastCol; row ++){
	    if(board[lastColPos][row].size() != 0 &&
	       isTopPieceColor(lastColPos, row, color) &&
	       (! isTopPieceWall(lastColPos, row))){
		lastCol = true;
	    }
	}
	
	boolean firstRow = false;
	for(int col = 0; col < size && !firstRow; col ++){
	    if(board[col][firstRowPos].size() != 0 &&
	       isTopPieceColor(col, firstRowPos, color) &&
	       (! isTopPieceWall(col, firstRowPos))){
		firstRow = true;
	    }	    
	}
	
	boolean lastRow = false;
	for(int col = 0; col < size && !lastRow; col ++){
	    if(board[col][lastRowPos].size() != 0 &&
	       isTopPieceColor(col, lastRowPos, color) &&
	       (! isTopPieceWall(col, lastRowPos))){
		lastRow = true;
	    }	    
	}

	
	if((firstRow && lastRow) || (firstCol && lastCol)){
	    checkedTile = new int[size][size];
	    for(int row = 0; row < size; row ++){
		if(board[firstColPos][row].size() != 0){
		    if(branchH(firstColPos, row, lastColPos, color)){
			return true;
		    }
		}
	    }
	    for(int col = 0; col < size; col ++){
		if(board[col][firstRowPos].size() !=0){
		    if(branchV(col, firstRowPos, lastRowPos, color)){
			return true;
		    }
		}
	    }
	}
	return false;
    }

    public boolean hasSouth(int col, int row, int color){
	return ( ((row - 1) >= 0)                  &&
		 (checkedTile[col][row - 1] == 0)  &&
		 (board[col][row - 1].size() > 0)  &&
		 (! isTopPieceWall(col, row - 1))  &&
		 (isTopPieceColor(col, row -1, color)) );
    }

    public boolean hasNorth(int col, int row, int color){
	return ( ((row + 1) < size)                &&
		 (checkedTile[col][row + 1] == 0)  &&
		 (board[col][row + 1].size() > 0) &&
		 (! isTopPieceWall(col, row + 1))  &&
		 (isTopPieceColor(col, row + 1, color)) );
    }

    public boolean hasWest(int col, int row, int color){
	return ( ((col - 1) >= 0)                  &&
		 (checkedTile[col - 1][row] == 0)  &&
		 (board[col - 1][row].size() > 0) &&
		 (! isTopPieceWall(col - 1, row))  &&
		 (isTopPieceColor(col - 1, row, color)) );
    }

    public boolean hasEast(int col, int row, int color){
	return ( ((col + 1) < size)                &&
		 (checkedTile[col + 1][row] == 0)  &&
		 (board[col + 1][row].size() > 0) &&
		 (! isTopPieceWall(col + 1, row))  &&
		 (isTopPieceColor(col + 1, row, color)) );   
    }
	
    
    public boolean branchH(int col, int row, int lastColPos, int color){
	checkedTile[col][row] = 1;
	return((col == lastColPos) ||
	       (hasSouth(col, row, color)  && branchH(col, row -1, lastColPos, color)) ||
	       (hasNorth(col, row, color)  && branchH(col, row +1, lastColPos, color)) || 
	       (hasWest(col, row, color)   && branchH(col -1, row, lastColPos, color)) ||
	       (hasEast(col, row, color)   && branchH(col +1, row, lastColPos, color)) );    
    }
    
    public boolean branchV(int col, int row, int lastRowPos, int color){
	checkedTile[col][row] = 1;
	return((row == lastRowPos) ||
	       (hasSouth(col, row, color)  && branchH(col, row -1, lastRowPos, color)) ||
	       (hasNorth(col, row, color)  && branchH(col, row +1, lastRowPos, color)) || 
	       (hasWest(col, row, color)   && branchH(col -1, row, lastRowPos, color)) ||
	       (hasEast(col, row, color)   && branchH(col +1, row, lastRowPos, color)) );    
    }
}


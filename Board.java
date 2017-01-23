import java.util.ArrayList;

public class Board{
    
    ArrayList<Piece>[][] board; //the board is represented by a 2D array

    int size;        //size is the lenght and width of the board, they are equal because it is a square

    int[][] checkedTile; //this is the 2D array of the board, but 1 if tile is check, 0 if it hasn't been

    
    //Default constructor
    //Input: Takes a player inputted number between 3 to 8, defining the size
    public Board(int s){
	board = new ArrayList[s][s]; //created board with player defined size
	for(int col = 0; col < s; col ++){ //iterates through the columns
	    for(int row = 0; row < s; row ++){ //for each column, create rows
		board[row][col] = new ArrayList<Piece>(); //sets each array to contain an ArrayList
	    }
	}
	size = s; //sets size to player defined size 
    }


    
    //Overwritten toString()
    //Prints the board, converts everything in the 2D Array into a string
    public String toString(){
	String s = "";
	for (ArrayList[] i : board) {
	    for (int j = 0; j < i.length; j++) {
		ArrayList list = i[j];
		if (list.size() == 0) {
		    s += "[EMPTY SPACE] ";
		}
		else {
		    s += "[" + list.get( list.size() - 1 ).toString() + "] ";
		}
	    }
	    s += "\n";
	}
	return s;
    }


    
    //prints out a stack at a tile, with the bottom piece on the bottom
    public void displayStack(int x, int y){
	System.out.println("TOP");
	for (int i = board[x][y].size()-1; i >= 0; i-- ) {
	    System.out.println(board[x][y].get(i));
	}
	System.out.println("BOTTOM");
    }


    
    //takes a color and checks if there are any stacks of that color
    //returns TRUE if there is a stack that controlled by that color
    public boolean hasStacks(int color){
	for(int col = 0; col < size; col ++){
	    for(int row = 0; row < size; row ++){
		if(stackOwner(col, row) == color){
		    //if tile is empty, it inputs -1
		    //if tile is controlled by black, it inputs 0
		    //if tile is controlled by white, it inputs 1
		    return true;
		}
	    }
	}
	return false;
    }


    
    //returns TRUE if there are any stacks/pieces on the board
    //returns FALSE if the board has no stacks/pieces on it
    public boolean hasStacks(){
	for(int col = 0; col < size; col ++){
	    for(int row = 0; row < size; row ++){
		if(isOccupied(col, row)){
		    //if tile is not empty, it inputs TRUE
		    //if tile is empty, it inputs FALSE
		    return true;
		}
	    }
	}
	return false;
    }


    
    //returns TRUE if the tile at position is empty
    //returns FALSE if the tile has any pieces on it
    public boolean isEmpty(int col, int row){
	return board[col][row].size() == 0;
    }


    
    //returns TRUE if the tile at position is not empty
    //returns FALSE if the tile is empty
    public boolean isOccupied(int col, int row){
	return (!isEmpty(col, row));
    }


    
    //returns -1 if tile doesn't have any pieces
    //returns 0 if the top stone of the stack is black
    //returns 1 if the top stone of the stack is white
    public int stackOwner(int x, int y){
	ArrayList<Piece> p = board[x][y];
        int lastPos = p.size()-1;
	if (p.isEmpty())
	    return  -1;
	else
	    return p.get(lastPos).getColor();
    }



    //returns TRUE if the inputted color owns the stack at tile
    //returns FALSE if the inputted color does not own the stack at tile
    public boolean isTopPieceColor(int col , int row, int color){
	return stackOwner(col, row) == color;
    }



    //returns TRUE if the top piece of a stack is a wall
    //returns FALSE if the top piece of a stack is not a wall
    public boolean isTopPieceWall(int col, int row){
	int lastPos = board[col][row].size() - 1;
	//System.out.println(board[col][row].get(lastPos));
	return board[col][row].get(lastPos).isWall;
    }



    //returns TRUE if the top piece of a stack is not a wall
    //returns FALSE if the top piece of a stack is a wall
    public boolean isTopPieceNotWall(int col, int row){
	return (!isTopPieceWall(col, row));
    }



    //returns TRUE if the top piece of a stack is a capstone
    //returns FALSE if the top piece of a stack is not a capstone
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



    //returns TRUE if there is a possible road based on the first column and last column or first row and last row
    //returns FALSE if there is no possible road at all
    public boolean isRoadPossible(int color){

	boolean firstCol = false;
	for(int x = 0; x < size && !firstCol; x ++){
	    if(isOccupied(x, 0) &&
	       isTopPieceNotWall(x, 0) &&
	       isTopPieceColor(x, 0, color))
		firstCol = true;
	}
	

	boolean lastCol = false;
	if(firstCol){
	    for(int x = 0; x < size && !lastCol; x ++){ 
		if(isOccupied(x, size - 1)        &&
		   isTopPieceNotWall(x, size - 1) &&  
		   isTopPieceColor(x, size - 1, color)){	
		    lastCol = true;
		}
	    }
	}
	
	boolean firstRow = false;
	for(int x = 0; x < size && !firstRow; x ++){
	    if(isOccupied(0, x)        &&
	       isTopPieceNotWall(0, x) &&  
	       isTopPieceColor(0, x, color)){
		firstRow = true;
	    }	    
	}

	boolean lastRow = false;
	if(firstRow){
	    for(int x = 0; x < size && !lastRow; x ++){
		if(isOccupied(size - 1, x)        &&
		   isTopPieceNotWall(size - 1, x) &&
		   isTopPieceColor(size - 1, x, color)){
		    lastRow = true;
		}
	    }
	}

	return ((firstCol && lastCol) ||
		(firstRow && lastRow));
	
    }

    

    //prints out the checkedTiles, 0 indicating they haven't been, but 1 if they have been
    public void printChecked(){
	for(int col = 0; col < size; col ++){
	    System.out.println("");
	    for(int row = 0; row < size; row ++){
		System.out.print(checkedTile[col][row]);		
	    }
	}
    }
    
    public boolean hasSouth(int col, int row, int color){
	return ( ((col + 1) < size) &&
		 (checkedTile[col + 1][row] != 1) &&
		 (isOccupied(col + 1, row)) &&
		 (isTopPieceNotWall(col + 1, row)) &&
		 (isTopPieceColor(col + 1, row, color)));
    }

    public boolean hasNorth(int col, int row, int color){
	return ( ((col - 1) >= 0)        &&
		 (checkedTile[col - 1][row] != 1)  &&		 
		 (isOccupied(col - 1, row))        &&		 
		 (!isTopPieceWall(col - 1, row)) &&
		 (isTopPieceColor(col - 1, row, color)) );
    }

    public boolean hasWest(int col, int row, int color){
	return ( ((row - 1) >= 0)        &&
		 (checkedTile[col][row - 1] != 1)  &&
		 (isOccupied(col, row - 1))        &&
		 (!isTopPieceWall(col, row - 1)) &&
		 (isTopPieceColor(col , row - 1, color)) );
    }

    public boolean hasEast(int col, int row, int color){
	return ( ((row + 1) < size)                &&
		 (checkedTile[col][row + 1] != 1)  &&
		 (isOccupied(col, row + 1))        &&
		 (!isTopPieceWall(col, row + 1)) &&
		 (isTopPieceColor(col, row + 1, color)) );   
    }
	
    
    public boolean branchH(int col, int row, int color){
	checkedTile[col][row] = 1;
	return((row == size - 1) ||
	       (hasSouth(col, row, color)  && branchH(col, row + 1, color)) ||
	       (hasNorth(col, row, color)  && branchH(col, row - 1, color)) || 
	       (hasWest(col, row, color)   && branchH(col - 1, row, color)) ||
	       (hasEast(col, row, color)   && branchH(col + 1, row, color)) );    
    }
    
    public boolean branchV(int col, int row, int color){
	checkedTile[col][row] = 1;
	return((col == size - 1) ||
	       (hasSouth(col, row, color)  && branchV(col, row + 1, color)) ||
	       (hasNorth(col, row, color)  && branchV(col, row - 1, color)) || 
	       (hasWest(col, row, color)   && branchV(col - 1, row, color)) ||
	       (hasEast(col, row, color)   && branchV(col + 1, row, color)) );    
    }
    

    
    //returns TRUE if there is a road created of the inputted color
    //returns FALSE if there is no road created of the inputted color 
    public boolean isRoad(int color){
	if(isRoadPossible(color)){
	    //input TRUE if there is a possible road based on the first column and last column or first row and last row
	    //input FALSE if there is no possible road at all
	    checkedTile = new int[size][size];
	    //printChecked();
	    
	    for(int x = 0; x < size; x ++){//iterates though the rows in the first column
		if(isOccupied(0, x) && isTopPieceColor(0, x, color)){
		    //returns TRUE if the tile at position is not empty
		    //returns FALSE if the tile is empty
		    if(branchV(0, x, color))
			return true;
		}
	    }
	    
	    for(int x = 0; x < size; x ++){//iterates through the columns in the first row
		if(isOccupied(x, 0) && isTopPieceColor(x, 0, color)){
		    //returns TRUE if the tile at position is not empty
		    //returns FALSE if the tile is empty
		    if(branchH(x, 0, color))
			return true;
		}
	    }
	}
	return false;
    }
}

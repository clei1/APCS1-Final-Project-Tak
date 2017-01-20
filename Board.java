import java.util.ArrayList;

public class Board{
    
    ArrayList<Piece>[][] board; //the board is represented by a 2D array

    int firstRowPos; //the first row is always 0
    int firstColPos; //the first column is always 0

    int lastRowPos;  //the last row is one less than the size
    int lastColPos;  //the last column is one less than the size

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
	firstRowPos = 0; //sets firstRowPos to 0
	firstColPos = 0; //sets firstColPos to 0
	size = s; //sets size to player defined size 
	lastRowPos = size -1; //sets lastRowPos to largest row possible
	lastColPos = size - 1;//sets lastColPos to largest column possible
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
	//checks if there are the input colored non-wall stones in the first column
	boolean firstCol = false; //sets base to be false, ensuring that this will only be set true if the top of any stack in the first column is the same color as input and isn't a wall
	for(int row = 0; row < size && !firstCol; row ++){ //iterates from the top of the column to the bottom, exits when already true
	    if(isOccupied(firstColPos, row)  &&
	       //input TRUE if the tiles in the first column(left most column) have stones on them
	       //input FALSE if the tiles in the first column(left most column) are empty

	       isTopPieceNotWall(firstColPos, row) &&
	       //input TRUE if the non empty tile does not have a wall
	       //input FALSE if the non empty tile has a wall
	       
	       isTopPieceColor(firstColPos, row, color)){
	       //input TRUE if the non empty tile is controlled by the color
	       //input FALSE if the non empty tile is not controlled by the color
		firstCol = true;
	    }
	}

	boolean lastCol = false;
	//checks if there are the input colored non-wall stones in the last column
	for(int row = 0; row < size && !lastCol; row ++){ //iterates from the top of the column to the bottom, exits when already true
	    if(isOccupied(lastColPos, row)        &&
	       isTopPieceNotWall(lastColPos, row) &&  
	       isTopPieceColor(lastColPos, row, color)){	
		lastCol = true;
	    }
	}
	
	boolean firstRow = false;
	for(int col = 0; col < size && !firstRow; col ++){
	    if(isOccupied(col, firstRowPos)        &&
	       isTopPieceNotWall(col, firstRowPos) &&  
	       isTopPieceColor(col, firstRowPos, color)){
		firstRow = true;
	    }	    
	}
	
	boolean lastRow = false;
	for(int col = 0; col < size && !lastRow; col ++){
	    if(isOccupied(col, lastRowPos)        &&
	       isTopPieceNotWall(col, lastRowPos) &&
	       isTopPieceColor(col, lastRowPos, color)){
		lastRow = true;
	    }
	}

	System.out.println("firstCol: " + firstCol);
	System.out.println("lastCol: " + lastCol);
	System.out.println("firstCol && lastCol: " + (firstCol && lastCol));
	System.out.println("firstRow: " + firstRow);
	System.out.println("lastRow: " + lastRow);
	System.out.println("firstRow && lastRow: " + (firstRow && lastRow));

	return ((firstCol && lastCol) ||
		(firstRow && lastRow));
	
    }

    

    //prints out the checkedTiles, 0 indicating they haven't been, but 1 if they have been
    public void printChecked(){
	for(int col = 0; col < size; col ++){
	    System.out.println("");
	    for(int row = 0; row < size; row ++){
		System.out.println(checkedTile[col][row]);		
	    }
	}
    }
    
    public boolean hasSouth(int col, int row, int color){
	return ( ((row + 1) < size)                &&
		 //input TRUE if the southern tile is within board limits
		 //checks if there is a tile under inputted tile
		 //ex. [3][4], checks [3][5]

		 (checkedTile[col][row + 1] == 0)  &&
		 //input TRUE if the southern tile has not been checked
		 //input FALSE if the southern tile has been checked
		 
		 (isOccupied(col, row + 1))  &&
		 //input TRUE if the southern tile is not empty
		 //input FALSE if the southern tile is empty
		 
		 (isTopPieceNotWall(col, row + 1))  &&
		 //input TRUE if the southern tile is not a wall
		 //input FALSE if the southern tile is  a wall

		 (isTopPieceColor(col, row + 1, color)));
	         //returns TRUE if the inputted color owns the stack at tile
	         //returns FALSE if the inputted color does not own the stack at tile
    }

    public boolean hasNorth(int col, int row, int color){
	return ( ((row - 1) >= firstRowPos)        &&
		 (checkedTile[col][row - 1] == 0)  &&		 
		 (isOccupied(col, row - 1))        &&		 
		 (isTopPieceNotWall(col, row - 1)) &&
		 (isTopPieceColor(col, row - 1, color)) );
    }

    public boolean hasWest(int col, int row, int color){
	return ( ((col - 1) >= firstColPos)        &&
		 (checkedTile[col - 1][row] == 0)  &&
		 (isOccupied(col - 1, row))        &&
		 (isTopPieceNotWall(col - 1, row)) &&
		 (isTopPieceColor(col - 1, row, color)) );
    }

    public boolean hasEast(int col, int row, int color){
	return ( ((col + 1) < size)                &&
		 (checkedTile[col + 1][row] == 0)  &&
		 (isOccupied(col + 1, row))        &&
		 (isTopPieceNotWall(col + 1, row)) &&
		 (isTopPieceColor(col + 1, row, color)) );   
    }
	
    
    public boolean branchH(int col, int row, int lastColPos, int color){
	checkedTile[col][row] = 1;
	return((col == lastColPos) ||
	       (hasSouth(col, row, color)  && branchH(col, row + 1, lastColPos, color)) ||
	       (hasNorth(col, row, color)  && branchH(col, row - 1, lastColPos, color)) || 
	       (hasWest(col, row, color)   && branchH(col - 1, row, lastColPos, color)) ||
	       (hasEast(col, row, color)   && branchH(col + 1, row, lastColPos, color)) );    
    }
    
    public boolean branchV(int col, int row, int lastRowPos, int color){
	checkedTile[col][row] = 1;
	return((row == lastRowPos) ||
	       (hasSouth(col, row, color)  && branchH(col, row + 1, lastRowPos, color)) ||
	       (hasNorth(col, row, color)  && branchH(col, row - 1, lastRowPos, color)) || 
	       (hasWest(col, row, color)   && branchH(col - 1, row, lastRowPos, color)) ||
	       (hasEast(col, row, color)   && branchH(col + 1, row, lastRowPos, color)) );    
    }
    

    
    //returns TRUE if there is a road created of the inputted color
    //returns FALSE if there is no road created of the inputted color 
    public boolean isRoad(int color){
	if(isRoadPossible(color)){
	    //input TRUE if there is a possible road based on the first column and last column or first row and last row
	    //input FALSE if there is no possible road at all
	    checkedTile = new int[size][size];
	    printChecked();
	    
	    for(int row = 0; row < size; row ++){//iterates though the rows in the first column
		if(isOccupied(firstColPos, row)){
		    //returns TRUE if the tile at position is not empty
		    //returns FALSE if the tile is empty
		    if(branchH(firstColPos, row, lastColPos, color))
			return true;
		}
	    }
	    
	    for(int col = 0; col < size; col ++){//iterates through the columns in the first row
		if(isOccupied(col, firstRowPos)){
		    //returns TRUE if the tile at position is not empty
		    //returns FALSE if the tile is empty
		    if(branchV(col, firstRowPos, lastRowPos, color))
			return true;
		}
	    }
	}
	return false;
    }
}
